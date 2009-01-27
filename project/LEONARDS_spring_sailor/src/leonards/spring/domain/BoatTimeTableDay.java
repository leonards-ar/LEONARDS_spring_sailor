/*
 * Created on Jul 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoatTimeTableDay implements Serializable {
	private Boat boat = null;
	private Vector shifts = null;
	private Date day;
	
	/**
	 * 
	 */
	public BoatTimeTableDay() {
		super();
	}

	public boolean isBoatAvailable() {
		return getBoat().getState().canReserve() || 
		       ((getBoat().getStateDateFrom() != null && getDay().getTime() < getBoat().getStateDateFrom().getTime()) ||
		        (getBoat().getStateDateTo() != null && getDay().getTime() > getBoat().getStateDateTo().getTime()));
	}

	/**
	 * @return
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @return
	 */
	public Vector getShifts() {
		return shifts;
	}

	/**
	 * @param date
	 */
	public void setDay(Date date) {
		day = date;
	}

	/**
	 * @param vector
	 */
	public void setShifts(Vector vector) {
		shifts = vector;
	}

	/**
	 * @return
	 */
	public Boat getBoat() {
		return boat;
	}

	/**
	 * @param boat
	 */
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public boolean isWeekendDay() {
		int dayOfWeek = getDayOfWeek();
		return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
	}
	
	private int getDayOfWeek() {
		try {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(getDay());
			return calendar.get(Calendar.DAY_OF_WEEK);
		} catch(Throwable ex) {
			return -1;
		}
	}	
}
