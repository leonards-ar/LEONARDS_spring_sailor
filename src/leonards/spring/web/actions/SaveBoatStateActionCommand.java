/*
 * Created on Jul 7, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.Boat;
import leonards.spring.domain.DBHome;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveBoatStateActionCommand extends SpringActionCommand {
	private Boat boatToSave = null;
	
	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			buildBoatToSave();
			validateForm();
			getBoatToSave().save();
			removeFromSession("boatToEdit");
			// Refresh users search
			putInSession("boats", DBHome.searchBoats());
			setSuccessMessage("El estado del barco ha sido modificado con exito");
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private void buildBoatToSave() throws WebActionException {
		getBoatToSave().setStateId(getIntegerParameter("stateId"));
		getBoatToSave().setStateDateFrom(getDateParameter("date_from"));
		getBoatToSave().setStateDateTo(getDateParameter("date_to"));
		getBoatToSave().setStateComments(getStringParameter("comments"));
	}
	
	private Boat getBoatToSave() {
		if( boatToSave == null ) {
			boatToSave = (Boat)getFromSession("boatToEdit");
		}
		return boatToSave;
	}
	
	protected void validateForm() throws WebActionFormValidationException {
		if( hasParameterValue("date_from") && !isValidDateParameter("date_from")) {
			getErrors().addElement("La fecha de comienzo del estado es invalida");
		}
		if( hasParameterValue("date_to") && !isValidDateParameter("date_to")) {
			getErrors().addElement("La fecha de comienzo del estado es invalida");
		}
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}

}
