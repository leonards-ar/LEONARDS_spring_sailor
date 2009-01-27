/*
 * Created on Jul 20, 2004
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
public class ReservationShift implements Serializable {
	private Integer shiftId = null;
	private Shift shift = null;
	private Date date = null;
	private Integer reservationId = null;
	
	/**
	 * 
	 */
	public ReservationShift() {
		super();
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public Shift getShift() {
		if( shift == null ) {
			try {
				shift = DBHome.retrieveShift(getShiftId());
			} catch(Throwable ex) {
				shift = null;
			}
		}
		return shift;
	}

	/**
	 * @return
	 */
	public Integer getShiftId() {
		return shiftId;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param shift
	 */
	public void setShift(Shift shift) {
		this.shift = shift;
	}

	/**
	 * @param integer
	 */
	public void setShiftId(Integer integer) {
		shiftId = integer;
	}

	/**
	 * @return
	 */
	public Integer getReservationId() {
		return reservationId;
	}

	/**
	 * @param integer
	 */
	public void setReservationId(Integer integer) {
		reservationId = integer;
	}

	public Double getValue() {
		return getShift().getValueOf(getDayOfWeek());
	}
	
	private int getDayOfWeek() {
		try {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(getDate());
			return calendar.get(Calendar.DAY_OF_WEEK);
		} catch(Throwable ex) {
			return -1;
		}
	}
	
	/**
	 * 
	 * @param baseDate
	 * @param seconds
	 * @return
	 */
	private Date buildDate(Date baseDate, long seconds) {
		return new Date(baseDate.getTime() + (seconds * 1000));
	}
	
	/**
	 * @return
	 */
	public Date getFrom() {
		try {
			return buildDate(getDate(), getShift().getTimeFrom());
		} catch( Throwable ex ) {
			return null;
		}
	}

	/**
	 * @return
	 */
	public Date getTo() {
		try {
			return buildDate(getDate(), getShift().getTimeTo());
		} catch( Throwable ex ) {
			return null;
		}
	}
}
