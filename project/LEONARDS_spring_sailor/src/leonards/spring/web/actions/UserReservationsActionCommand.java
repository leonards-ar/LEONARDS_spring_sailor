/*
 * Created on Jul 24, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.BoatTimeTable;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.StaticDataManager;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserReservationsActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public UserReservationsActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			cleanSession();
			BoatTimeTable timeTable = new BoatTimeTable();
			timeTable.setBoatId(StaticDataManager.getParameter(Parameter.DEFAULT_BOAT_ID).getValueAsInteger());
			((User)getFromSession("user")).refreshBalance();
			putInSession("reserver", getFromSession("user"));
			putInSession("boatTimeTable", timeTable );
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
}
