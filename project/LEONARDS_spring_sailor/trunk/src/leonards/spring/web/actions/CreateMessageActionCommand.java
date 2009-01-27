/*
 * Created on Jul 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Date;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.Message;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateMessageActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public CreateMessageActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			Message messageToEdit = new Message();
			messageToEdit.setUserId(((User)getFromSession("user")).getIdAsInteger());
			messageToEdit.setDate(new Date());
			putInSession("messageToEdit", messageToEdit);
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}		
	}
}
