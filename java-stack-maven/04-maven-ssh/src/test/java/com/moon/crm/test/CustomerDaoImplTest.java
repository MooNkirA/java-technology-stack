package com.moon.crm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;

/**
 * 客户持久层测试类
 * 
 * @author MoonZero
 */
public class CustomerDaoImplTest {

	@Test
	public void testFindCustomerById() {
		// 获取spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		// 获取dao层对象
		ICustomerDao cd = (ICustomerDao) ac.getBean("customerDao");
		
		// 调用方法
		Customer c = cd.findCustomerById(1L);
		System.out.println(c);
	}
}
