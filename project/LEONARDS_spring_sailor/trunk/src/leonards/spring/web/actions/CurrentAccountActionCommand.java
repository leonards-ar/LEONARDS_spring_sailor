/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.MovementSearchFilter;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CurrentAccountActionCommand extends SpringActionCommand {
	MovementSearchFilter filter = null;
	
	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			cleanSession();
			buildSearchFilter();
			validateForm();
			putInSession("movementFilter", getFilter());
			putInSession("currentAccount", DBHome.searchCurrentAccount(getFilter()));
		} catch( WebActionException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}		
	}
	
	private MovementSearchFilter getFilter() {
		if( filter == null ) {
			filter  = getFromSession("movementFilter") != null ? (MovementSearchFilter)getFromSession("movementFilter") : new MovementSearchFilter();
		}
		return filter;
	}
	
	private void validateForm() throws WebActionFormValidationException, WebActionException {
		if( hasParameterValue("from") && !isValidDateParameter("from") ) {
			getErrors().addElement("La fecha desde es invalida");
		}
		
		if( hasParameterValue("to") && !isValidDateParameter("to") ) {
			getErrors().addElement("La fecha hasta es invalida");
		}

		if( hasParameterValue("from") && isValidDateParameter("from") && hasParameterValue("to") && isValidDateParameter("to") && getDateParameter("from").getTime() > getDateParameter("to").getTime() ) {
			getErrors().addElement("La fecha desde no puede ser posterior a la fecha hasta"); 
		}

		if( hasErrors() ) {
			throw new WebActionFormValidationException(getErrors());
		}		
	}
	
	private void buildSearchFilter() throws WebActionException {
		if( hasParameterValue("from") && isValidDateParameter("from") ) {
			getFilter().setFrom(getDateParameter("from"));
		} else {
			getFilter().setFrom(null);
		}
		if( hasParameterValue("to") && isValidDateParameter("to") ) {
			getFilter().setTo(getDateParameter("to"));
		} else {
			getFilter().setTo(null);
		}
	}
	

}
