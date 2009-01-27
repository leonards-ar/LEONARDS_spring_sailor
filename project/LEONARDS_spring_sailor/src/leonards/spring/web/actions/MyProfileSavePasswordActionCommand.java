/*
 * Created on Jul 18, 2004
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
public class MyProfileSavePasswordActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public MyProfileSavePasswordActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			validateForm();
			getUser().setPassword(getStringParameter("password"));
			getUser().updatePassword();
			setSuccessMessage("Su contraseña ha sido modificada con exito");
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("currentpassword") ) {
			getErrors().addElement("Debe escribir su contraseña actual");	
		}

		if( !hasParameterValue("password") ) {
			getErrors().addElement("Debe escribir su nueva contraseña");	
		}

		if( !hasParameterValue("passwordConfirmation") ) {
			getErrors().addElement("Debe escribir la confirmacion de su nueva contraseña");	
		}

		if( hasParameterValue("currentpassword") ) {
			User userToCheck = new User(getUser().getUsername(), getStringParameter("currentpassword"));
			try {
				userToCheck.logon();
			} catch( Exception ex ) {
				getErrors().addElement("La contraseña actual es incorrecta");
			}		
		}

		if( hasParameterValue("password") && hasParameterValue("passwordConfirmation") && !getStringParameter("password").equals(getStringParameter("passwordConfirmation")) ) {
			getErrors().addElement("Su nueva contraseña no coincide con la confirmacion");
		}		

		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
	}
	

	protected User getUser() {
		return (User)getFromSession("user");
	}
}
