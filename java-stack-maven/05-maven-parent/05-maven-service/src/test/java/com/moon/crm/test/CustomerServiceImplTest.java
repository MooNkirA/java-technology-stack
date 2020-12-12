package com.moon.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moon.crm.entity.Customer;
import com.moon.crm.service.ICustomerService;

/**
 * 客户业务逻辑测试类
 * 
 * @author MoonZero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml", "classpath:applicationContext.xml",
		"classpath:applicationContext-service.xml" })
public class CustomerServiceImplTest {
	// 使用注解注入属性
	@Autowired
	private ICustomerService customerService;

	@Test
	public void testFindCustomerById() {
		System.out.println(customerService);
		Customer c = customerService.findCustomerById(1L);
		System.out.println(c);
	}
}
