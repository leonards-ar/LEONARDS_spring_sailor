/*
 * Created on Jul 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateUserActionCommand extends SpringActionCommand {

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			User userToEdit = (User)((User)((Vector)getFromSession("users")).elementAt(getIntegerParameter("userIndex").intValue())).clone();
			putInSession("userToEdit", userToEdit);
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

}
