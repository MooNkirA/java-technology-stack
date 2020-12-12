package com.moon.crm.dao;

import com.moon.crm.entity.Customer;

/**
 * 客户持久层接口
 * 
 * @author MoonZero
 */
public interface ICustomerDao {
	/**
	 * 查询客户
	 * 
	 * @param id
	 * @return Customer
	 */
	public Customer findCustomerById(Long id);
}
