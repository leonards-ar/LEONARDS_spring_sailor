/*
 * Created on Jul 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteUserActionCommand extends SpringActionCommand {

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			User userToDelete = (User)((Vector)getFromSession("users")).elementAt(getIntegerParameter("userIndex").intValue());
			User user = (User)getFromSession("user");
			if( user != null && !user.equals(userToDelete) ) {
				userToDelete.delete();
				// Refresh users search
				putInSession("users", DBHome.searchUsers());
			} else {
				getErrors().addElement("No puede eliminarse a si mismo");
				throw new WebActionFormValidationException(getErrors());
			}
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

}
