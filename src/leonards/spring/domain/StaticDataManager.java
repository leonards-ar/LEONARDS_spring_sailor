/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: StaticDataManager.java
 *
 * Property of Leonards / Mindpool
 * Created on 17/06/2004
 */
package leonards.spring.domain;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class StaticDataManager {

	private static Vector documentTypes = null;
	private static Vector permitTypes = null;
	private static Vector userStates = null;
	private static Vector boatStates = null;
	private static Vector movementTypes = null;
	private static Vector profiles = null;
	private static Vector reservationStates = null;
	private static Vector reservationTypes = null;
	private static Hashtable parameters = null;
	
	/**
	 * 
	 */
	private StaticDataManager() {
		super();
	}

	/**
	 * @return
	 */
	public static Vector getBoatStates() throws SpringException {
		if( boatStates == null ) {
			try {
				boatStates = DBHome.retrieveBoatStates();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve boat states.", ex);
			}
		}
		return boatStates;
	}
	
	public static BoatState getBoatState(Integer id) throws SpringException {
		BoatState aState = new BoatState(id);
		int index = getBoatStates().indexOf(aState);
		return index >= 0 ? (BoatState)getBoatStates().elementAt(index) : null;
	}

	/**
	 * @return
	 */
	public static Vector getMovementTypes() throws SpringException {
		if( movementTypes == null ) {
			try {
				movementTypes = DBHome.retrieveMovementTypes();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve movement types.", ex);
			}
		}

		return movementTypes;
	}

	public static MovementType getMovementType(Integer id) throws SpringException {
		MovementType aType = new MovementType(id);
		int index = getMovementTypes().indexOf(aType);
		return index >= 0 ? (MovementType)getMovementTypes().elementAt(index) : null;
	}

	public static Vector getIncomeMovementTypes() throws SpringException {
		Vector movements = new Vector();
		Iterator types = getMovementTypes().iterator();
		MovementType aType;
		
		while( types.hasNext() ) {
			aType = (MovementType)types.next();
			if( aType.isIncomeMovement() ) {
				movements.addElement(aType);
			}
		}
		
		return movements;
	}

	public static Vector getExpenseMovementTypes() throws SpringException {
		Vector movements = new Vector();
		Iterator types = getMovementTypes().iterator();
		MovementType aType;
		
		while( types.hasNext() ) {
			aType = (MovementType)types.next();
			if( aType.isExpenseMovement() ) {
				movements.addElement(aType);
			}
		}
		
		return movements;
	}

	public static Vector getOtherIncomeMovementTypes() throws SpringException {
		Vector movements = new Vector();
		Iterator types = getMovementTypes().iterator();
		MovementType aType;
		
		while( types.hasNext() ) {
			aType = (MovementType)types.next();
			if( aType.isIncomeMovement() && !aType.getIdAsInteger().equals(getReservationMovementId())) {
				movements.addElement(aType);
			}
		}
		
		return movements;
	}	
	
	public static Integer getReservationMovementId() throws SpringException {
		return getParameter(Parameter.RESERVATION_MOVEMENT).getValueAsInteger();
	}

	public static Integer getActiveReservationStateId() throws SpringException {
		return getParameter(Parameter.ACTIVE_RESERVATION_STATE_ID).getValueAsInteger();
	}
	
	/**
	 * @return
	 */
	public static Vector getUserStates() throws SpringException {
		if( userStates == null ) {
			try {
				userStates = DBHome.retrieveUserStates();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve user states.", ex);
			}
		}
		return userStates;
	}

	public static UserState getUserState(Integer id) throws SpringException {
		UserState aState = new UserState(id);
		int index = getUserStates().indexOf(aState);
		return index >= 0 ? (UserState )getUserStates().elementAt(index) : null;
	}
	

	/**
	 * @return
	 */
	public static Vector getDocumentTypes() throws SpringException {
		if( documentTypes == null ) {
			try {
				documentTypes = DBHome.retrieveDocumentTypes();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve document types.", ex);
			}
		}
		return documentTypes;
	}

	public static ReferenceData getDocumentType(Integer id) throws SpringException {
		ReferenceData aType = new ReferenceData(id);
		int index = getDocumentTypes().indexOf(aType);
		return index >= 0 ? (ReferenceData)getDocumentTypes().elementAt(index) : null;
	}
	
	/**
	 * @return
	 */
	public static Vector getPermitTypes() throws SpringException {
		if( permitTypes == null ) {
			try {
				permitTypes = DBHome.retrievePermitTypes();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve permit types.", ex);
			}
		}
		return permitTypes;
	}

	public static ReferenceData getPermitType(Integer id) throws SpringException {
		ReferenceData aType = new ReferenceData(id);
		int index = getPermitTypes().indexOf(aType);
		return index >= 0 ? (ReferenceData)getPermitTypes().elementAt(index) : null;
	}

	/**
	 * @return
	 */
	public static Vector getReservationStates() throws SpringException {
		if( reservationStates == null ) {
			try {
				reservationStates = DBHome.retrieveReservationStates();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve reservation states.", ex);
			}
		}
		return reservationStates;
	}

	public static ReferenceData getReservationState(Integer id) throws SpringException {
		ReferenceData aState = new ReferenceData(id);
		int index = getReservationStates().indexOf(aState);
		return index >= 0 ? (ReferenceData)getReservationStates().elementAt(index) : null;
	}

	/**
	 * @return
	 */
	public static Vector getReservationTypes() throws SpringException {
		if( reservationTypes == null ) {
			try {
				reservationTypes = DBHome.retrieveReservationTypes();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve reservation types.", ex);
			}
		}
		return reservationTypes;
	}

	public static ReservationType getReservationType(Integer id) throws SpringException {
		ReservationType aType = new ReservationType(id);
		int index = getReservationTypes().indexOf(aType);
		return index >= 0 ? (ReservationType)getReservationTypes().elementAt(index) : null;
	}

	public static Hashtable getParameters() throws SpringException {
		if( parameters == null ) {
			try {
				parameters = DBHome.retrieveParameters();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve parameters.", ex);
			}			
		}
		return parameters;
	}

	public static Parameter getParameter(String key) throws SpringException {
		return (Parameter)getParameters().get(key);
	}

	/**
	 * @return
	 */
	public static Vector getProfiles() throws SpringException {
		if( profiles == null ) {
			try {
				profiles = DBHome.retrieveProfiles();
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve profiles.", ex);
			}
		}
		return profiles;
	}

	public static Profile getProfile(Integer id) throws SpringException {
		Profile aProfile = new Profile(id);
		int index = getProfiles().indexOf(aProfile);
		return index >= 0 ? (Profile)getProfiles().elementAt(index) : null;
	}

	public static void reset() {
		documentTypes = null;
		permitTypes = null;
		userStates = null;
		boatStates = null;
		movementTypes = null;
		profiles = null;
		parameters = null;
		reservationStates = null;
		reservationTypes = null;
	}
}
