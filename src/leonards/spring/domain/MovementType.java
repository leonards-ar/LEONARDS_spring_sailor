/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: MovementType.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class MovementType extends PersistentObject {
	private String description = null;
	private int sign = 1;
	
	/**
	 * 
	 */
	public MovementType() {
		super();
	}

	/**
	 * @param id
	 */
	public MovementType(Object id) {
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
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public int getSign() {
		return sign;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param s
	 */
	public void setSign(int i) {
		sign = i;
	}

	public boolean isIncomeMovement() {
		return getSign() > 0;
	}
	
	public boolean isExpenseMovement() {
		return getSign() < 0;
	}
	
	public boolean isReservationMovement() {
		try {
			return getId() != null && getIdAsInteger().equals(StaticDataManager.getReservationMovementId());
		} catch( Throwable ex ) {
			return false;
		}
		
	}

}
