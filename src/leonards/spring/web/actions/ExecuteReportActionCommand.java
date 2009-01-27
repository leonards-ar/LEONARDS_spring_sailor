/*
 * Created on Aug 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import leonards.common.web.framework.WebActionException;
import leonards.spring.domain.ReportManager;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExecuteReportActionCommand extends SpringActionCommand {

	/**
	 * 
	 */
	public ExecuteReportActionCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#execute()
	 */
	public void execute() throws WebActionException {
		try {
			String name = getStringParameter("reportname");
			leonards.spring.domain.Report springReport = ReportManager.getResport(name);
			leonards.common.report.Report report = new leonards.common.report.Report(ReportManager.executeReport(name));
			report.setName(springReport.getName());
			putInSession("report", report);
		} catch( Throwable ex ) {
			throw new WebActionException("El sistema no se encuentra disponible.", ex);
		}		
	}

}
