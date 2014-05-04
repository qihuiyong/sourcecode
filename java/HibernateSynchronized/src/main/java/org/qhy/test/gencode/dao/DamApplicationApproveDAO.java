package org.qhy.test.gencode.dao;

import org.hibernate.Session;

import org.qhy.test.gencode.base.BaseDamApplicationApproveDAO;


public class DamApplicationApproveDAO extends BaseDamApplicationApproveDAO implements org.qhy.test.gencode.dao.iface.DamApplicationApproveDAO {

	public DamApplicationApproveDAO () {}
	
	public DamApplicationApproveDAO (Session session) {
		super(session);
	}


}