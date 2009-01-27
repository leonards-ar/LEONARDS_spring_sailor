/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.web.actions
 * File: LoginActionCommand.java
 *
 * Property of Leonards / Mindpool
 * Created on 22/06/2004
 */
package leonards.spring.web.actions;

import leonards.common.web.InvalidPasswordException;
import leonards.common.web.InvalidUsernameException;
import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.common.web.framework.WebActionInvalidSessionException;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class LoginActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public LoginActionCommand() {
		super();
	}

	/** 
	 * @throws WebActionException
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			validateForm();
			User user = new User(getStringParameter("username"), getStringParameter("password"));
			try {
				user.logon();
			} catch(InvalidUsernameException ex) {
				getErrors().addElement("Combinacion usuario/contraseña invalida.");
				throw new WebActionFormValidationException(getErrors());			
			} catch(InvalidPasswordException ex) {
				getErrors().addElement("Combinacion usuario/contraseña invalida.");
				throw new WebActionFormValidationException(getErrors());			
			}
			putInSession("user", user);
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}
	
	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("username") ) {
			getErrors().addElement("Debe escribir su nombre de usuario");
		}
		if( !hasParameterValue("password") ) {
			getErrors().addElement("Debe escribir su contraseña");
		}
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#checkSession()
	 */
	public void checkSession() throws WebActionInvalidSessionException {
		// Do nothing as no session must
		// be checked as this point
	}

}
