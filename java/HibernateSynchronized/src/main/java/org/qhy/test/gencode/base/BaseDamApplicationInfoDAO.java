package org.qhy.test.gencode.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.qhy.test.gencode.dao.iface.DamApplicationInfoDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseDamApplicationInfoDAO extends org.qhy.test.gencode.dao._RootDAO {

	public BaseDamApplicationInfoDAO () {}
	
	public BaseDamApplicationInfoDAO (Session session) {
		super(session);
	}

	// query name references


	public static DamApplicationInfoDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static DamApplicationInfoDAO getInstance () {
		if (null == instance) instance = new org.qhy.test.gencode.dao.DamApplicationInfoDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return org.qhy.test.gencode.DamApplicationInfo.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a org.qhy.test.gencode.DamApplicationInfo
	 */
	public org.qhy.test.gencode.DamApplicationInfo cast (Object object) {
		return (org.qhy.test.gencode.DamApplicationInfo) object;
	}

	public org.qhy.test.gencode.DamApplicationInfo get(java.lang.String key)
	{
		return (org.qhy.test.gencode.DamApplicationInfo) get(getReferenceClass(), key);
	}

	public org.qhy.test.gencode.DamApplicationInfo get(java.lang.String key, Session s)
	{
		return (org.qhy.test.gencode.DamApplicationInfo) get(getReferenceClass(), key, s);
	}

	public org.qhy.test.gencode.DamApplicationInfo load(java.lang.String key)
	{
		return (org.qhy.test.gencode.DamApplicationInfo) load(getReferenceClass(), key);
	}

	public org.qhy.test.gencode.DamApplicationInfo load(java.lang.String key, Session s)
	{
		return (org.qhy.test.gencode.DamApplicationInfo) load(getReferenceClass(), key, s);
	}

	public org.qhy.test.gencode.DamApplicationInfo loadInitialize(java.lang.String key, Session s) 
	{ 
		org.qhy.test.gencode.DamApplicationInfo obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationInfo> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationInfo> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationInfo> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param damApplicationInfo a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo)
	{
		return (java.lang.String) super.save(damApplicationInfo);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param damApplicationInfo a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo, Session s)
	{
		return (java.lang.String) save((Object) damApplicationInfo, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param damApplicationInfo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo)
	{
		saveOrUpdate((Object) damApplicationInfo);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param damApplicationInfo a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo, Session s)
	{
		saveOrUpdate((Object) damApplicationInfo, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param damApplicationInfo a transient instance containing updated state
	 */
	public void update(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo) 
	{
		update((Object) damApplicationInfo);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param damApplicationInfo a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo, Session s)
	{
		update((Object) damApplicationInfo, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.String id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param damApplicationInfo the instance to be removed
	 */
	public void delete(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo)
	{
		delete((Object) damApplicationInfo);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param damApplicationInfo the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo, Session s)
	{
		delete((Object) damApplicationInfo, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (org.qhy.test.gencode.DamApplicationInfo damApplicationInfo, Session s)
	{
		refresh((Object) damApplicationInfo, s);
	}


}