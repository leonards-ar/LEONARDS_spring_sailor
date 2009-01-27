/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Contact.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Contact extends PersistentObject implements Serializable {
	private String completeName = null;
	private Document document = null;
	private String telephone = null;
	private Integer userId = null;
	private User user = null;
	
	/**
	 * 
	 */
	public Contact() {
		super();
	}

	/**
	 * @param id
	 */
	public Contact(Object id) {
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
	public Document getDocument() {
		if(document == null) {
			document = new Document();
		}
		return document;
	}

	/**
	 * @return
	 */
	public String getTelephone() {
		return telephone;
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
	public void setTelephone(String string) {
		telephone = string;
	}

	/**
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return
	 */
	public Integer getUserId() {
		return userId;
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

	/**
	 * @return
	 */
	public String getCompleteName() {
		return completeName;
	}

	/**
	 * @param string
	 */
	public void setCompleteName(String string) {
		completeName = string;
	}

}
