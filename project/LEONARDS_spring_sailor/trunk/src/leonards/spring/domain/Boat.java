/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Boat.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Boat extends PersistentObject implements Serializable {
	private String name = null;
	private Integer stateId = null;
	private BoatState state = null;
	private Date stateDateFrom = null;
	private Date stateDateTo = null;
	private String stateComments = null;

	/**
	 * 
	 */
	public Boat() {
		super();
	}

	/**
	 * @param id
	 */
	public Boat(Object id) {
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
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public BoatState getState() {
		if( state == null ) {
			try {
				setState(StaticDataManager.getBoatState(getStateId()));
			} catch(Throwable ex) {
				setState(null);
			}
		}
		return state;
	}

	/**
	 * @return
	 */
	public String getStateComments() {
		return stateComments;
	}

	/**
	 * @return
	 */
	public Date getStateDateFrom() {
		return stateDateFrom;
	}

	/**
	 * @return
	 */
	public Date getStateDateTo() {
		return stateDateTo;
	}

	/**
	 * @return
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param state
	 */
	public void setState(BoatState state) {
		this.state = state;
	}

	/**
	 * @param string
	 */
	public void setStateComments(String string) {
		stateComments = string;
	}

	/**
	 * @param date
	 */
	public void setStateDateFrom(Date date) {
		stateDateFrom = date;
	}

	/**
	 * @param date
	 */
	public void setStateDateTo(Date date) {
		stateDateTo = date;
	}

	/**
	 * @param integer
	 */
	public void setStateId(Integer integer) {
		if(stateId != null && !stateId.equals(integer)) {
			setState(null);
		}
		stateId = integer;
	}

	/* (non-Javadoc)
	 * @see leonards.common.base.PersistentObject#save()
	 */
	public void save() throws NestedException {
		// If boats are enable then no date limits are
		// required. Cancel existing reservations
		if( getState() != null && getState().canReserve() ) {
			setStateDateFrom(null);
			setStateDateTo(null);
		} else if( getState() != null && !getState().canReserve() ) {
			ReservationSearchFilter filter = new ReservationSearchFilter();
			filter.setDateFrom(getStateDateFrom());
			filter.setDateTo(getStateDateTo());
			filter.setStateId(StaticDataManager.getParameter(Parameter.ACTIVE_RESERVATION_STATE_ID).getValueAsInteger());
			Iterator reservations = DBHome.searchReservations(filter).iterator();
			Reservation aReservation;
			while( reservations.hasNext() ) {
				aReservation = (Reservation)reservations.next();
				aReservation.cancelByAdministrator();
			}
		}
		
		super.save();
	}

}
