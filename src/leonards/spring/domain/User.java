/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: User.java
 *
 * Property of Leonards / Mindpool
 * Created on 14/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import leonards.common.base.*;
import leonards.common.web.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class User extends WebApplicationUser implements Serializable, Cloneable {
	private String address = null;
	private String telephone = null;
	private String celPhone = null;
	private Document document = null;
	private Permit permit = null;
	private String dossier = null;
	private UserState state = null;
	private Integer stateId = null;
	private Profile profile = null;
	private Integer profileId = null;
	private String department = null;
	private Double balance = null;
	private Vector contacts = null;
	
	/**
	 * 
	 */
	public User() {
		super();
	}

	public boolean isLoggedOn() {
		return getId() != null;	
	}
	
	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super(username, password);
	}

	/** 
	 * @throws LogonException
	 * @see leonards.common.web.WebApplicationUser#logon()
	 */
	public void logon() throws LogonException {
		DBHome.logon(this);
	}

	public void setPassword(String password) {
		super.setPassword(password != null ? SecurityToolkit.md5(password) : null);
	}
	
	protected void update() throws NestedException {
		DBHome.update(this);
	}
	
	protected void insert() throws NestedException {
		DBHome.insert(this);
	}
	
	public void delete() throws NestedException {
		DBHome.delete(this);
	}
	
	public void updatePassword() throws NestedException {
		DBHome.updatePassword(this);
	}
	
	/**
	 * @return
	 */
	public String getAddress() {
		return address != null ? address : "";
	}

	/**
	 * @return
	 */
	public Double getBalance() {
		return balance != null ? balance : new Double(0.0);
	}

	/**
	 * @return
	 */
	public String getCelPhone() {
		return celPhone != null ? celPhone : "";
	}

	public void refreshBalance() throws NestedException {
		setBalance(DBHome.retrieveBalance(getIdAsInteger()));
	}

	/**
	 * @return
	 */
	public String getDepartment() {
		return department != null ? department : "";
	}

	/**
	 * @return
	 */
	public Document getDocument() {
		if( document == null ) {
			setDocument(new Document());
		}
		return document;
	}

	/**
	 * @return
	 */
	public String getDossier() {
		return dossier != null ? dossier : "";
	}

	/**
	 * @return
	 */
	public Permit getPermit() {
		if( permit == null ) {
			setPermit(new Permit());
		}
		return permit;
	}

	/**
	 * @return
	 */
	public Profile getProfile() {
		if( profile == null ) {
			try {
				setProfile(StaticDataManager.getProfile(getProfileId()));
			} catch( Throwable ex ) {
				setProfile(null);
			}
			
		}
		return profile;
	}

	/**
	 * @return
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * @return
	 */
	public UserState getState() {
		if( state == null ) {
			try {
				setState(StaticDataManager.getUserState(getStateId()));
			} catch(Throwable ex) {
				setState(null);
			}
			
		}
		return state;
	}

	/**
	 * @return
	 */
	public Integer getStateId() {
		return stateId != null ? stateId : new Integer(-1);
	}

	/**
	 * @return
	 */
	public String getTelephone() {
		return telephone != null ? telephone : "";
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param double1
	 */
	public void setBalance(Double double1) {
		balance = double1;
	}

	/**
	 * @param string
	 */
	public void setCelPhone(String string) {
		celPhone = string;
	}

	/**
	 * @param string
	 */
	public void setDepartment(String string) {
		department = string;
	}

	/**
	 * @param document
	 */
	public void setDocument(Document document) {
		this.document = document;
	}


	/**
	 * @param string
	 */
	public void setDossier(String string) {
		dossier = string;
	}

	/**
	 * @param permit
	 */
	public void setPermit(Permit permit) {
		this.permit = permit;
	}

	/**
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @param integer
	 */
	public void setProfileId(Integer integer) {
		profileId = integer;
	}

	/**
	 * @param state
	 */
	public void setState(UserState state) {
		this.state = state;
	}

	/**
	 * @param integer
	 */
	public void setStateId(Integer integer) {
		stateId = integer;
	}

	/**
	 * @param string
	 */
	public void setTelephone(String string) {
		telephone = string;
	}

	/**
	 * @return
	 */
	public Vector getContacts() throws SpringException {
		if( contacts == null ) {
			try {
				setContacts(DBHome.retrieveContacts(getIdAsInteger()));
			} catch(Throwable ex) {
				throw new SpringException("Could not retrieve user contacts.", ex);
			}
		}
		return contacts;
	}

	/**
	 * @param vector
	 */
	public void setContacts(Vector vector) {
		contacts = vector;
	}
	
	public void setDocument(Integer documentTypeId, String number) {
		document = new Document();
		document.setDocumentTypeId(documentTypeId);
		document.setNumber(number);
		setDocument(document);
	}

	public void setPermit(Integer permitTypeId, String number, Date permitDueDate) {
		permit = new Permit();
		permit.setPermitTypeId(permitTypeId);
		permit.setNumber(number);
		permit.setDueDate(permitDueDate);
		setPermit(permit);
	}
	
	/** 
	 * Makes a shallow copy of this instance
	 * @return An object representing the copy 
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private boolean hasValue(String str) {
		return str != null && str.trim().length() > 0;
	}

	public String getCompleteName() {
		if(hasValue(getSurname()) && hasValue(getName())) {
			return getSurname() + ", " + getName();
		} else if( hasValue(getSurname())) {
			return getSurname();
		} else if( hasValue(getName()) ) {
			return getName();
		} else {
			return "";
		}
		
	}
}
