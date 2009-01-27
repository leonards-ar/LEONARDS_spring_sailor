/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: DBHome.java
 *
 * Property of Leonards / Mindpool
 * Created on 17/06/2004
 */
package leonards.spring.domain;

import java.util.*;

import leonards.common.sql.*;
import leonards.common.web.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class DBHome {
	protected static final String CONNECTION_NAME = "jdbc/spring";
	
	/**
	 * 
	 */
	private DBHome() {
		super();
	}

	protected static boolean getBoolean(DBResultSetRow row, String columnName) {
		Integer value = row.getInteger(columnName);
		return value != null && value.intValue() == 1;
	}

	protected static void buildReservationShift(ReservationShift reservationShift, DBResultSetRow row) {
		reservationShift.setDate(row.getDate("date"));
		reservationShift.setShiftId(row.getInteger("shift_id"));
		reservationShift.setReservationId(row.getInteger("reservation_id"));
	}
	
	protected static void buildShift(Shift shift, DBResultSetRow row) {
		shift.setId(row.getInteger("shift_id"));
		shift.setDescription(row.getString("description"));
		shift.setTimeFrom(row.getNativeLong("time_from"));
		shift.setTimeTo(row.getNativeLong("time_to"));
		shift.setEnabled(getBoolean(row, "enabled"));
	}
	
	protected static void buildShiftDetail(ShiftDetail shiftDetail, DBResultSetRow row) {
		shiftDetail.setValue(row.getDouble("value"));
		shiftDetail.setDay(row.getInt("day"));
		shiftDetail.setShiftId(row.getInteger("shift_id"));
	}
	
	protected static void buildBoatState(BoatState state, DBResultSetRow row) {
		state.setId(row.getInteger("state_id"));
		state.setDescription(row.getString("description"));
		state.setCanReserve(getBoolean(row, "can_reserve"));
	}

	protected static void buildUserState(UserState state, DBResultSetRow row) {
		state.setId(row.getInteger("state_id"));
		state.setDescription(row.getString("description"));
		state.setEnabled(getBoolean(row, "can_reserve"));
	}

	protected static void buildParameter(Parameter param, DBResultSetRow row) {
		param.setName(row.getString("name").toUpperCase());
		param.setValue(row.getString("value"));
		param.setLabel(row.getString("label"));
		param.setReferenceDataSql(row.getString("reference_data_statement"));
	}

	protected static void buildMovementType(MovementType type, DBResultSetRow row) {
		type.setId(row.getInteger("movement_type_id"));
		type.setDescription(row.getString("description"));
		type.setSign(row.getInt("sign"));
	}

	protected static void buildDocumentType(ReferenceData type, DBResultSetRow row) {
		type.setId(row.getInteger("document_type_id"));
		type.setDescription(row.getString("description"));
	}

	protected static void buildMessage(Message msg, DBResultSetRow row) {
		msg.setId(row.getInteger("message_id"));
		msg.setUserId(row.getInteger("user_id"));
		msg.setDate(row.getDate("date"));
		msg.setMessage(row.getString("message"));
		msg.setUser(new User());
		msg.getUser().setName(row.getString("name"));
		msg.getUser().setSurname(row.getString("surname"));
	}
	
	protected static void buildMenuGroup(MenuGroup group, DBResultSetRow row) {
		group.setId(row.getInteger("menu_group_id"));
		group.setDescription(row.getString("menu_group_description"));
	}

	protected static void buildReferenceData(ReferenceData referenceData, DBResultSetRow row) {
		referenceData.setId(row.getInteger("id"));
		referenceData.setDescription(row.getString("description"));
	}

	protected static void buildMovement(Movement movement, DBResultSetRow row) {
		movement.setId(row.getInteger("movement_id"));
		movement.setMovementTypeId(row.getInteger("movement_type_id"));
		movement.setAmount(row.getDouble("amount"));
		movement.setBalance(row.getDouble("balance"));
		movement.setDocumentData(row.getString("document_data"));
		movement.setComments(row.getString("comments"));
		movement.setDate(row.getDate("date"));
		movement.setUserId(row.getInteger("user_id"));
	}
	
	protected static void buildMenuItem(MenuItem item, DBResultSetRow row) {
		item.setId(row.getInteger("menu_item_id"));
		item.setDescription(row.getString("description"));
		item.setTargetUrl(row.getString("target_url"));
		item.setTarget(row.getString("target"));
		item.setMenuGroupId(row.getInteger("menu_group_id"));
		item.setOrder(row.getInteger("menu_order"));
	}
	
	
	protected static void buildReservationState(ReferenceData state, DBResultSetRow row) {
		state.setId(row.getInteger("reservation_state_id"));
		state.setDescription(row.getString("description"));
	}

	protected static void buildReservationType(ReservationType type, DBResultSetRow row) {
		type.setId(row.getInteger("reservation_type_id"));
		type.setDescription(row.getString("description"));
		type.setShiftsQty(row.getInteger("shifts_qty"));
		type.setExtraValue(row.getDouble("extra_value"));
		type.setEnabled(getBoolean(row, "enabled"));
	}

	
	protected static void buildPermitType(ReferenceData type, DBResultSetRow row) {
		type.setId(row.getInteger("permit_type_id"));
		type.setDescription(row.getString("description"));
	}

	protected static void buildProfile(Profile profile, DBResultSetRow row) {
		profile.setId(row.getInteger("profile_id"));
		profile.setDescription(row.getString("description"));
		profile.setDefaultMenuItemId(row.getInteger("home_menu_item_id"));
		profile.setSpecialReserver(getBoolean(row, "special_reserver"));
	}

	protected static void buildReservation(Reservation aReservation,  DBResultSetRow row) {
		aReservation.setId(row.getInteger("reservation_id"));
		aReservation.setUserId(row.getInteger("user_id"));
		aReservation.setValue(row.getDouble("value"));
		aReservation.setStateId(row.getInteger("reservation_state_id"));
		aReservation.setTypeId(row.getInteger("reservation_type_id"));
		aReservation.setStateDate(row.getDate("state_date"));
		aReservation.setBoatId(row.getInteger("boat_id"));
	}
	
	protected static void buildBoat(Boat boat, DBResultSetRow row) {
		boat.setId(row.getInteger("boat_id"));
		boat.setName(row.getString("name"));
		boat.setStateId(row.getInteger("state_id"));
		boat.setStateDateFrom(row.getDate("state_date_from"));
		boat.setStateDateTo(row.getDate("state_date_to"));
		boat.setStateComments(row.getString("state_comments"));		
	}

	protected static void buildContact(Contact contact, DBResultSetRow row) {
		contact.setId(row.getInteger("contact_id"));
		contact.setUserId(row.getInteger("user_id"));
		contact.setCompleteName(row.getString("full_name"));
		contact.getDocument().setDocumentTypeId(row.getInteger("document_type_id"));
		contact.getDocument().setNumber(row.getString("document"));
		contact.setTelephone(row.getString("telephone"));
	}

	protected static void buildUser(User user, DBResultSetRow row) {
		user.setId(row.getInteger("user_id"));
		user.setUsername(row.getString("username"));
		user.setPassword(row.getString("password"));
		user.setName(row.getString("name"));
		user.setSurname(row.getString("surname"));		
		user.setEmail(row.getString("email"));
		user.setAddress(row.getString("address"));
		user.setTelephone(row.getString("telephone"));
		user.setCelPhone(row.getString("cel_phone"));
		user.getDocument().setDocumentTypeId(row.getInteger("document_type_id"));
		user.getDocument().setNumber(row.getString("document"));
		user.getPermit().setPermitTypeId(row.getInteger("permit_type_id"));
		user.getPermit().setNumber(row.getString("permit"));		
		user.getPermit().setDueDate(row.getDate("permit_due_date"));
		user.setDossier(row.getString("dossier"));
		user.setStateId(row.getInteger("state_id"));
		user.setProfileId(row.getInteger("profile_id"));
		user.setDepartment(row.getString("department"));
		user.setBalance(row.getDouble("balance"));
	}

	public static void logon(User user) throws LogonException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("select * from tbl_users where username = ? and deleted = 0", CONNECTION_NAME);
			sp.setString(1, user.getUsername());
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				DBResultSetRow row = rs.next();
				String dbPassword = row.getString("password");
				
				if( dbPassword != null && dbPassword.equals(user.getPassword()) ) {
					buildUser(user, row);
				} else {
					throw new InvalidPasswordException("Invalid password for user [" + user.getUsername() + "]");
				}	
			} else {
				throw new InvalidUsernameException("User [" + user.getUsername() + "] does not exist.");
			}
		} catch( InvalidPasswordException ex ) {
			throw ex;
		} catch( InvalidUsernameException ex ) { 
			throw ex;
		} catch( DBException dbEx ) {
			throw new LogonException("Database error performing logon.", dbEx);
		} catch( Throwable ex ) {
			throw new LogonException("Unexpected error performing logon.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveShiftDetails(Integer shiftId) throws DBException {
		DBStaticStatement sp = null;
		Vector details = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_shift_values order by day", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ShiftDetail aDetail;
			while( rs.hasNext() ) {
				aDetail = new ShiftDetail();
				buildShiftDetail(aDetail, rs.next());
				details.addElement(aDetail);
			}
			return details;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving shift details for shift [" + shiftId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Shift retrieveShift(Integer shiftId) throws DBException {
		DBStaticStatement sp = null;
		Shift shift = null;
		try {
			sp = new DBStaticStatement("select * from tbl_shifts where shift_id = ?", CONNECTION_NAME);
			sp.setInt(1, shiftId);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				shift = new Shift();
				buildShift(shift, rs.next());
			}
			return shift;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving shift [" + shiftId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	
	public static Vector retrieveShifts() throws DBException {
		DBStaticStatement sp = null;
		Shift aShift = null;
		Vector shifts = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_shifts order by time_from, time_to", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			while( rs.hasNext() ) {
				aShift = new Shift();
				buildShift(aShift, rs.next());
				shifts.addElement(aShift);
			}
			return shifts;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving shifts.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Boat retrieveBoat(Integer boatId) throws DBException {
		DBStaticStatement sp = null;
		Boat boat = null;
		try {
			sp = new DBStaticStatement("select * from tbl_boats where boat_id = ?", CONNECTION_NAME);
			sp.setInt(1, boatId);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				boat = new Boat();
				buildBoat(boat, rs.next());
			}
			return boat;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving boat [" + boatId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static User retrieveUser(Integer userId) throws DBException {
		DBStaticStatement sp = null;
		User user = null;
		try {
			sp = new DBStaticStatement("select * from tbl_users where user_id = ? and deleted = 0", CONNECTION_NAME);
			sp.setInt(1, userId);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				user = new User();
				buildUser(user, rs.next());
			}
			return user;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving user [" + userId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static User retrieveUser(String username) throws DBException {
		DBStaticStatement sp = null;
		User user = null;
		try {
			sp = new DBStaticStatement("select * from tbl_users where username = ? and deleted = 0", CONNECTION_NAME);
			sp.setString(1, username);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				user = new User();
				buildUser(user, rs.next());
			}
			return user;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving user [" + username + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Vector retrieveBoatStates() throws DBException {
		DBStaticStatement sp = null;
		Vector states = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_boat_states order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			BoatState aState;
			while( rs.hasNext() ) {
				aState = new BoatState();
				buildBoatState(aState, rs.next());
				states.addElement(aState);
			}
			return states;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving boat states.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveMovementTypes() throws DBException {
		DBStaticStatement sp = null;
		Vector types = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_movement_types order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			MovementType aType;
			while( rs.hasNext() ) {
				aType = new MovementType();
				buildMovementType(aType, rs.next());
				types.addElement(aType);
			}
			return types;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving movement types.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveUserStates() throws DBException {
		DBStaticStatement sp = null;
		Vector states = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_user_states order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			UserState aState;
			while( rs.hasNext() ) {
				aState = new UserState();
				buildUserState(aState, rs.next());
				states.addElement(aState);
			}
			return states;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving user states.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveDocumentTypes() throws DBException {
		DBStaticStatement sp = null;
		Vector types = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_document_types order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ReferenceData aType;
			while( rs.hasNext() ) {
				aType = new ReferenceData();
				buildDocumentType(aType, rs.next());
				types.addElement(aType);
			}
			return types;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving document types.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	

	public static Vector retrieveReservationStates() throws DBException {
		DBStaticStatement sp = null;
		Vector states = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_reservation_states order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ReferenceData aState;
			while( rs.hasNext() ) {
				aState = new ReferenceData();
				buildReservationState(aState, rs.next());
				states.addElement(aState);
			}
			return states;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reservation states.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveReservationTypes() throws DBException {
		DBStaticStatement sp = null;
		Vector types = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_reservation_types where enabled = 1 order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ReservationType aType;
			while( rs.hasNext() ) {
				aType = new ReservationType();
				buildReservationType(aType, rs.next());
				types.addElement(aType);
			}
			return types;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reservation types.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Vector retrievePermitTypes() throws DBException {
		DBStaticStatement sp = null;
		Vector types = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_permit_types order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ReferenceData aType;
			while( rs.hasNext() ) {
				aType = new ReferenceData();
				buildPermitType(aType, rs.next());
				types.addElement(aType);
			}
			return types;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving permit types.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Vector retrieveReservationShifts(Integer reservationId) throws DBException {
		DBStaticStatement sp = null;
		Vector shifts = new Vector();
		try {
			sp = new DBStaticStatement("select rs.* from tbl_reservations_shifts as rs, tbl_shifts as s where s.shift_id = rs.shift_id and rs.reservation_id = ? order by rs.date, s.time_from", CONNECTION_NAME);
			sp.setInt(1, reservationId);
			DBResultSet rs = sp.executeQuery();
			ReservationShift aShift;
			while( rs.hasNext() ) {
				aShift = new ReservationShift();
				buildReservationShift(aShift, rs.next());
				shifts.addElement(aShift);
			}
			return shifts;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reservation shifts for reservation [" + reservationId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Vector retrieveProfiles() throws DBException {
		DBStaticStatement sp = null;
		Vector profiles = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_profiles order by description", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			Profile aProfile;
			while( rs.hasNext() ) {
				aProfile = new Profile();
				buildProfile(aProfile, rs.next());
				profiles.addElement(aProfile);
			}
			return profiles;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving permit types.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Menu retrieveMenu(Integer profileId) throws DBException {
		DBStaticStatement sp = null;
		Menu menu = new Menu();
		try {
			sp = new DBStaticStatement("select mg.menu_group_id, mg.description as menu_group_description, mi.* from tbl_menu_group as mg, tbl_menu_item as mi, tbl_profiles_menu_items as pmi where mi.menu_group_id = mg.menu_group_id and pmi.menu_item_id = mi.menu_item_id and pmi.profile_id = ? order by mg.description, mi.menu_order", CONNECTION_NAME);
			sp.setInt(1, profileId);
			DBResultSet rs = sp.executeQuery();
			MenuGroup aMenuGroup = null;
			MenuItem aMenuItem;
			DBResultSetRow row;
			while( rs.hasNext() ) {
				row = rs.next();
				if( aMenuGroup == null || !aMenuGroup.getIdAsInteger().equals(row.getInteger("menu_group_id"))) {
					if( aMenuGroup != null ) {
						menu.addMenuGroup(aMenuGroup);
					}
					aMenuGroup = new MenuGroup();
					buildMenuGroup(aMenuGroup, row);
				}
				aMenuItem = new MenuItem();
				buildMenuItem(aMenuItem, row);
				aMenuItem.setMenuGroup(aMenuGroup);
				aMenuGroup.addMenuItem(aMenuItem);
			}
			if( aMenuGroup != null ) {
				menu.addMenuGroup(aMenuGroup);
			}
			return menu;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving menu for profile [" + profileId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector retrieveReservationContacts(Integer reservationId) throws DBException {
		DBStaticStatement sp = null;
		Vector contacts = new Vector();
		try {
			sp = new DBStaticStatement("select uc.* from tbl_user_contacts as uc, tbl_reservation_contacts as rc where uc.deleted = 0 and rc.reservation_id = ? and rc.contact_id = uc.contact_id order by uc.full_name", CONNECTION_NAME);
			sp.setInt(1,reservationId);
			DBResultSet rs = sp.executeQuery();
			Contact aContact;
			while( rs.hasNext() ) {
				aContact = new Contact();
				buildContact(aContact, rs.next());
				contacts.addElement(aContact);
			}
			return contacts;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reservation contacts.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static int retrieveMaximumReservationShifts() throws DBException {
		DBStaticStatement sp = null;
		int shifts = 0;
		try {
			sp = new DBStaticStatement("select max(shifts_qty) as maxShiftsQty from tbl_reservation_types", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				shifts = rs.getFirstDataRow().getInt("maxShiftsQty");
			}
			return shifts;
		} catch( DBException ex ) {
			throw ex;			
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving maximum resaervation shifts.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static Vector retrieveContacts(Integer userId) throws DBException {
		DBStaticStatement sp = null;
		Vector contacts = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_user_contacts where deleted = 0 and user_id = ? order by full_name", CONNECTION_NAME);
			sp.setInt(1, userId);
			DBResultSet rs = sp.executeQuery();
			Contact aContact;
			while( rs.hasNext() ) {
				aContact = new Contact();
				buildContact(aContact, rs.next());
				contacts.addElement(aContact);
			}
			return contacts;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving contacts.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static MenuGroup retrieveMenuGroup(Integer menuGroupId) throws DBException {
		DBStaticStatement sp = null;
		MenuGroup menuGroup = null;
		try {
			sp = new DBStaticStatement("select * from tbl_menu_group where menu_group_id = ?", CONNECTION_NAME);
			sp.setInt(1, menuGroupId);
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				menuGroup = new MenuGroup();
				buildMenuGroup(menuGroup, rs.next());
			}
			return menuGroup;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving menu group [" + menuGroupId + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void cancelReservation(Reservation reservation, Integer newStateId) throws DBException {
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			sp = new DBStaticStatement("update tbl_reservations set reservation_state_id=?, state_date=NOW() where reservation_id=?", trxCtx);
			sp.setInt(1, newStateId);
			sp.setInt(2, reservation.getIdAsInteger());

			sp.execute();
			reservation.refreshValue();
			
			/*
			 * Added by Mariano Capurro, as special reservers must not have
			 * their balance updated any more.
			 * 2006-11-26
			 */
			if(!reservation.getUser().getProfile().isSpecialReserver()) {
				updateUserBalance(reservation.getUserId(), reservation.getValue(), trxCtx);
			}
						
			trxCtx.commit();	
		} catch( DBException ex ) {
			trxCtx.rollback();
			throw ex;
		} catch( Throwable ex ) {
			trxCtx.rollback();
			throw new DBException("Unexpected error updating reservation [" + reservation.getIdAsString() + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void update(User user) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_users set username=?, name=?, surname=?, email=?, address=?, telephone=?, cel_phone=?, document_type_id=?, document=?, permit_type_id=?, permit=?, permit_due_date=?, dossier=?, state_id=?, profile_id=?, department=? where user_id=?", CONNECTION_NAME);
			sp.setString(1, user.getUsername());
			sp.setString(2, user.getName());
			sp.setString(3, user.getSurname());
			sp.setString(4, user.getEmail());
			sp.setString(5, user.getAddress());
			sp.setString(6, user.getTelephone());
			sp.setString(7, user.getCelPhone());
			sp.setInt(8, user.getDocument().getDocumentTypeId());
			sp.setString(9, user.getDocument().getNumber());
			sp.setInt(10, user.getPermit().getPermitTypeId());
			sp.setString(11, user.getPermit().getNumber());
			sp.setDate(12, user.getPermit().getDueDate());
			sp.setString(13, user.getDossier());
			sp.setInt(14, user.getStateId());
			sp.setInt(15, user.getProfileId());
			sp.setString(16, user.getDepartment());
			sp.setInt(17, user.getIdAsInteger());

			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error updating user [" + (user != null ? user.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void updatePassword(User user) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_users set password=? where user_id=?", CONNECTION_NAME);
			sp.setString(1, user.getPassword());
			sp.setInt(2, user.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error updating user [" + (user != null ? user.getIdAsString() : "null") + "] password.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static void update(Boat boat) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_boats set name=?, state_id=?, state_date_from=?, state_date_to=?, state_comments=? where boat_id=?", CONNECTION_NAME);
			sp.setString(1, boat.getName());
			sp.setInt(2, boat.getStateId());
			sp.setShortDate(3, boat.getStateDateFrom());
			sp.setShortDate(4, boat.getStateDateTo());
			sp.setString(5, boat.getStateComments());
			sp.setInt(6, boat.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error updating boat [" + (boat != null ? boat.getIdAsString() : "null") + "] state.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static void update(Shift shift) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_shifts set description=?, time_from=?, time_to=?, enabled=? where shift_id=?", CONNECTION_NAME);
			sp.setString(1, shift.getDescription());
			sp.setLong(2, shift.getTimeFrom());
			sp.setLong(3, shift.getTimeTo());
			sp.setInt(4, shift.isEnabled() ? 1 : 0);
			sp.setInt(5, shift.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error updating shift [" + (shift != null ? shift.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static void updateValue(Parameter parameter) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_parameters set value=? where name=?", CONNECTION_NAME);
			sp.setString(1, parameter.getValue());
			sp.setString(2, parameter.getName());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error updating parameter [" + (parameter != null ? parameter.getName() : "null") + "] state.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}
	
	public synchronized static void insert(User user) throws DBException, UsernameAlreadyExistsException {
		if( retrieveUser(user.getUsername()) != null ) {
			throw new UsernameAlreadyExistsException("Username " + user.getUsername() + " already exists.");
		}
		
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			sp = new DBStaticStatement("insert into tbl_users (username, password, name, surname, email, address, telephone, cel_phone, document_type_id, document, permit_type_id, permit, permit_due_date, dossier, state_id, profile_id, department, balance, deleted) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)", trxCtx);
			sp.setString(1, user.getUsername());
			sp.setString(2, user.getPassword());
			sp.setString(3, user.getName());
			sp.setString(4, user.getSurname());
			sp.setString(5, user.getEmail());
			sp.setString(6, user.getAddress());
			sp.setString(7, user.getTelephone());
			sp.setString(8, user.getCelPhone());
			sp.setInt(9, user.getDocument().getDocumentTypeId());
			sp.setString(10, user.getDocument().getNumber());
			sp.setInt(11, user.getPermit().getPermitTypeId());
			sp.setString(12, user.getPermit().getNumber());
			sp.setDate(13, user.getPermit().getDueDate());
			sp.setString(14, user.getDossier());
			sp.setInt(15, user.getStateId());
			sp.setInt(16, user.getProfileId());
			sp.setString(17, user.getDepartment());
			sp.setDouble(18, user.getBalance());
			sp.execute();
			
			user.setId(getLastInsertedIdAsInteger(trxCtx));
			
			trxCtx.commit();
		} catch( DBException ex ) {
			trxCtx.rollback();
			throw ex;
		} catch( Throwable ex ) {
			trxCtx.rollback();
			throw new DBException("Unexpected error inserting user [" + (user != null ? user.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws DBException
	 */
	private static int getFreeShiftId() throws DBException {
		//:TODO: Verify that the shift ID does not exists
		return retrieveShifts().size() + 1;
	}
	
	public static synchronized void insert(Shift shift) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("insert into tbl_shifts (shift_id, description, time_from, time_to, enabled) values (?,?,?,?,?)", CONNECTION_NAME);
			sp.setInt(1, getFreeShiftId());
			sp.setString(2, shift.getDescription());
			sp.setLong(3, shift.getTimeFrom());
			sp.setLong(4, shift.getTimeTo());
			sp.setInt(5, shift.isEnabled() ? 1 : 0);
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error inserting shift [" + (shift != null ? shift.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static void insert(Message message) throws DBException {
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			sp = new DBStaticStatement("insert into tbl_messages (user_id, date, message) values (?,?,?)", trxCtx);
			sp.setInt(1, message.getUserId());
			sp.setDate(2, message.getDate());
			sp.setString(3, message.getMessage());

			sp.execute();
			
			message.setId(getLastInsertedIdAsInteger(trxCtx));
			
			trxCtx.commit();
		} catch( DBException ex ) {
			trxCtx.rollback();
			throw ex;
		} catch( Throwable ex ) {
			trxCtx.rollback();
			throw new DBException("Unexpected error inserting message.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void insert(Contact contact) throws DBException {
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			sp = new DBStaticStatement("insert into tbl_user_contacts (user_id, full_name, document_type_id, document, telephone, deleted) values (?,?,?,?,?,0)", trxCtx);
			sp.setInt(1, contact.getUserId());
			sp.setString(2, contact.getCompleteName());
			sp.setInt(3, contact.getDocument().getDocumentTypeId());
			sp.setString(4, contact.getDocument().getNumber());
			sp.setString(5, contact.getTelephone());
			sp.execute();
			
			contact.setId(getLastInsertedIdAsInteger(trxCtx));
			
			trxCtx.commit();
		} catch( DBException ex ) {
			if( trxCtx != null ) {
				trxCtx.rollback();
			}
			throw ex;
		} catch( Throwable ex ) {
			if( trxCtx != null ) {
				trxCtx.rollback();
			}
			throw new DBException("Unexpected error inserting contact.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}
	}

	protected static void checkDuplicatedReservation(Reservation reservation) throws DBException, ReservationAlreadyExistsException {
		Iterator shifts = reservation.getShifts().iterator();
		ReservationShift aShift;
		while(shifts.hasNext()) {
			aShift = (ReservationShift)shifts.next();
			if( retrieveReservation(aShift.getDate(), aShift.getShiftId()) != null ) {
				throw new ReservationAlreadyExistsException("There is already a reservation for [" + aShift.getDate() + "] and shift [" + aShift.getShift().getDescription() + "]" );
			}
		}
	}

	protected static void deleteReservationContacts(Integer reservationId, DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("delete from tbl_reservation_contacts where reservation_id=?", trxCtx);
			sp.setInt(1, reservationId);
			sp.execute();
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}			
		}
	}

	protected static void saveReservationContact(Integer reservationId, Integer contactadId, DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("insert into tbl_reservation_contacts (reservation_id, contact_id) values (?,?)", trxCtx);
			sp.setInt(1, reservationId);
			sp.setInt(2, contactadId);
			sp.execute();
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}			
		}
	}

	protected static void updateReservationContacts(Reservation reservation, DBTransactionContext trxCtx) throws DBException {
		Iterator contacts = reservation.getContacts().iterator();
		Contact aContact;
		deleteReservationContacts(reservation.getIdAsInteger(), trxCtx);
		while(contacts.hasNext()) {
			aContact = (Contact)contacts.next();
			saveReservationContact(reservation.getIdAsInteger(), aContact.getIdAsInteger(), trxCtx);
		}
	}

	protected static void deleteReservationShifts(Integer reservationId, DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("delete from tbl_reservations_shifts where reservation_id=?", trxCtx);
			sp.setInt(1, reservationId);
			sp.execute();
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}			
		}
	}

	protected static void saveReservationShift(Integer reservationId, ReservationShift shift, DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("insert into tbl_reservations_shifts (reservation_id, shift_id, date) values (?,?,?)", trxCtx);
			sp.setInt(1, reservationId);
			sp.setInt(2, shift.getShiftId());
			sp.setDate(3, shift.getDate());
			sp.execute();
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}			
		}
	}

	protected static void updateReservationShifts(Reservation reservation, DBTransactionContext trxCtx) throws DBException {
		Iterator shifts = reservation.getShifts().iterator();
		ReservationShift aShift;
		deleteReservationShifts(reservation.getIdAsInteger(), trxCtx);
		while(shifts.hasNext()) {
			aShift = (ReservationShift)shifts.next();
			saveReservationShift(reservation.getIdAsInteger(), aShift, trxCtx);
		}
	}

	public synchronized static void insert(Reservation reservation) throws DBException, ReservationAlreadyExistsException {
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		checkDuplicatedReservation(reservation);
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			sp = new DBStaticStatement("insert into tbl_reservations (state_date, value, user_id, reservation_type_id, reservation_state_id, boat_id) values (NOW(),?,?,?,?,?)", trxCtx);
			sp.setDouble(1, reservation.getValue());
			sp.setInt(2, reservation.getUserId());
			sp.setInt(3, reservation.getTypeId());
			sp.setInt(4, reservation.getStateId());
			sp.setInt(5, reservation.getBoatId());
			sp.execute();

			reservation.setId(getLastInsertedIdAsInteger(trxCtx));
			
			updateReservationShifts(reservation, trxCtx);
			updateReservationContacts(reservation, trxCtx);
			/*
			 * Added by Mariano Capurro, as special reservers must not have
			 * their balance updated any more.
			 * 2006-11-26
			 */
			if(!reservation.getUser().getProfile().isSpecialReserver()) {
				updateUserBalance(reservation.getUserId(), reservation.getValueAsNegative(), trxCtx);
			}			
			
			trxCtx.commit();
		} catch( DBException ex ) {
			if( trxCtx != null ) {
				trxCtx.rollback();
			}
			throw ex;
		} catch( Throwable ex ) {
			if( trxCtx != null ) {
				trxCtx.rollback();
			}
			throw new DBException("Unexpected error inserting reservation.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}
	}


	protected static Integer getLastInsertedIdAsInteger(DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("select last_insert_id() as last_id", trxCtx);
			DBResultSet rs = sp.executeQuery();
			if(rs.hasNext()) {
				return rs.next().getInteger("last_id");
			} else {
				return null;
			}
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
		
	}

	public static void delete(Contact contact) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_user_contacts set deleted = 1 where contact_id=?", CONNECTION_NAME);
			sp.setInt(1, contact.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error deleting contact [" + (contact != null ? contact.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void delete(User user) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_users set deleted = 1 where user_id=?", CONNECTION_NAME);
			sp.setInt(1, user.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error deleting user [" + (user != null ? user.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static void delete(Message message) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("delete from tbl_messages where message_id=?", CONNECTION_NAME);
			sp.setInt(1, message.getIdAsInteger());
			sp.execute();	
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error deleting message [" + (message != null ? message.getIdAsString() : "null") + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Hashtable retrieveParameters() throws DBException {
		DBStaticStatement sp = null;
		Hashtable parameters = new Hashtable();
		try {
			sp = new DBStaticStatement("select * from tbl_parameters", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			Parameter aParam;
			while( rs.hasNext() ) {
				aParam = new Parameter();
				buildParameter(aParam, rs.next());
				parameters.put(aParam.getName(), aParam);
			}
			return parameters;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving parameters.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Double retrieveBalance(Integer userId) throws DBException {
		User updatedUser = retrieveUser(userId);
		return updatedUser.getBalance();
	}
	
	public static Vector retrieveReferenceData(String sqlStatement) throws DBException {
		DBStaticStatement sp = null;
		Vector items = new Vector();
		try {
			sp = new DBStaticStatement(sqlStatement, CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			ReferenceData anItem;
			while( rs.hasNext() ) {
				anItem = new ReferenceData();
				buildReferenceData(anItem, rs.next());
				items.addElement(anItem);
			}
			return items;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reference data for [" + sqlStatement + "].", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Vector searchUsers() throws DBException {
		DBStaticStatement sp = null;
		Vector users = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_users where deleted = 0 order by surname, name", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			User anUser;
			while( rs.hasNext() ) {
				anUser = new User();
				buildUser(anUser, rs.next());
				users.addElement(anUser);
			}
			return users;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching users.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}

	public static Vector searchBoats() throws DBException {
		DBStaticStatement sp = null;
		Vector boats = new Vector();
		try {
			sp = new DBStaticStatement("select * from tbl_boats order by name", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			Boat aBoat;
			while( rs.hasNext() ) {
				aBoat = new Boat();
				buildBoat(aBoat, rs.next());
				boats.addElement(aBoat);
			}
			return boats;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching users.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	
	public synchronized static void insert(Movement movement) throws DBException {
		DBStaticStatement sp = null;
		DBTransactionContext trxCtx = null;
		try {
			trxCtx = new DBTransactionContext(CONNECTION_NAME);
			trxCtx.beginTransaction();
			movement.setBalance(new Double(getCurrentBalance(trxCtx).doubleValue() + movement.getSignAmount().doubleValue()));
			movement.setDate(new Date());
			sp = new DBStaticStatement("insert into tbl_movements (movement_id, movement_type_id, amount, balance, document_data, comments, date, user_id) values (null, ?, ?, ?, ?, ?, ?, ?)", trxCtx);
			sp.setInt(1, movement.getMovementTypeId());
			sp.setDouble(2, movement.getAmount());
			sp.setDouble(3, movement.getBalance());
			sp.setString(4, movement.getDocumentData());
			sp.setString(5, movement.getComments());
			sp.setDate(6, movement.getDate());
			sp.setInt(7, movement.getUserId());
			
			sp.execute();
			
			if(movement.getUserId() != null && movement.getMovementType().isReservationMovement()) {
				updateUserBalance(movement.getUserId(), movement.getAmount(), trxCtx);
			}
			
			trxCtx.commit();
		} catch( DBException ex ) {
			trxCtx.rollback();
			throw ex;
		} catch( Throwable ex ) {
			trxCtx.rollback();
			throw new DBException("Unexpected error inserting movement.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
			if( trxCtx != null ) {
				try { trxCtx.close(); } catch(Throwable ex) {}
			}
		}		 
	}
	
	protected static void updateUserBalance(Integer userId, Double amount, DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("update tbl_users set balance = balance + ? where user_id = ? and deleted = 0", trxCtx);
			sp.setDouble(1, amount != null ? amount : new Double(0.0));
			sp.setInt(2, userId);
			sp.execute();
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}							
	}
	
	
	
	private static Double getCurrentBalance(DBTransactionContext trxCtx) throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("select balance from tbl_movements order by date desc, movement_id desc limit 1", trxCtx);
			DBResultSet rs = sp.executeQuery();
			if(rs.hasNext()) {
				return rs.next().getDouble("balance");
			} else {
				return new Double(0.0);
			}
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	
	public static Double getCreditBalance() throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("select sum(balance) as credit from tbl_users where deleted = 0", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			if(rs.hasNext()) {
				return rs.next().getDouble("credit");
			} else {
				return new Double(0.0);
			}
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}
	
	public static Double getCurrentBalance() throws DBException {
		DBStaticStatement sp = null;
		try {
			sp = new DBStaticStatement("select balance from tbl_movements order by date desc, movement_id desc limit 1", CONNECTION_NAME);
			DBResultSet rs = sp.executeQuery();
			if(rs.hasNext()) {
				return rs.next().getDouble("balance");
			} else {
				return new Double(0.0);
			}
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static Vector searchCurrentAccount(MovementSearchFilter filter) throws DBException {
		DBStaticStatement sp = null;
		Vector movements = new Vector();
		
		try {
			DBDynamicSqlGenerator gen = filter.getSqlGenerator();
			sp = new DBStaticStatement(gen.getSqlStatement(), CONNECTION_NAME);
			gen.updateParameters(sp);
						
			DBResultSet rs = sp.executeQuery();
			Movement aMovement;
			while( rs.hasNext() ) {
				aMovement = new Movement();
				buildMovement(aMovement, rs.next());
				movements.addElement(aMovement);
			}
			return movements;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching current account.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}
	}
	
	public static Reservation retrieveReservation(Date day, Integer shiftId) throws DBException {
		Reservation aReservation = null;
		DBStaticStatement sp = null;
		
		try {
			sp = new DBStaticStatement("select r.* from tbl_reservations as r, tbl_reservations_shifts as rs where r.reservation_id = rs.reservation_id and rs.date = ? and r.reservation_state_id = ? and rs.shift_id = ? order by r.reservation_id, rs.date", CONNECTION_NAME);
			sp.setDate(1, day);
			sp.setInt(2, StaticDataManager.getActiveReservationStateId());
			sp.setInt(3, shiftId);
				
			DBResultSet rs = sp.executeQuery();
			if( rs.hasNext() ) {
				aReservation = new Reservation();
				buildReservation(aReservation, rs.next());
			}
			
			return aReservation;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error retrieving reservation.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	
	public static Vector searchActiveReservations(User user) throws DBException {
		Vector reservations = new Vector();
		DBStaticStatement sp = null;
		
		try {
			sp = new DBStaticStatement("select r.* from tbl_reservations as r, tbl_reservations_shifts as rs where r.reservation_id = rs.reservation_id and r.user_id = ? and rs.date > curdate() and r.reservation_state_id = ? group by r.reservation_id order by rs.date, r.reservation_id", CONNECTION_NAME);
			sp.setInt(1, user.getIdAsInteger());
			sp.setInt(2, StaticDataManager.getActiveReservationStateId());
				
			DBResultSet rs = sp.executeQuery();
			Reservation aReservation;
			while( rs.hasNext() ) {
				aReservation = new Reservation();
				buildReservation(aReservation, rs.next());
				aReservation.setUser(user);
				reservations.addElement(aReservation);
			}
			return reservations;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching active reservations.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}		
	}

	public static Vector searchReservations(ReservationSearchFilter filter) throws DBException {
		Vector reservations = new Vector();
		DBStaticStatement sp = null;
		
		try {
			DBDynamicSqlGenerator gen = filter.getSqlGenerator();
			
			sp = new DBStaticStatement(gen.getSqlStatement(), CONNECTION_NAME);
			gen.updateParameters(sp);
				
			DBResultSet rs = sp.executeQuery();
			Reservation aReservation;
			while( rs.hasNext() ) {
				aReservation = new Reservation();
				buildReservation(aReservation, rs.next());
				reservations.addElement(aReservation);
			}
			return reservations;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching active reservations.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}	
	}
	
	public static Vector searchMessages(int maxMessages) throws DBException {
		Vector msgs = new Vector();
		DBStaticStatement sp = null;
		
		try {
			sp = new DBStaticStatement("select m.*, u.name, u.surname from tbl_messages as m, tbl_users as u where m.user_id = u.user_id order by date desc, message_id desc limit ?", CONNECTION_NAME);
			sp.setInt(1, maxMessages);
						
			DBResultSet rs = sp.executeQuery();
			Message aMessage;
			while( rs.hasNext() ) {
				aMessage = new Message();
				buildMessage(aMessage, rs.next());
				msgs.addElement(aMessage);
			}
			return msgs;
		} catch( DBException ex ) {
			throw ex;
		} catch( Throwable ex ) {
			throw new DBException("Unexpected error searching messages.", ex);
		} finally {
			if( sp != null ) {
				try { sp.close(); } catch(Throwable ex) {}
			}
		}					
	}
}
