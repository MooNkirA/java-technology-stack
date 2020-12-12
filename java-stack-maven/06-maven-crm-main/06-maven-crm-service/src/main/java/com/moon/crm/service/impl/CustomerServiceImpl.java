package com.moon.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;
import com.moon.crm.service.ICustomerService;

/**
 * 客户业务逻辑层实现类
 * 
 * @author MoonZero
 */
// 使用注解配置spring管理对象
@Service
public class CustomerServiceImpl implements ICustomerService {

	// 定义dao层注入
	@Autowired
	private ICustomerDao customerDao;

	/**
	 * 查询客户
	 */
	@Override
	public Customer findCustomerById(Long custId) {
		return customerDao.findCustomerById(custId);
	}
}
