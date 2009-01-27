/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Permit.java
 *
 * Property of Leonards / Mindpool
 * Created on 17/06/2004
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
public class Permit extends PersistentObject implements Serializable {
	private String number = null;
	private Integer permitTypeId = null;
	private Date dueDate = null;
	private ReferenceData permitType = null;
	
	
	/**
	 * @param id
	 */
	public Permit(Object id) {
		super(id);
	}

	/**
	 * 
	 */
	public Permit() {
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
	public String getNumber() {
		return number;
	}

	/**
	 * @return
	 */
	public Integer getPermitTypeId() {
		return permitTypeId;
	}

	/**
	 * @param string
	 */
	public void setNumber(String string) {
		number = string;
	}

	/**
	 * @param integer
	 */
	public void setPermitTypeId(Integer integer) {
		permitTypeId = integer;
	}

	/**
	 * @return
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param date
	 */
	public void setDueDate(Date date) {
		dueDate = date;
	}

	/**
	 * @return
	 */
	public ReferenceData getPermitType() {
		if( permitType == null ) {
			try {
				setPermitType(StaticDataManager.getPermitType(getPermitTypeId()));
			} catch(Throwable ex) {
				setPermitType(null);
			}
			
		}
		return permitType;
	}

	/**
	 * @param data
	 */
	public void setPermitType(ReferenceData data) {
		permitType = data;
	}
	
	public boolean hasNumber() {
		return getNumber() != null && getNumber().length() > 0;
	}
	
	public boolean isDue() {
		return getDueDate() == null || getDueDate().getTime() <= System.currentTimeMillis();
	}

}
