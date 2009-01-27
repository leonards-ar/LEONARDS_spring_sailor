/*
 * Created on Jul 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.Message;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteMessageActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public DeleteMessageActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			Message messageToDelete = (Message)((Vector)getFromSession("messages")).elementAt(getIntegerParameter("messageIndex").intValue());
			messageToDelete.delete();
			// Refresh users search
			//:TODO: Replace with constant
			putInSession("messages", DBHome.searchMessages(500));
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

}
