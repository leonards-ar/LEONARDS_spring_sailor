/*
 * Created on Jul 7, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Enumeration;
import java.util.Hashtable;

import leonards.common.web.framework.WebActionException;
import leonards.common.web.framework.WebActionFormValidationException;
import leonards.spring.domain.Parameter;
import leonards.spring.domain.StaticDataManager;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveParametersActionCommand extends SpringActionCommand {

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		Hashtable params = getNonFrameworkRequestParameters();
		Enumeration paramNames = params.keys();
		String paramName;
		Parameter aParameter;
		while( paramNames.hasMoreElements() ) {
			paramName = paramNames.nextElement().toString();
			aParameter = new Parameter(paramName, params.get(paramName).toString());
			try {
				if( aParameter.isValidParameter() ) {
					aParameter.updateValue();
				}
			} catch( Throwable ex ) {
				getErrors().addElement("No se pudo guardar el parametro [" + aParameter.getName() + "].");
			}
		}
		
		StaticDataManager.reset();
		
		if( getErrors().size() > 0 ) {
			throw new WebActionFormValidationException(getErrors());
		} else {
			setSuccessMessage("Los parametros se han guardado con exito");
		}
	}

}
