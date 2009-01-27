/*
 * Created on Aug 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Report {
	private String name = null;
	private String sql = null;
	private String poolName = null;
	private String key = null;
	
	/**
	 * 
	 */
	public Report() {
		super();
	}

	/**
	 * 
	 * @param name
	 * @param sql
	 * @param poolName
	 */
	public Report(String name, String sql, String poolName, String key) {
		super();
		setName(name);
		setSql(sql);
		setPoolName(poolName);
		setKey(key);
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getPoolName() {
		return poolName;
	}

	/**
	 * @return
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setPoolName(String string) {
		poolName = string;
	}

	/**
	 * @param string
	 */
	public void setSql(String string) {
		sql = string;
	}

	/**
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}

}
