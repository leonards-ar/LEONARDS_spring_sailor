/*
 * Created on Aug 1, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.web.actions;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import leonards.common.sql.DBException;
import leonards.common.web.framework.WebActionCommand;
import leonards.common.web.framework.WebActionInvalidSessionException;
import leonards.spring.domain.DBHome;
import leonards.spring.domain.User;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class SpringActionCommand extends WebActionCommand {
	private static Vector persistantSessionAttributes = null;
	public static int DEFAULT_ROWS_PER_PAGE = 15;
	public static int DEFAULT_MAX_PAGE_SHORTCUTS = 8;
	
	/**
	 * 
	 */
	public SpringActionCommand() {
		super();
	}

	protected void cleanSession() {
		Enumeration keys = getSession().getAttributeNames();
		String aKey;
		while(keys.hasMoreElements()) {
			aKey = keys.nextElement().toString();
			if(!getPersistantSessionAttributes().contains(aKey)) {
				getSession().removeAttribute(aKey);
				removeFromSession(aKey);		
			}
		}
	}
	
	private static Vector getPersistantSessionAttributes() {
		if(persistantSessionAttributes == null) {
			persistantSessionAttributes = new Vector();
			persistantSessionAttributes.addElement("user");
		}
		return persistantSessionAttributes;
	}
	
	/* (non-Javadoc)
	 * @see leonards.common.web.framework.WebActionCommand#checkSession()
	 */
	public void checkSession() throws WebActionInvalidSessionException {
		// Check all persistent attributes
		Iterator persistantAttrs = getPersistantSessionAttributes().iterator();
		String anAttr;
		while(persistantAttrs.hasNext()) {
			anAttr = persistantAttrs.next().toString();
			if(getFromSession(anAttr) == null) {
				throw new WebActionInvalidSessionException("Attribute [" + anAttr + "] not present in session");
			}
		}
		// Check that user is logged on
		User user = (User)getFromSession("user");
		if(user==null || !user.isLoggedOn()) {
			throw new WebActionInvalidSessionException("No logged user");
		}
	}
	
	protected void refreshUser() {
		User user = (User)getFromSession("user");
		try {
			if( user != null ) {
				User newUser = DBHome.retrieveUser(user.getIdAsInteger());
				if( newUser != null ) {
					putInSession("user", newUser);
				}
			}
		} catch( DBException ex ) {
			// Do nothing, just leave unmodified
			// user
		}
	}
}
