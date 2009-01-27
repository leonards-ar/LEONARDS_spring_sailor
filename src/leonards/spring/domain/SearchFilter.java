/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import leonards.common.sql.DBDynamicSqlGenerator;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class SearchFilter {

	/**
	 * 
	 */
	public SearchFilter() {
		super();
	}
	
	public abstract DBDynamicSqlGenerator getSqlGenerator();

}
