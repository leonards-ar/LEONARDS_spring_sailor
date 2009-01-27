/*
 * Created on Jul 18, 2004
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
public class CancelMessageEditionActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public CancelMessageEditionActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			updateResponsePages();
			removeFromSession("messageToEdit");
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private void updateResponsePages() {
		if( hasParameterValue("successPage") ) {
			setSuccessPage(getStringParameter("successPage"));
		}
		if( hasParameterValue("validationErrorPage") ) {
			setValidationErrorPage(getStringParameter("validationErrorPage"));
		}
	}
}
