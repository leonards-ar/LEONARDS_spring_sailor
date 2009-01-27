/*
 * Created on Jul 14, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveMyProfileActionCommand extends SpringActionCommand {
	private User userToSave = null;

	/**
	 * 
	 */
	public SaveMyProfileActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		
		try {
			buildUserToSave();
			validateForm();
			getUserToSave().save();
			putInSession("user", getUserToSave());
			setSuccessMessage("Sus datos han sido modificado con exito");
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private void buildUserToSave() throws WebActionException {
		getUserToSave().setAddress(getStringParameter("address"));
		getUserToSave().setCelPhone(getStringParameter("celphone"));
		getUserToSave().setEmail(getStringParameter("email"));
		getUserToSave().setTelephone(getStringParameter("telephone"));
	}
	
	/**
	 * @return
	 */
	public User getUserToSave() {
		if( userToSave == null ) {
			userToSave = (User)getFromSession("userToEdit");
		}
		return userToSave;
	}

	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("email") ) {
			getErrors().addElement("Debe escribir su e-mail");
		}		
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}


}
