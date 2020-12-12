package com.moon.crm.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;

/**
 * 客户持久层实现类
 * 
 * @author MoonZero
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {

	/**
	 * 查询客户
	 */
	@Override
	public Customer findCustomerById(Long id) {
		return getHibernateTemplate().get(Customer.class, id);
	}

}
