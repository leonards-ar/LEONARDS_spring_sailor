/*
 * Created on Aug 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CancelReservationActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public CancelReservationActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			removeFromSession("timeTableShift");
			removeFromSession("freeShifts");
			removeFromSession("contactToEdit");
			removeFromSession("selectedContacts");
			removeFromSession("dayIndex");
			removeFromSession("shiftIndex");
			
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}		
	}

}
