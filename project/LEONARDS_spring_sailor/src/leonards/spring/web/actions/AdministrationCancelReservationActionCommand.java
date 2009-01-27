/*
 * Created on Aug 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.Reservation;
import leonards.spring.domain.ReservationSearchFilter;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AdministrationCancelReservationActionCommand
	extends SpringActionCommand {

	/**
	 * 
	 */
	public AdministrationCancelReservationActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			getReservationToCancel().cancelByAdministrator();
			putInSession("reservations", DBHome.searchReservations(getFilter()));
			setSuccessMessage("La reserva ha sido cancelada con exito");
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	private ReservationSearchFilter getFilter() {
		return (ReservationSearchFilter)getFromSession("reservationFilter");
	}
	
	private Vector getReservations() {
		return (Vector)getFromSession("reservations");
	}
	
	private Reservation getReservationToCancel() {
		return (Reservation)getReservations().elementAt(getIntegerParameter("reservationIndex").intValue());
	}

}
