/*
 * Created on Jul 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.Movement;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.StaticDataManager;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveMovementActionCommand extends SpringActionCommand {
	private Movement movementToSave = null;

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			updateResponsePages();
			buildMovementToSave();
			validateForm();
			getMovementToSave().save();
			
			// Update User Balance
			checkAndRefreshUser();
			
			// Update Movement
			removeFromSession("movementToEdit");
			Movement movementToEdit = new Movement();
			movementToEdit.setMovementTypeId(StaticDataManager.getParameter(Parameter.RESERVATION_MOVEMENT).getValueAsInteger());
			putInSession("movementToEdit", movementToEdit);

			setSuccessMessage("El movimiento ha sido guardado con exito");
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}		

	private void checkAndRefreshUser() {
		User user = (User)getFromSession("user");
		Integer movementUserId = getMovementToSave().getUserId();
		if( movementUserId != null && movementUserId.equals(user.getIdAsInteger()) ) {
			try {
				user.refreshBalance();
			} catch(Throwable ex) {
				// Do nothing, just leave balance unchanged
			}
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
	
	private void buildMovementToSave() throws WebActionException {
		getMovementToSave().setMovementTypeId(getIntegerParameter("movementTypeId"));
		getMovementToSave().setDocumentData(getStringParameter("document_data"));
		getMovementToSave().setComments(getStringParameter("comments"));
		if( isValidIntegerParameter("userId") ) {
			getMovementToSave().setUserId(getIntegerParameter("userId"));
		}
		if( isValidDoubleParameter("amount") ) {
			getMovementToSave().setAmount(getDoubleParameter("amount"));
		}
		
	}
	
	private Movement getMovementToSave() {
		if( movementToSave == null ) {
			movementToSave = (Movement)getFromSession("movementToEdit");
		}
		return movementToSave;
	}
	
	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("userId") ) {
			getErrors().addElement("Debe seleccionar el usuario");
		}
		if( !hasParameterValue("amount") ) {
			getErrors().addElement("Debe escribir la cantidad");
		}
		if( hasParameterValue("amount") && !isValidDoubleParameter("amount")) {
			getErrors().addElement("La cantidad debe ser un numero v�lido");
		}
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}	

}
