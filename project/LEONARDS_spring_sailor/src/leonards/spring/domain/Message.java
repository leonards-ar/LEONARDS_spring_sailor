/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Message.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Date;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Message extends PersistentObject implements Serializable {
	private Integer userId = null;
	private User user = null;
	private Date date = null;
	private String message = null;
	
	/**
	 * 
	 */
	public Message() {
		super();
	}

	/**
	 * @param id
	 */
	public Message(Object id) {
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

	/** 
	 * @throws NestedException
	 * @see leonards.common.base.PersistentObject#delete()
	 */
	public void delete() throws NestedException {
		DBHome.delete(this);
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
	public String getMessage() {
		return message;
	}

	/**
	 * @return
	 */
	public User getUser() {
		if(user == null) {
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
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
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
