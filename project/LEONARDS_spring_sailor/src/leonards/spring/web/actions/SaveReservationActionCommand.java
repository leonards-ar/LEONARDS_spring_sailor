/*
 * Created on Aug 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.*;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveReservationActionCommand extends SpringActionCommand {
	private Reservation reservationToSave = null;
	
	/**
	 * 
	 */
	public SaveReservationActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			if(transactionInProgress()) {
				return;	
			}
			
			buildReservationToSave();
			checkReserverBalance();
			getReservationToSave().save();

			removeFromSession("timeTableShift");
			removeFromSession("freeShifts");
			removeFromSession("contactToEdit");
			removeFromSession("selectedContacts");
			removeFromSession("dayIndex");
			removeFromSession("shiftIndex");

			getReserver().refreshBalance();

			putInSession("reservation", getReservationToSave());
			
			if( !getReserver().getProfile().isSpecialReserver() ) {
				// Do not open print document dialog for
				// special users
				putInSession("newReservation", "yes");
			}
			
			setSuccessMessage("Su reserva ha sido guardada con exito");
			
		} catch( ReservationAlreadyExistsException ex ) {
			getErrors().addElement("Se ha ocupado el turno de su reserva en curso");
			throw new WebActionFormValidationException(getErrors());
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible", ex);
		} finally {
			try {
				// Refresh Time Table
				BoatTimeTable timeTable = new BoatTimeTable();
				timeTable.setBoatId(StaticDataManager.getParameter(Parameter.DEFAULT_BOAT_ID).getValueAsInteger());
				putInSession("boatTimeTable", timeTable );
			} catch( Throwable ex ) {
				throw new WebActionException("El sistema no se encuentra disponible", ex);
			}
			endTransactionInProgress();
		}
	}
	
	private synchronized boolean transactionInProgress() {
		Boolean isTrxInProgress = (Boolean)getFromSession("trxInProgress");
		if( isTrxInProgress == null || !isTrxInProgress.booleanValue() ) {
			putInSession("trxInProgress", Boolean.TRUE);
			return false;
		} else {
			return true;
		}
	}

	private synchronized void endTransactionInProgress() {
		removeFromSession("trxInProgress");	
	}
	private User getReserver() {
		return (User)getFromSession("reserver");
	}
	
	private void checkReserverBalance() throws WebActionFormValidationException {
		try {
			if( getReserver().getProfile().isSpecialReserver() ) {
				return;
			}
			
			getReservationToSave().refreshValue();
			if( getReserver().getBalance().doubleValue() - getReservationToSave().getValue().doubleValue() < (-1) * StaticDataManager.getParameter(Parameter.MAX_CREDIT_LIMIT).getValueAsInt() ) {
				getErrors().addElement("No posee suficiente saldo para realizar la reserva");
				throw new WebActionFormValidationException(getErrors());
			}
		} catch(WebActionFormValidationException ex) {
			throw ex;
		} catch(Throwable ex) {
			getErrors().addElement("No se pudo verificar su saldo");
			throw new WebActionFormValidationException(getErrors());
		}		
	}
	
	private void buildReservationToSave() {
		getReservationToSave().setTypeId(getIntegerParameter("typeId"));
		getReservationToSave().setType(null);
		getReservationToSave().setShifts(getReservationShifts());
		getReservationToSave().setContacts((Vector)getFromSession("selectedContacts"));
		getReservationToSave().setUserId(getReserver().getIdAsInteger());
		getReservationToSave().setUser(getReserver());
	}
	
	private BoatTimeTableShift getTimeTableFirstShift() {
		return (BoatTimeTableShift)getFromSession("timeTableShift");
	}
	
	private Reservation getReservationToSave() {
		if(reservationToSave == null) {
			reservationToSave = getTimeTableFirstShift().getReservation();
		}
		return reservationToSave;
	}
	
	private int getSelectedDayIndex() {
		Integer index = (Integer)getFromSession("dayIndex");
		return index != null ? index.intValue() : -1;
	}
	
	private int getSelectedShiftIndex() {
		Integer index = (Integer)getFromSession("shiftIndex");
		return index != null ? index.intValue() : -1;
	}

	private BoatTimeTable getTimeTable() {
		return (BoatTimeTable)getFromSession("boatTimeTable");
	}
	
	private Vector getReservationShifts() {
		int shiftsQty = getReservationToSave().getType().getShiftsQty().intValue();
		int currentShifts = 0;
		Vector shifts = new Vector();
		ReservationShift aReservationShift;
		BoatTimeTableDay aDay;
		BoatTimeTableShift aShift;
		
		for( int i = getSelectedDayIndex(); currentShifts < shiftsQty && i < getTimeTable().getDays().size(); i++) {
			aDay = (BoatTimeTableDay)getTimeTable().getDays().elementAt(i);
			for( int j = ( i == getSelectedDayIndex() ? getSelectedShiftIndex() : 0); currentShifts < shiftsQty && j < aDay.getShifts().size(); j++) {
				aShift = (BoatTimeTableShift)aDay.getShifts().elementAt(j);
				aReservationShift = new ReservationShift();
				aReservationShift.setDate(aShift.getDay());
				aReservationShift.setReservationId(getReservationToSave().getIdAsInteger());
				aReservationShift.setShift(aShift.getShift());
				aReservationShift.setShiftId(aShift.getShift().getIdAsInteger());
				shifts.addElement(aReservationShift);

				currentShifts++;

			}
		}
		return shifts;
	}	
}
