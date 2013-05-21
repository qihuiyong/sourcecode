package org.qhy.demo.hibernate.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.qhy.demo.hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends SessionFactoryUtils {
	@Autowired
	private SessionFactory sessionFactory;
	public List<User> getUserList() {
		Session session = this.openSession(sessionFactory);
		Query query= session.createQuery("From User");
		return query.list();
	}
	public void save(User t){
//		Session session = this.openSession(sessionFactory);
		Session session =this.sessionFactory.getCurrentSession();
//		Transaction tx= session.beginTransaction();
		session.save(t);
//		tx.rollback();
	}
	public void update(User t){
		Session session = this.openSession(sessionFactory);
		session.merge(t);
	}
}
