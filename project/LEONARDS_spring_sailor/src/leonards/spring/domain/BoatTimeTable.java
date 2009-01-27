/*
 * Created on Jul 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoatTimeTable implements Serializable {
	private Integer boatId = null;
	private Boat boat = null;
	private Vector days = null;
	private Vector shifts = null;
	
	/**
	 * 
	 */
	public BoatTimeTable() {
		super();
	}
	
	public Vector getDays() {
		if(days == null) {
			days = new Vector();
			int qty = getTotalTimeTableSize();
			BoatTimeTableDay aDay;
			Date currentDay = null;
			for(int i=0; i < qty; i++) {
				aDay = new BoatTimeTableDay();
				currentDay = getNextDay(currentDay);
				aDay.setDay(currentDay);
				aDay.setBoat(getBoat());
				aDay.setShifts(getDayShifts(aDay.getDay()));
				days.addElement(aDay);
			}
		}
		return days;		
	}

	private Date getFirstDay() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(df.format(new Date()));
		} catch( Throwable ex ) {
			return new Date();
		}
	}
	
	private Date getNextDay(Date currentDay) {
		if( currentDay == null ) {
			return getFirstDay();
		}
		return new Date(currentDay.getTime() + (24L * 60 * 60 * 1000));
	}
	
	private int getTotalTimeTableSize() {
		int excedentDays = 0;
		try {
			excedentDays = ( DBHome.retrieveMaximumReservationShifts() / getShiftsPerDay() ) + 1;
		} catch(Throwable ex) {
			excedentDays = 7;
		}
		return getDaysToDisplay() + excedentDays;
	}
	
	public int getDaysToDisplay() {
		try {
			return StaticDataManager.getParameter(Parameter.BOAT_TIME_TABLE_DAYS).getValueAsInt();
		} catch(Throwable ex) {
			return 15;
		}
	}
	
	public Vector getShifts() {
		if(shifts == null) {
			try {
				shifts = DBHome.retrieveShifts();
			} catch( Throwable ex) {
				shifts = null;
			}
		}
		return shifts;
	}
	
	private Vector getDayShifts(Date day) {
		try {
			Iterator shifts = getShifts().iterator();
			Vector dayShifts = new Vector();
			BoatTimeTableShift aDayShift;
			Shift aShift;
			while(shifts.hasNext()) {
				aShift = (Shift)shifts.next();
				aDayShift = new BoatTimeTableShift();
				aDayShift.setDay(day);
				aDayShift.setShift(aShift);
				aDayShift.setReservation(DBHome.retrieveReservation(day, aShift.getIdAsInteger()));
				dayShifts.addElement(aDayShift);
			}
			return dayShifts;
		} catch(Throwable ex) {
			return null;
		}
	}
	/**
	 * @return
	 */
	public Boat getBoat() {
		if(boat == null) {
			try {
				boat = DBHome.retrieveBoat(getBoatId());
			} catch(Throwable ex) {
				boat = null;
			}
		}
		return boat;
	}

	/**
	 * @return
	 */
	public Integer getBoatId() {
		return boatId;
	}

	/**
	 * @param boat
	 */
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	/**
	 * @param integer
	 */
	public void setBoatId(Integer integer) {
		boatId = integer;
	}

	/**
	 * @return
	 */
	private int getShiftsPerDay() {
		return getShifts() != null && getShifts().size() > 0 ? getShifts().size() : 1;
	}

}
