/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: BoatState.java
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
public class BoatState extends PersistentObject implements Serializable {
	private String description = null;
	private boolean canReserve = true;
	
	/**
	 * 
	 */
	public BoatState() {
		super();
	}

	/**
	 * @param id
	 */
	public BoatState(Object id) {
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
	public boolean canReserve() {
		return canReserve;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param b
	 */
	public void setCanReserve(boolean b) {
		canReserve = b;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

}
