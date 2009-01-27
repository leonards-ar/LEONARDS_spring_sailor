/*
 * Created on Aug 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import leonards.common.sql.DBException;
import leonards.common.sql.DBResultSet;
import leonards.common.sql.DBStaticStatement;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportManager {
	private static Hashtable reports;
	
	/**
	 * 
	 */
	public ReportManager() {
		super();
	}
	
	public static DBResultSet executeReport(String reportName) throws DBException {
		DBStaticStatement sp = null;
		Report report = getResport(reportName); 
		try {
			sp = new DBStaticStatement(report.getSql(), report.getPoolName());
			return sp.executeQuery();
		} finally {
			if(sp != null) {
				try { sp.close(); } catch(Throwable ex) {}
			} 
		}
	}

	public static Report getResport(String reportName) {
		return (Report)getReports().get(reportName);
	}
	
	/**
	 * @return
	 */
	protected static Hashtable getReports() {
		if(reports == null) {
			setReports(new Hashtable());
			getReports().put("AUTH_USERS", new Report("Lista de Autorizados a Ingresar al Frana", "select concat(u.surname, ',', u.name) as 'Apellido y Nombre', u.telephone as 'Telefono', concat(dt.description, ' ', u.document) as 'Documento', u.email as 'e-mail'  from tbl_users as u, tbl_user_states as us, tbl_document_types as dt where u.permit != '' and u.permit_due_date > NOW() and  u.department != '' and u.state_id = us.state_id and us.can_reserve = 1 and u.document_type_id = dt.document_type_id order by u.surname, u.name", DBHome.CONNECTION_NAME, "AUTH_USERS"));
		}
		return reports;
	}

	/**
	 * @param hashtable
	 */
	protected static void setReports(Hashtable hashtable) {
		reports = hashtable;
	}

	/**
	 * 
	 * @return
	 */	
	public static Vector reports() {
		Vector reports = new Vector();
		Enumeration keys = getReports().keys();
		while(keys.hasMoreElements()) {
			reports.addElement(getReports().get(keys.nextElement()));
		}
		return reports;
	}

}
