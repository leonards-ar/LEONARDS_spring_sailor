/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Configuration.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.util.Hashtable;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Configuration {
	private static Hashtable parameters = null;
	public static final String DEFAULT_BOAT_ID = "DEFAULT_BOAT_ID";
	public static final String MAIN_ADMIN_ID = "MAIN_ADMIN_ID";
	public static final String RESERVATION_MOVEMENT_TYPE_ID = "RESERVATION_MOVEMENT_TYPE_ID";
	
	/**
	 * 
	 */
	private Configuration() {
		super();
	}
	
	protected static Hashtable getParameters() throws SpringException {
		if( parameters == null ) {
			try {
				parameters = DBHome.retrieveParameters();
			} catch( Throwable ex ) {
				throw new SpringException("Could not retrieve parameters.", ex);
			}
		}
		return parameters;
	}

	public static String getParameter(String name) throws SpringException {
		return (String)getParameters().get(name.toUpperCase());
	}
	
	public static Integer getParameterAsInteger(String name) throws SpringException {
		String value = getParameter(name);
		return value != null ? new Integer(value) : null;
	}
	
	public static Integer getDefaultBoatId() throws SpringException {
		return getParameterAsInteger(DEFAULT_BOAT_ID);
	}

	public static Integer getMainAdministratorId() throws SpringException {
		return getParameterAsInteger(MAIN_ADMIN_ID);
	}

	public static Integer getReservationMovementTypeId() throws SpringException {
		return getParameterAsInteger(RESERVATION_MOVEMENT_TYPE_ID);
	}
		
	public static void reset() {
		parameters = null;
	}
	
}
