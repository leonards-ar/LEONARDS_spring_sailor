/*
 * Created on Aug 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Date;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.ReservationSearchFilter;
import leonards.spring.domain.StaticDataManager;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AdministrationReservationsActionCommand
	extends SpringActionCommand {

	/**
	 * 
	 */
	public AdministrationReservationsActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			cleanSession();
			putInSession("boat", DBHome.retrieveBoat(StaticDataManager.getParameter(Parameter.DEFAULT_BOAT_ID).getValueAsInteger()));
			ReservationSearchFilter filter = getFilter();
			putInSession("reservations", DBHome.searchReservations(filter));
			putInSession("reservationFilter", filter);
			putInSession("shifts", DBHome.retrieveShifts());
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	private ReservationSearchFilter getFilter() {
		ReservationSearchFilter filter = new ReservationSearchFilter();
		filter.setDateFrom(getDefaultFromDate());
		filter.setDateTo(getDefaultToDate());
		return filter;
	}
	
	private long getDeltaInMilliseconds() {
		int deltaDays;
		try {
			deltaDays = StaticDataManager.getParameter(Parameter.BOAT_TIME_TABLE_DAYS).getValueAsInt();
		} catch(Throwable ex) {
			deltaDays = 15;
		}
		return deltaDays * (24L * 60 * 60 * 1000);
	}
	
	private long getNowMilliseconds() {
		return new Date().getTime();
	}
	
	private Date getDefaultFromDate() {
		return new Date(getNowMilliseconds() - getDeltaInMilliseconds());
	}

	private Date getDefaultToDate() {
		return new Date(getNowMilliseconds() + getDeltaInMilliseconds());
	}

}
