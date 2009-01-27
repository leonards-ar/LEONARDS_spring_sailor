/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Profile.java
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
public class Profile extends PersistentObject implements Serializable {
	private String description = null;
	private Menu menu = null;
	private Integer DefaultMenuItemId = null;
	private boolean specialReserver = false;
	
	/**
	 * 
	 */
	public Profile() {
		super();
	}

	/**
	 * @param id
	 */
	public Profile(Object id) {
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
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @return
	 */
	public Menu getMenu() throws SpringException {
		if( menu == null ) {
			try {
				menu = DBHome.retrieveMenu(getIdAsInteger());
			} catch( Throwable ex ) {
				throw new SpringException("Could not retrieve profile menu.", ex);
			}
			
		}
		return menu;
	}

	/**
	 * @param menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public MenuItem getDefaultMenuItem(String defaultPage) throws SpringException {
		MenuItem item = getMenu().getMenuItem(getDefaultMenuItemId());
		return item != null ? item : new MenuItem(defaultPage);
	}

	/**
	 * @return
	 */
	public Integer getDefaultMenuItemId() {
		return DefaultMenuItemId;
	}

	/**
	 * @param integer
	 */
	public void setDefaultMenuItemId(Integer integer) {
		DefaultMenuItemId = integer;
	}

	/**
	 * @return
	 */
	public boolean isSpecialReserver() {
		return specialReserver;
	}

	/**
	 * @param b
	 */
	public void setSpecialReserver(boolean b) {
		specialReserver = b;
	}

}
