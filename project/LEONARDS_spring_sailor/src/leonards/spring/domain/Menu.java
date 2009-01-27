/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: Menu.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class Menu implements Serializable {
	private Vector menuGroups = null;
	
	/**
	 * 
	 */
	public Menu() {
		super();
	}

	/**
	 * @return
	 */
	public Vector getMenuGroups() {
		if( menuGroups == null ) {
			menuGroups = new Vector();
		}
		return menuGroups;
	}

	/**
	 * @param vector
	 */
	public void setMenuGroups(Vector vector) {
		menuGroups = vector;
	}

	public void addMenuGroup(MenuGroup group) {
		getMenuGroups().addElement(group);
	}
	
	public MenuItem getMenuItem(Integer id) {
		Iterator groups = getMenuGroups().iterator();
		MenuGroup aMenuGroup = null;
		MenuItem theMenuItem = null;
		
		while( groups.hasNext() ) {
			aMenuGroup = (MenuGroup)groups.next();
			if( (theMenuItem = aMenuGroup.getMenuItem(id) ) != null ) {
				return theMenuItem;
			}
		}
		
		return null;
	}

}
