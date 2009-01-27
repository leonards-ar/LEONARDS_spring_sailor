/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.util.Date;

import leonards.common.sql.DBDynamicSqlGenerator;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MovementSearchFilter extends SearchFilter {
	private Date from = null;
	private Date to = null;
	
	/**
	 * 
	 */
	public MovementSearchFilter() {
		super();
	}

	/* (non-Javadoc)
	 * @see leonards.spring.domain.SearchFilter#getSqlGenerator()
	 */
	public DBDynamicSqlGenerator getSqlGenerator() {
		DBDynamicSqlGenerator gen = new DBDynamicSqlGenerator();
		gen.addSelectElement("*");
		gen.addTableElement("tbl_movements");
		gen.addWhereElement("date >= ?", getFrom());
		gen.addWhereElement("date <= date_add(?, INTERVAL 1 DAY)", getTo());
		gen.addOrderByElement("date desc");
		if( !gen.hasWhereElements() ) {
			gen.addFinalElement("limit 1024");
		}
		return gen;
	}

	/**
	 * @return
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * @return
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * @param date
	 */
	public void setFrom(Date date) {
		from = date;
	}

	/**
	 * @param date
	 */
	public void setTo(Date date) {
		to = date;
	}

}
