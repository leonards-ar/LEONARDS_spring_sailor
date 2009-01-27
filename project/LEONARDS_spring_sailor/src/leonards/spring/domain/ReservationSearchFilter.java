/*
 * Created on Aug 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Date;

import leonards.common.sql.DBDynamicSqlGenerator;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReservationSearchFilter extends SearchFilter implements Serializable {
	private Date dateFrom = null;
	private Date dateTo = null;
	private Integer stateId = null;
	
	/**
	 * 
	 */
	public ReservationSearchFilter() {
		super();
	}
	

	/**
	 * @return
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @return
	 */
	public Date getDateTo() {
		return dateTo;
	}

	/**
	 * @param date
	 */
	public void setDateFrom(Date date) {
		dateFrom = date;
	}

	/**
	 * @param date
	 */
	public void setDateTo(Date date) {
		dateTo = date;
	}

	/* (non-Javadoc)
	 * @see leonards.spring.domain.SearchFilter#getSqlGenerator()
	 */
	public DBDynamicSqlGenerator getSqlGenerator() {
		DBDynamicSqlGenerator gen = new DBDynamicSqlGenerator();
		gen.addSelectElement("distinct r.reservation_id, r.*");
		gen.addTableElement("tbl_reservations as r, tbl_reservations_shifts as rs");
		gen.addWhereElement("rs.date >= ?", getDateFrom());
		gen.addWhereElement("date <= ?", getDateTo());
		gen.addWhereElement("r.reservation_state_id = ?", getStateId());
		gen.addConstantWhereElement("r.reservation_id = rs.reservation_id");		
		gen.addGroupByElement("r.reservation_id");
		gen.addOrderByElement("rs.date, r.reservation_id");
		return gen;
	}
	/**
	 * @return
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * @param integer
	 */
	public void setStateId(Integer integer) {
		stateId = integer;
	}

}
