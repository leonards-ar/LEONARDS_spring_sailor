/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Shift.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Shift extends PersistentObject implements Serializable {
	private String description = null;
	private Vector details = null;
	private long timeTo = 0; // In seconds
	private long timeFrom = 0; // In seconds
	private boolean enabled = true;
	
	/**
	 * 
	 */
	public Shift() {
		super();
	}

	/**
	 * @param id
	 */
	public Shift(Object id) {
		super(id);
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#update()
	 */
	protected void update() throws NestedException {
		DBHome.update(this);
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#insert()
	 */
	protected void insert() throws NestedException {
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#delete()
	 */
	public void delete() throws NestedException {
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public Vector getDetails() {
		if( details == null ) {
			try {
				details = DBHome.retrieveShiftDetails(getIdAsInteger());
			} catch( Throwable ex ) {
				details = null;
			}
		}
		return details;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param vector
	 */
	public void setDetails(Vector vector) {
		details = vector;
	}

	/**
	 * @return
	 */
	public long getTimeFrom() {
		return timeFrom;
	}

	/**
	 * @return
	 */
	public long getTimeTo() {
		return timeTo;
	}

	/**
	 * @param l
	 */
	public void setTimeFrom(long l) {
		timeFrom = l;
	}

	public void setTimeFrom(int hour, int minutes) {
		setTimeFrom((hour * 60L * 60L) + (minutes * 60L));
	}
	
	public void setTimeTo(int hour, int minutes) {
		setTimeTo((hour * 60L * 60L) + (minutes * 60L));
	}
		
	/**
	 * @param l
	 */
	public void setTimeTo(long l) {
		timeTo = l;
	}
	
	public Double getValueOf(int dayOfWeek) {
		Iterator details = getDetails().iterator();
		ShiftDetail aShiftDetail;
		while(details.hasNext()) {
			aShiftDetail = (ShiftDetail)details.next();
			if(aShiftDetail.getDay() == dayOfWeek) {
				return aShiftDetail.getValue();
			}
		}
		return null;
	}

	private int getHour(long milliseconds) {
		return (int)(milliseconds / 60L / 60L);
	}

	private int getMinutes(long milliseconds) {
		long hour = getHour(milliseconds) * 60L * 60L;
		return (int)((milliseconds - hour) / 60L);
	}

	public int getTimeFromHour() {
		return getHour(getTimeFrom());
	}
	
	public int getTimeFromMinutes() {
		return getMinutes(getTimeFrom());
	}
	
	public int getTimeToHour() {
		return getHour(getTimeTo());
	}
	
	public int getTimeToMinutes() {
		return getMinutes(getTimeTo());
	}

	/**
	 * @return Returns the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
