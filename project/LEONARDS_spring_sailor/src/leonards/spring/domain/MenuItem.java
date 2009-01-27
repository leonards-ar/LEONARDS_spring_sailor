/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: MenuItem.java
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
public class MenuItem extends PersistentObject implements Serializable {
	private String description = null;
	private String targetUrl = null;
	private String target = null;
	private Integer menuGroupId = null;
	private MenuGroup menuGroup = null;
	private Integer order = null;
	
	/**
	 * 
	 */
	public MenuItem() {
		super();
	}

	/**
	 * @param id
	 */
	public MenuItem(Object id) {
		super(id);
	}
	
	public MenuItem(String targetUrl) {
		this();
		setTargetUrl(targetUrl);
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
	public MenuGroup getMenuGroup() {
		if( menuGroup == null ) {
			try {
				setMenuGroup(DBHome.retrieveMenuGroup(getMenuGroupId()));
			} catch(Throwable ex) {
				setMenuGroup(null);
			}
			 
		}
		return menuGroup;
	}

	/**
	 * @return
	 */
	public Integer getMenuGroupId() {
		return menuGroupId;
	}

	/**
	 * @return
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param group
	 */
	public void setMenuGroup(MenuGroup group) {
		menuGroup = group;
	}

	/**
	 * @param integer
	 */
	public void setMenuGroupId(Integer integer) {
		menuGroupId = integer;
	}

	/**
	 * @param string
	 */
	public void setTarget(String string) {
		target = string;
	}

	/**
	 * @param string
	 */
	public void setTargetUrl(String string) {
		targetUrl = string;
	}

	/**
	 * @return
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param integer
	 */
	public void setOrder(Integer integer) {
		order = integer;
	}

}
