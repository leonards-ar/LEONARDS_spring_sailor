/*
 * Created on Jul 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.Message;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveMessageActionCommand extends SpringActionCommand {
	private Message messageToSave = null;
	
	/**
	 * 
	 */
	public SaveMessageActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			updateResponsePages();
			buildMessageToSave();
			validateForm();
			getMessageToSave().save();
			removeFromSession("messageToEdit");
			// Refresh messages search
			// :TODO: Replace with constant
			putInSession("messages", DBHome.searchMessages(500));
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("message") ) {
			getErrors().addElement("Debe escribir el mensaje");
		}		
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}	

	private void buildMessageToSave() throws WebActionException {
		getMessageToSave().setMessage(hasParameterValue("message") ? getStringParameter("message").trim() : "");
	}
	
	public Message getMessageToSave() {
		if( messageToSave == null ) {
			messageToSave = (Message)getFromSession("messageToEdit");
		}
		return messageToSave;
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
