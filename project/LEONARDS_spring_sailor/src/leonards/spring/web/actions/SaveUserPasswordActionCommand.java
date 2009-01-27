/*
 * Created on Jul 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.User;

/**
 * @author mcapurro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveUserPasswordActionCommand extends SpringActionCommand {

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		
		try {
			validateForm();
			User userToEdit = (User)getFromSession("userToEdit");
			userToEdit.setPassword(getStringParameter("password"));
			userToEdit.updatePassword();
			removeFromSession("userToEdit");
			setSuccessMessage("La contraseña ha sido modificada con exito");
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
		
	}

	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("password") ) {
			getErrors().addElement("Debe escribir la nueva contraseña");
		}
		if( !hasParameterValue("passwordConfirmation") ) {
			getErrors().addElement("Debe escribir la confirmacion de la nueva contraseña");
		}
		if( hasParameterValue("passwordConfirmation") && hasParameterValue("password") && !getStringParameter("password").equals(getStringParameter("passwordConfirmation"))) {
			getErrors().addElement("La nueva contraseña no coincide con su confirmacion");
		}
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}
}
