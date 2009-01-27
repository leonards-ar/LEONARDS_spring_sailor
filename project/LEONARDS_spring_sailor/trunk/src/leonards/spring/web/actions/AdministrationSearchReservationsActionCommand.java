
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.ReservationSearchFilter;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AdministrationSearchReservationsActionCommand
	extends SpringActionCommand {

	/**
	 * 
	 */
	public AdministrationSearchReservationsActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			ReservationSearchFilter filter = getFilter();
			putInSession("reservations", DBHome.searchReservations(filter));
			putInSession("reservationFilter", filter);
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	private ReservationSearchFilter getFilter() throws WebActionException {
		ReservationSearchFilter filter = (ReservationSearchFilter)getFromSession("reservationFilter");
		filter.setDateFrom(getDateParameter("from"));
		filter.setDateTo(getDateParameter("to"));
		
		return filter;
	}

}
