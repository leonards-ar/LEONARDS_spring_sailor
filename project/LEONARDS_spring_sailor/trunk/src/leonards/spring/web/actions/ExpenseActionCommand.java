/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.Movement;

/**
 * @author mcapurro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpenseActionCommand extends SpringActionCommand {

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			cleanSession();
			Movement movementToEdit = new Movement();
			putInSession("movementToEdit", movementToEdit);
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

}
