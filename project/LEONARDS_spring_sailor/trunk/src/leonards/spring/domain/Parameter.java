/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Parameter.java
 *
 * Property of Leonards / Mindpool
 * Created on 22/06/2004
 */
package leonards.spring.domain;

import java.util.Vector;

import leonards.common.base.NestedException;
import leonards.common.sql.DBException;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Parameter {
	public static final String RESERVATION_MOVEMENT = "RESERVATION_MOVEMENT";
	public static final String DEFAULT_BOAT_ID = "DEFAULT_BOAT_ID";
	public static final String MAIN_ADMIN_ID = "MAIN_ADMIN_ID";
	public static final String ACTIVE_RESERVATION_STATE_ID = "ACTIVE_RESERVATION_STATE_ID";
	public static final String BOAT_TIME_TABLE_DAYS = "BOAT_TIME_TABLE_DAYS";
	public static final String MAX_CREDIT_LIMIT = "MAX_CREDIT_LIMIT";
	public static final String MAX_ACTIVE_RESERVATIONS = "MAX_ACTIVE_RESERVATIONS";
	public static final String MAX_INVITED_CREW = "MAX_INVITED_CREW";
	public static final String ADMIN_CANCEL_RESERVATION_STATE_ID = "ADMIN_CANCEL_RESERVATION_STATE_ID";
	public static final String USER_CANCEL_RESERVATION_STATE_ID = "USER_CANCEL_RESERVATION_STATE_ID";
	
	private static final String validParameters[] = {RESERVATION_MOVEMENT,DEFAULT_BOAT_ID,MAIN_ADMIN_ID, ACTIVE_RESERVATION_STATE_ID, BOAT_TIME_TABLE_DAYS, MAX_CREDIT_LIMIT, MAX_ACTIVE_RESERVATIONS, MAX_INVITED_CREW,ADMIN_CANCEL_RESERVATION_STATE_ID,USER_CANCEL_RESERVATION_STATE_ID};
	
	private String name = null;
	private String value = null;
	private String label = null;
	private String referenceDataSql = null;
	
	/**
	 * 
	 */
	public Parameter() {
		this(null, null);
	}

	public Parameter(String name, String value) {
		this(name, value, null);
	}

	public Parameter(String name, String value, String label) {
		super();
		setName(name);
		setValue(value);
		setLabel(label);
	}
	
	public boolean isValidParameter() {
		for( int i = 0; i < validParameters.length; i++ ) {
			if( validParameters[i].equals(getName()) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string) {
		label = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	public boolean hasReferenceDataSql() {
		return getReferenceDataSql() != null && getReferenceDataSql().length() > 0;
	}

	public Vector getReferenceData() {
		try {
			if( hasReferenceDataSql() ) {
				return DBHome.retrieveReferenceData(getReferenceDataSql());
			} else {
				return null;
			}
		} catch( DBException ex ) {
			return null;
		}
	}
	
	/** 
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return getName().hashCode();
	}
	
	public Integer getValueAsInteger() {
		return new Integer(getValue());
	}
	
	public int getValueAsInt() {
		return Integer.parseInt(getValue());
	}
	
	public Double getValueAsDouble() {
		return new Double(getValue());
	}

	/**
	 * @return
	 */
	public String getReferenceDataSql() {
		return referenceDataSql;
	}

	/**
	 * @param string
	 */
	public void setReferenceDataSql(String string) {
		referenceDataSql = string;
	}

	public void updateValue() throws NestedException {
		DBHome.updateValue(this);
	}
}
