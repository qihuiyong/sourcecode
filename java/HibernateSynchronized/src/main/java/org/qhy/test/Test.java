package org.qhy.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.qhy.test.gencode.DamApplicationInfo;
import org.qhy.test.gencode.dao.DamApplicationInfoDAO;


public class Test {
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();

		Session session = sf.openSession();
		//自动生成的DAO类
		DamApplicationInfoDAO dao = new DamApplicationInfoDAO(session);
		List<DamApplicationInfo> list = dao.findAll();
		System.out.println("size===========>"+list.size());
		if(session.isOpen()){
			System.out.println("关闭sesion");
			session.close();
		}else{
			System.out.println("sesion已经关闭");
		}
		sf.close();
	}

}
