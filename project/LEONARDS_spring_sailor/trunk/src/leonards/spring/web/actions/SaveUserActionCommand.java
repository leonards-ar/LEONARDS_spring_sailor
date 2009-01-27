/*
 * Created on Jul 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.User;
import leonards.spring.domain.UsernameAlreadyExistsException;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveUserActionCommand extends SpringActionCommand {
	private User userToSave = null;
	
	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		
		try {
			buildUserToSave();
			validateForm();
			getUserToSave().save();
			checkAndRefreshUser();
			removeFromSession("userToEdit");
			// Refresh users search
			putInSession("users", DBHome.searchUsers());
			setSuccessMessage("El usuario ha sido guardado con exito");
		} catch( UsernameAlreadyExistsException ex ) {
			getErrors().addElement("Ya existe el usuario '" + getUserToSave().getUsername() + "'");
			throw new WebActionFormValidationException(getErrors());
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private void checkAndRefreshUser() {
		User user = (User)getFromSession("user");
		if( user != null && user.equals(getUserToSave()) ) {
			refreshUser();
		}
	}

	private void buildUserToSave() throws WebActionException {
		getUserToSave().setAddress(getStringParameter("address"));
		getUserToSave().setCelPhone(getStringParameter("celphone"));
		getUserToSave().setDepartment(getStringParameter("department"));
		getUserToSave().setDocument(getIntegerParameter("documentTypeId"), getStringParameter("document"));
		getUserToSave().setDossier(getStringParameter("dossier"));
		getUserToSave().setEmail(getStringParameter("email"));
		getUserToSave().setName(getStringParameter("name"));
		if( !getUserToSave().isPersistent() ) {
			getUserToSave().setPassword(getStringParameter("password"));
			getUserToSave().setUsername(getStringParameter("username"));
		}
		getUserToSave().setPermit(getIntegerParameter("permitTypeId"), getStringParameter("permit"), isValidDateParameter("permit_due_date") ? getDateParameter("permit_due_date") : null);
		getUserToSave().setProfileId(getIntegerParameter("profileId"));
		getUserToSave().setStateId(getIntegerParameter("stateId"));
		getUserToSave().setSurname(getStringParameter("surname"));
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
		if( !getUserToSave().isPersistent() ) {
			if( !hasParameterValue("username") ) {
				getErrors().addElement("Debe escribir el nombre de usuario");
			}
			if( !hasParameterValue("password") ) {
				getErrors().addElement("Debe escribir la contraseña");
			}
			if( !hasParameterValue("passwordConfirmation") ) {
				getErrors().addElement("Debe escribir la confirmacion de la contraseña");
			}
			if( hasParameterValue("passwordConfirmation") && hasParameterValue("password") && !getStringParameter("password").equals(getStringParameter("passwordConfirmation"))) {
				getErrors().addElement("La nueva contraseña no coincide con su confirmacion");
			}			
		}
		if( !hasParameterValue("name") ) {
			getErrors().addElement("Debe escribir el nombre");
		}		
		if( !hasParameterValue("surname") ) {
			getErrors().addElement("Debe escribir el apellido");
		}
		if( !hasParameterValue("email") ) {
			getErrors().addElement("Debe escribir el e-mail");
		}		
		if( hasParameterValue("permit_due_date") && !isValidDateParameter("permit_due_date")) {
			getErrors().addElement("La fecha de vencimiento es invalida");
		}
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}

}
