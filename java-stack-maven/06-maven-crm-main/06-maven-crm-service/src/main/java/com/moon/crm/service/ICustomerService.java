package com.moon.crm.service;

import com.moon.crm.entity.Customer;

/**
 * 客户业务逻辑层接口
 * 
 * @author MoonZero
 */
public interface ICustomerService {

	/**
	 * 查询客户
	 * 
	 * @param custId
	 * @return Customer
	 */
	Customer findCustomerById(Long custId);
}
