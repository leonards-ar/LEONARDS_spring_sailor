/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Movement.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.util.Date;
import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Movement extends PersistentObject {
	private Integer movementTypeId = null;
	private MovementType movementType = null;
	private Double amount = null;
	private Double balance = null;
	private String documentData = null;
	private String comments = null;
	private Date date = null;
	private Integer userId = null;
	private User user = null;
	
	/**
	 * 
	 */
	public Movement() {
		super();
	}

	/**
	 * @param id
	 */
	public Movement(Object id) {
		super(id);
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#update()
	 */
	protected void update() throws NestedException {
	}

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#insert()
	 */
	protected void insert() throws NestedException {
		DBHome.insert(this);
	}

	public Double getSignAmount() {
		return new Double(getAmount().doubleValue() * getMovementType().getSign());
	}
	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#delete()
	 */
	public void delete() throws NestedException {
	}

	/**
	 * @return
	 */
	public Double getAmount() {
		return amount != null ? amount : new Double(0.0);
	}

	/**
	 * @return
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @return
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public String getDocumentData() {
		return documentData;
	}

	/**
	 * @return
	 */
	public MovementType getMovementType() {
		if( movementType == null ) {
			try {
				movementType = StaticDataManager.getMovementType(getMovementTypeId());
			} catch( Throwable ex ) {
				movementType = null;
			}
			
		}
		return movementType;
	}

	/**
	 * @return
	 */
	public Integer getMovementTypeId() {
		return movementTypeId;
	}

	/**
	 * @return
	 */
	public User getUser() {
		if( user == null && getUserId() != null ) {
			try {
				user = DBHome.retrieveUser(getUserId());
			} catch( Throwable ex ) {
				user = null;
			}
		}
		return user;
	}

	/**
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param double1
	 */
	public void setAmount(Double double1) {
		amount = double1;
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
	public void setComments(String string) {
		comments = string;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param string
	 */
	public void setDocumentData(String string) {
		documentData = string;
	}

	/**
	 * @param type
	 */
	public void setMovementType(MovementType type) {
		movementType = type;
	}

	/**
	 * @param integer
	 */
	public void setMovementTypeId(Integer integer) {
		movementTypeId = integer;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param integer
	 */
	public void setUserId(Integer integer) {
		userId = integer;
	}

}
