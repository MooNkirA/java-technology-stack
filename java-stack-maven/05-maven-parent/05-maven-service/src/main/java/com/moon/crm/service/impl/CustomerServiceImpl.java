package com.moon.crm.service.impl;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;
import com.moon.crm.service.ICustomerService;

/**
 * 客户业务逻辑层实现类
 * 
 * @author MoonZero
 */
public class CustomerServiceImpl implements ICustomerService {

	// 定义dao层属性
	private ICustomerDao customerDao;

	// 提供set方法，用于属性注入
	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 查询客户
	 */
	@Override
	public Customer findCustomerById(Long custId) {
		return customerDao.findCustomerById(custId);
	}
}
