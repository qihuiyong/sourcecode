package org.qhy.test.gencode.base;

import org.hibernate.Session;
import org.qhy.test.gencode.dao.iface.DamApplicationTypeRelaDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseDamApplicationTypeRelaDAO extends org.qhy.test.gencode.dao._RootDAO {

	public BaseDamApplicationTypeRelaDAO () {}
	
	public BaseDamApplicationTypeRelaDAO (Session session) {
		super(session);
	}

	// query name references


	public static DamApplicationTypeRelaDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static DamApplicationTypeRelaDAO getInstance () {
		if (null == instance) instance = new org.qhy.test.gencode.dao.DamApplicationTypeRelaDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return org.qhy.test.gencode.DamApplicationTypeRela.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a org.qhy.test.gencode.DamApplicationTypeRela
	 */
	public org.qhy.test.gencode.DamApplicationTypeRela cast (Object object) {
		return (org.qhy.test.gencode.DamApplicationTypeRela) object;
	}


/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationTypeRela> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationTypeRela> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<org.qhy.test.gencode.DamApplicationTypeRela> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}


	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param damApplicationTypeRela a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela)
	{
		saveOrUpdate((Object) damApplicationTypeRela);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param damApplicationTypeRela a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela, Session s)
	{
		saveOrUpdate((Object) damApplicationTypeRela, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param damApplicationTypeRela a transient instance containing updated state
	 */
	public void update(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela) 
	{
		update((Object) damApplicationTypeRela);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param damApplicationTypeRela a transient instance containing updated state
	 * @param the Session
	 */
	public void update(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela, Session s)
	{
		update((Object) damApplicationTypeRela, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param damApplicationTypeRela the instance to be removed
	 */
	public void delete(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela)
	{
		delete((Object) damApplicationTypeRela);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param damApplicationTypeRela the instance to be removed
	 * @param s the Session
	 */
	public void delete(org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela, Session s)
	{
		delete((Object) damApplicationTypeRela, s);
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
	public void refresh (org.qhy.test.gencode.DamApplicationTypeRela damApplicationTypeRela, Session s)
	{
		refresh((Object) damApplicationTypeRela, s);
	}


}