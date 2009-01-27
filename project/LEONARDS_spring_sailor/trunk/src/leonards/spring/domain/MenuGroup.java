/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: MenuGroup.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Vector;
import leonards.common.base.*;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class MenuGroup extends PersistentObject implements Serializable {
	private Vector menuItems = null;
	private String description = null;
	
	/**
	 * 
	 */
	public MenuGroup() {
		super();
	}

	/**
	 * @param id
	 */
	public MenuGroup(Object id) {
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
	public Vector getMenuItems() {
		if( menuItems == null ) {
			menuItems = new Vector();
		}
		return menuItems;
	}

	/**
	 * @param vector
	 */
	public void setMenuItems(Vector vector) {
		menuItems = vector;
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
	
	public void addMenuItem(MenuItem item) {
		getMenuItems().addElement(item);	
	}
	
	public MenuItem getMenuItem(Integer id) {
		int idx = getMenuItems().indexOf(new MenuItem(id));
		return idx >= 0 ? (MenuItem)getMenuItems().elementAt(idx) : null;
	}
}
