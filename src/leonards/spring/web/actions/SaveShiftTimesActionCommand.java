/*
 * Created on Aug 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Vector;

import sun.security.action.GetBooleanAction;

import leonards.common.util.StringUtils;
import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.Shift;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveShiftTimesActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public SaveShiftTimesActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			Vector shifts = (Vector)getFromSession("shifts");
			Shift aShift;
			for(int i=0; shifts != null && i < shifts.size(); i++) {
				aShift = (Shift)shifts.elementAt(i);
				aShift.setTimeFrom(getIntegerParameter("fromHour_" + i).intValue(), getIntegerParameter("fromMinute_" + i).intValue());
				aShift.setTimeTo(getIntegerParameter("toHour_" + i).intValue(), getIntegerParameter("toMinute_" + i).intValue());
				aShift.setEnabled(StringUtils.hasValue(getStringParameter("enabled_" + i)));
				aShift.save();
			}
			setSuccessMessage("Los turnos han sido modificados con exito");
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}
	}

}
