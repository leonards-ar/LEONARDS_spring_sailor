/*
 * Created on Aug 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import leonards.common.base.NestedException;
import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.BoatTimeTableShift;
import leonards.spring.domain.Contact;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.SpringException;
import leonards.spring.domain.StaticDataManager;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReservationContactActionCommand extends SpringActionCommand {
	private Contact contactToSave = null;
	
	/**
	 * 
	 */
	public ReservationContactActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			BoatTimeTableShift timeTableShift = (BoatTimeTableShift)getFromSession("timeTableShift");
			timeTableShift.getReservation().setTypeId(getIntegerParameter("typeId"));
			timeTableShift.getReservation().setType(null);
			
			String action = getStringParameter("contactaction");
			if(action != null && action.equalsIgnoreCase("ADD") ) {
				doAddContact();
			} else if(action != null && action.equalsIgnoreCase("DELETE") ) {
				doDeleteContact();
			} else if(action != null && action.equalsIgnoreCase("SAVE_ADD") ) {
				doSaveAndAddContact();
			} else if(action != null && action.equalsIgnoreCase("DELETE_SELECTED") ) {
				doDeleteSelectedContact();
			}
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

	private void doAddContact() throws WebActionException, SpringException {
		int contactIndex = isValidIntegerParameter("contactIndex") ? getIntegerParameter("contactIndex").intValue() : -1;
		if( contactIndex >= 0 ) {
			if(getSelectedContacts().size() >= StaticDataManager.getParameter(Parameter.MAX_INVITED_CREW).getValueAsInt() ) {
				getErrors().addElement("Ya alcanzo la cantidad maxima de invitados");
				throw new WebActionFormValidationException(getErrors());
			}
			getSelectedContacts().addElement(getUser().getContacts().elementAt(contactIndex));
		}
	}

	private Vector getSelectedContacts() {
		return (Vector)getFromSession("selectedContacts");
	}

	private User getUser() {
		return (User)getFromSession("user");
	}

	private void doDeleteContact() throws WebActionException, SpringException, NestedException {
		int contactIndex = isValidIntegerParameter("contactIndex") ? getIntegerParameter("contactIndex").intValue() : -1;
		if( contactIndex >= 0 ) {
			Contact contactToDelete = (Contact)getUser().getContacts().elementAt(contactIndex);
			contactToDelete.delete();
			getUser().setContacts(null);
		}
	}

	private void doSaveAndAddContact() throws WebActionException, NestedException {
		buildContactToSave();
		validateForm();
		getContactToSave().save();
		getUser().setContacts(null);
		if(getSelectedContacts().size() >= StaticDataManager.getParameter(Parameter.MAX_INVITED_CREW).getValueAsInt() ) {
			getErrors().addElement("Ya alcanzo la cantidad maxima de invitados");
			throw new WebActionFormValidationException(getErrors());
		}		
		getSelectedContacts().addElement(getContactToSave());
		putInSession("contactToEdit", new Contact());
	}

	private Contact getContactToSave() throws WebActionException {
		if( contactToSave == null ) {
			contactToSave = (Contact)getFromSession("contactToEdit");
		}
		return contactToSave;
	}

	private void buildContactToSave() throws WebActionException {
		getContactToSave().setCompleteName(getStringParameter("contactName"));
		getContactToSave().setTelephone(getStringParameter("contactTelephone"));
		getContactToSave().getDocument().setDocumentType(null);
		getContactToSave().getDocument().setNumber(getStringParameter("contactDocument"));
		getContactToSave().getDocument().setDocumentTypeId(getIntegerParameter("contactDocumentTypeId"));
		getContactToSave().setUserId(getUser().getIdAsInteger());
	}
	
	private void doDeleteSelectedContact() throws WebActionException {
		int selectedContactIndex = isValidIntegerParameter("selectedcontactindex") ? getIntegerParameter("selectedcontactindex").intValue() : -1;
		if( selectedContactIndex >= 0 ) {
			getSelectedContacts().remove(selectedContactIndex);
		}
	}
	
	protected void validateForm() throws WebActionFormValidationException {
		if( !hasParameterValue("contactName") ) {
			getErrors().addElement("Debe escribir el nombre del acompañante");
		}		
		if( !hasParameterValue("contactTelephone") ) {
			getErrors().addElement("Debe escribir el telefono del acompañante");
		}		
		if( !hasParameterValue("contactDocument") ) {
			getErrors().addElement("Debe escribir el documento del acompañante");
		}		
		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}
		
	}	
}
