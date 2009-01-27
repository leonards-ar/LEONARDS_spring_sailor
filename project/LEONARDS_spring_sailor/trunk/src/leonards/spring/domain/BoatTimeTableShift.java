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

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoatTimeTableShift implements Serializable, Cloneable {
	private Reservation reservation;
	private Shift shift = null;
	private Date day = null;

	/**
	 * 
	 */
	public BoatTimeTableShift() {
		super();
	}
	
	public boolean isAvailable() {
		return !isReserved();
	}

	public boolean isReserved() {
		return getReservation() != null;
	}

	public boolean canBeCancelled() {
		return isReserved() && getReservation().getFrom().getTime() > System.currentTimeMillis(); 	
	}

	/**
	 * @return
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * @param reservation
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public Double getValue() {
		int dayOfWeek = getDayOfWeek();
		return getShift().getValueOf(dayOfWeek);
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
	
	/**
	 * @return
	 */
	public Shift getShift() {
		return shift;
	}

	/**
	 * @param shift
	 */
	public void setShift(Shift shift) {
		this.shift = shift;
	}

	/**
	 * @return
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param day
	 */
	public void setDay(Date day) {
		this.day = day;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
