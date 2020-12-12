package com.moon.crm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;

/**
 * 客户持久层实现类
 * 
 * @author MoonZero
 */
// 使用注解配置对象让spring管理
@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {
	// 依赖注入方式1：成员变量注入(以前)
	// @Autowired
	// private SessionFactory sessionFactory;

	// 依赖注入方式2：注入到set方法
	// 根据方法参数的类型去容器中找SessionFactory类型的对象，注入到方法的参数。
	@Autowired
	public void setSf(SessionFactory sessionFactory) {
		// 因为在父类中必须要先创建hibenrateTemplate对象，才能调用其方法
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 查询客户
	 */
	@Override
	public Customer findCustomerById(Long id) {
		Customer c = getHibernateTemplate().get(Customer.class, id);
		System.out.println(c);
		return c;
	}

}
