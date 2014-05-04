package org.qhy.test.gencode.dao;

import org.hibernate.Session;

import org.qhy.test.gencode.base.BaseDamApplicationInfoDAO;


public class DamApplicationInfoDAO extends BaseDamApplicationInfoDAO implements org.qhy.test.gencode.dao.iface.DamApplicationInfoDAO {

	public DamApplicationInfoDAO () {}
	
	public DamApplicationInfoDAO (Session session) {
		super(session);
	}


}