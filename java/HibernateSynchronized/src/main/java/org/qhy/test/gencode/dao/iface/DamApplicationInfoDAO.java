package org.qhy.test.gencode.dao.iface;

import java.io.Serializable;

public interface DamApplicationInfoDAO {

	public java.util.List<org.qhy.test.gencode.DamApplicationInfo> findAll ();


	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param damApplicationInfo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param damApplicationInfo a transient instance containing updated state
	 */
	public void update(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param damApplicationInfo the instance to be removed
	 */
	public void delete(org.qhy.test.gencode.DamApplicationInfo damApplicationInfo);


}