/*
 * Created on Jul 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Iterator;
import java.util.Vector;

import leonards.common.web.framework.*;
import leonards.spring.domain.*;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StartReservationActionCommand extends SpringActionCommand {
	private BoatTimeTable timeTable = null;
	
	/**
	 * 
	 */
	public StartReservationActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			userCanReserve();
			BoatTimeTableShift newShift = (BoatTimeTableShift)getTimeTableShift().clone();
			newShift.setReservation(getNewReservation());
			putInSession("timeTableShift", newShift);
			putInSession("freeShifts", getFollowingFreeShifts());
			putInSession("contactToEdit", new Contact());
			putInSession("selectedContacts", new Vector());
			putInSession("dayIndex", getIntegerParameter("dayIndex"));
			putInSession("shiftIndex", getIntegerParameter("shiftIndex"));
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private Integer getFollowingFreeShifts() {
		int freeDays = 0;
		int maxShifts = getReservationTypeMaxShifts();
		boolean notAvailable = false;
		
		BoatTimeTableDay aDay;
		BoatTimeTableShift aShift;
		for( int i = getIntegerParameter("dayIndex").intValue(); !notAvailable && freeDays < maxShifts && i < getTimeTable().getDays().size(); i++) {
			aDay = (BoatTimeTableDay)getTimeTable().getDays().elementAt(i);
			for( int j = ( i == getIntegerParameter("dayIndex").intValue() ? getIntegerParameter("shiftIndex").intValue() : 0); !notAvailable &&  freeDays < maxShifts && j < aDay.getShifts().size(); j++) {
				aShift = (BoatTimeTableShift)aDay.getShifts().elementAt(j);
				if(aShift.isReserved() || !aDay.isBoatAvailable() ) {
					notAvailable = true;
				} else {
					freeDays++;
				}
			}
		}
		if(!notAvailable && freeDays < maxShifts) {
			freeDays = maxShifts;
		}
		return new Integer(freeDays);
	}

	private int getReservationTypeMaxShifts() {
		int max = 0;
		try {
			Iterator types = StaticDataManager.getReservationTypes().iterator();
			ReservationType aType;
			while(types.hasNext()) {
				aType = (ReservationType)types.next();
				if(max < aType.getShiftsQty().intValue()) {
					max = aType.getShiftsQty().intValue();
				}
			}
		} catch(Throwable ex) {
		}
		
		return max;	
	}
	
	private Reservation getNewReservation() throws SpringException {
		Reservation reservation = new Reservation();
		reservation.setBoatId(getTimeTable().getBoatId());
		reservation.setBoat(getTimeTable().getBoat());
		reservation.setStateId(StaticDataManager.getParameter(Parameter.ACTIVE_RESERVATION_STATE_ID).getValueAsInteger());
		reservation.setUserId(getReserver().getIdAsInteger());
		reservation.setUser(getReserver());
		reservation.setValue(getTimeTableShift().getValue());
		return reservation;
	}

	private User getReserver() {
		return (User)getFromSession("reserver");
	}

	private BoatTimeTable getTimeTable() {
		if(timeTable == null) {
			timeTable = (BoatTimeTable)getFromSession("boatTimeTable");
		}
		return timeTable;
	}
	
	private BoatTimeTableDay getTimeTableDay() {
		return (BoatTimeTableDay)getTimeTable().getDays().elementAt(getIntegerParameter("dayIndex").intValue());
	}
	
	private BoatTimeTableShift getTimeTableShift() {
		return (BoatTimeTableShift)getTimeTableDay().getShifts().elementAt(getIntegerParameter("shiftIndex").intValue());
	}
	
	protected void userCanReserve()throws WebActionFormValidationException {
		
		if( !getReserver().getState().isEnabled() ) {
			getErrors().addElement("No esta habilitado para reservar. Su estado actual es " + getReserver().getState().getDescription() );
		} else {
			if( getReserver().getProfile().isSpecialReserver() ) {
				// Nothing else to check for this users
				return;			
			}
			if(!getReserver().getPermit().hasNumber()) {
				getErrors().addElement("No tiene asociada su habilitacion");
			}
			if( getReserver().getPermit().getDueDate() == null ) {
				getErrors().addElement("Su habilitacion no tiene cargada la fecha de vencimiento");
			}
			if( getReserver().getPermit().getDueDate() != null && getReserver().getPermit().hasNumber() && getReserver().getPermit().isDue() ) {
				getErrors().addElement("Su habilitacion ha vencido el dia " + WebFrameworkToolkit.getHtmlShortDate(getReserver().getPermit().getDueDate()));
			}
			if( getReserver().getDepartment() == null ||  getReserver().getDepartment().indexOf("ITBA") < 0 ) {
				getErrors().addElement("No pertenece a ninguna asociacion habilitada para reservar el velero");
			}
			
			try {
				if( DBHome.searchActiveReservations(getReserver()).size() >= StaticDataManager.getParameter(Parameter.MAX_ACTIVE_RESERVATIONS).getValueAsInt() ) {
					getErrors().addElement("Ya a alcanzado el limite de reservas activas en simultaneo");
				}
			} catch(Throwable ex) {
				getErrors().addElement("No se pudo verificar la cantidad de reservas activas");
			}
		}
		
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}
}
