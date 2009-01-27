/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: ShiftDetail.java
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
public class ShiftDetail extends PersistentObject implements Serializable {
	private int day = -1;
	private Double value = null;
	private Integer shiftId = null;

	/**
	 * 
	 */
	public ShiftDetail() {
		super();
	}

	/**
	 * @param id
	 */
	public ShiftDetail(Object id) {
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
	public int getDay() {
		return day;
	}

	/**
	 * @return
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param i
	 */
	public void setDay(int i) {
		day = i;
	}

	/**
	 * @param double1
	 */
	public void setValue(Double double1) {
		value = double1;
	}

	/**
	 * @return
	 */
	public Integer getShiftId() {
		return shiftId;
	}

	/**
	 * @param integer
	 */
	public void setShiftId(Integer integer) {
		shiftId = integer;
	}

}
