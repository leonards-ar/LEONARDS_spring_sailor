/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Reservation.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Reservation extends PersistentObject implements Serializable {
	private ReferenceData state = null;
	private Integer stateId = null;
	private Date stateDate = null;
	private Double value = null;
	private Integer userId = null;
	private User user = null;
	private ReservationType type = null;
	private Integer typeId = null;
	private Integer BoatId = null;
	private Boat boat = null;
	private Vector shifts = null;
	private Date from = null;
	private Date to = null;
	private Vector contacts = null;
	
	/**
	 * 
	 */
	public Reservation() {
		super();
	}

	/**
	 * @param id
	 */
	public Reservation(Object id) {
		super(id);
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#update()
	 */
	protected void update() throws NestedException {
		refreshValue();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		try {
			return getStateId().equals(StaticDataManager.getParameter(Parameter.ACTIVE_RESERVATION_STATE_ID).getValueAsInteger());
		} catch(Throwable ex) {
			return false;
		}
	}
	
	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#insert()
	 */
	protected void insert() throws NestedException {
		refreshValue();
		DBHome.insert(this);
	}

	public Double getValueAsNegative() {
		double value = getValue() != null ? getValue().doubleValue() : 0.0;
		return new Double(-value);	
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
	public Date getFrom() {
		if( from == null ) {
			try {
				ReservationShift firstShift = (ReservationShift)getShifts().firstElement();
				from = firstShift.getFrom();
			} catch( Throwable ex ) {
				from = null;
			}
		}
		return from;
	}

	/**
	 * @return
	 */
	public Date getTo() {
		if( to == null ) {
			try {
				ReservationShift lastShift = (ReservationShift)getShifts().lastElement();
				to = lastShift.getTo();
			} catch( Throwable ex ) {
				to = null;
			}
		}
		return to;
	}

	/**
	 * @return
	 */
	public User getUser() {
		if(user == null) {
			try {
				user = DBHome.retrieveUser(getUserId());
			} catch( Exception ex ) {
				user = null;
			}
		}
		return user;
	}

	/**
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param integer
	 */
	public void setUserId(Integer integer) {
		userId = integer;
	}

	/**
	 * @param double1
	 */
	public void setValue(Double double1) {
		value = double1;
	}

	public void refreshValue() {
		Iterator shifts = getShifts().iterator();
		ReservationShift aShift;
		double value = getType() != null ? getType().getExtraValue().doubleValue() : 0.0;
		while(shifts.hasNext()) {
			aShift = (ReservationShift)shifts.next();
			value += aShift.getValue() != null  ? aShift.getValue().doubleValue() : 0.0;
		}
		setValue(new Double(value));
	}

	/**
	 * @return
	 */
	public ReferenceData getState() {
		if(state == null) {
			try {
				state = StaticDataManager.getReservationState(getStateId());
			} catch(Throwable ex) {
				state = null;
			}
			
		}
		return state;
	}

	/**
	 * @return
	 */
	public Date getStateDate() {
		return stateDate;
	}

	/**
	 * @return
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @return
	 */
	public ReservationType getType() {
		if(type == null) {
			try {
				type = StaticDataManager.getReservationType(getTypeId());
			} catch( Throwable ex ) {
				type = null;
			}
		}
		return type;
	}

	/**
	 * @return
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param data
	 */
	public void setState(ReferenceData data) {
		state = data;
	}

	/**
	 * @param date
	 */
	public void setStateDate(Date date) {
		stateDate = date;
	}

	/**
	 * @param integer
	 */
	public void setStateId(Integer integer) {
		stateId = integer;
	}

	/**
	 * @param type
	 */
	public void setType(ReservationType type) {
		this.type = type;
	}

	/**
	 * @param integer
	 */
	public void setTypeId(Integer integer) {
		typeId = integer;
	}

	/**
	 * @return
	 */
	public Boat getBoat() {
		if( boat == null ) {
			try {
				boat = DBHome.retrieveBoat(getBoatId());
			} catch( Throwable ex ) {
				boat = null;
			}
			
		}
		return boat;
	}

	/**
	 * @return
	 */
	public Integer getBoatId() {
		return BoatId;
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
		BoatId = integer;
	}

	/**
	 * @return
	 */
	public Vector getShifts() {
		if( shifts == null ) {
			try {
				shifts = DBHome.retrieveReservationShifts(getIdAsInteger());
			} catch(Throwable ex) {
				shifts = null;
			}
		}
		return shifts;
	}

	/**
	 * @param vector
	 */
	public void setShifts(Vector vector) {
		shifts = vector;
	}

	public void cancelByAdministrator() throws NestedException {
		Integer newStateId = StaticDataManager.getParameter(Parameter.ADMIN_CANCEL_RESERVATION_STATE_ID).getValueAsInteger();
		DBHome.cancelReservation(this,newStateId);
		setStateId(newStateId);
		setState(null);
	}

	public void cancelByUser() throws NestedException {
		Integer newStateId = StaticDataManager.getParameter(Parameter.USER_CANCEL_RESERVATION_STATE_ID).getValueAsInteger();
		DBHome.cancelReservation(this,newStateId);
		setStateId(newStateId);
		setState(null);
	}
		
	/**
	 * @return
	 */
	public Vector getContacts() {
		if(contacts == null) {
			try {
				setContacts(DBHome.retrieveReservationContacts(getIdAsInteger()));
			} catch(Throwable ex) {
				setContacts(null);
			}
			
		}
		return contacts;
	}

	/**
	 * @param vector
	 */
	public void setContacts(Vector vector) {
		contacts = vector;
	}

}
