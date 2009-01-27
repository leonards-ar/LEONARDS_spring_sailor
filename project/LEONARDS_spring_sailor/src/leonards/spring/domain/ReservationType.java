/*
 * Created on Jul 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

import java.io.Serializable;

import leonards.common.base.NestedException;
import leonards.common.base.PersistentObject;

/**
 * @author mcapurro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReservationType extends PersistentObject implements Serializable {
	private String description = null;
	private Integer shiftsQty = null;
	private Double extraValue = null;
	private boolean enabled = true;
	
	/**
	 * 
	 */
	public ReservationType() {
		super();
	}

	/**
	 * @param arg0
	 */
	public ReservationType(Object id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see leonards.common.base.PersistentObject#update()
	 */
	protected void update() throws NestedException {

	}

	/* (non-Javadoc)
	 * @see leonards.common.base.PersistentObject#insert()
	 */
	protected void insert() throws NestedException {

	}

	/* (non-Javadoc)
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
	public Integer getShiftsQty() {
		return shiftsQty;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param integer
	 */
	public void setShiftsQty(Integer integer) {
		shiftsQty = integer;
	}

	/**
	 * @return Returns the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return Returns the extraValue.
	 */
	public Double getExtraValue() {
		return extraValue;
	}

	/**
	 * @param extraValue The extraValue to set.
	 */
	public void setExtraValue(Double extraValue) {
		this.extraValue = extraValue;
	}

}
