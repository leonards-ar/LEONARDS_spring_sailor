/*
 * Created on Aug 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.BoatTimeTable;
import leonards.spring.domain.BoatTimeTableDay;
import leonards.spring.domain.BoatTimeTableShift;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.StaticDataManager;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserCancelReservationActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public UserCancelReservationActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			getTimeTableShift().getReservation().cancelByUser();
			BoatTimeTable timeTable = new BoatTimeTable();
			timeTable.setBoatId(StaticDataManager.getParameter(Parameter.DEFAULT_BOAT_ID).getValueAsInteger());
			putInSession("boatTimeTable", timeTable );
			getReserver().refreshBalance();
			setSuccessMessage("Su reserva ha sido cancelada con exito");
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	private User getReserver() {
		return (User)getFromSession("reserver");
	}

	private BoatTimeTable getTimeTable() {
		return (BoatTimeTable)getFromSession("boatTimeTable");
	}
	
	private BoatTimeTableDay getTimeTableDay() {
		return (BoatTimeTableDay)getTimeTable().getDays().elementAt(getIntegerParameter("dayIndex").intValue());
	}
	
	private BoatTimeTableShift getTimeTableShift() {
		return (BoatTimeTableShift)getTimeTableDay().getShifts().elementAt(getIntegerParameter("shiftIndex").intValue());
	}
}
