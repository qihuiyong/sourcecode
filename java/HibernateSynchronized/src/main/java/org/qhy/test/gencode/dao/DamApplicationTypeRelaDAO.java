package org.qhy.test.gencode.dao;

import org.hibernate.Session;

import org.qhy.test.gencode.base.BaseDamApplicationTypeRelaDAO;


public class DamApplicationTypeRelaDAO extends BaseDamApplicationTypeRelaDAO implements org.qhy.test.gencode.dao.iface.DamApplicationTypeRelaDAO {

	public DamApplicationTypeRelaDAO () {}
	
	public DamApplicationTypeRelaDAO (Session session) {
		super(session);
	}


}