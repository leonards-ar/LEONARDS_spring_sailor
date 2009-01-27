/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: UserState.java
 *
 * Property of Leonards / Mindpool
 * Created on 17/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class UserState extends PersistentObject implements Serializable {
	private String description = null;
	private boolean enabled = false;
	
	/**
	 * @param id
	 */
	public UserState(Object id) {
		super(id);
	}

	/**
	 * 
	 */
	public UserState() {
		super();
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
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param b
	 */
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
}
