package com.moon.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moon.crm.dao.ICustomerDao;
import com.moon.crm.entity.Customer;

/**
 * 客户持久层测试
 * 
 * @author MoonZero
 */
// 使用注解整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
// 使用注解导入配置文件
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-dao.xml" })
public class CustomerDaoImplTest {
	// 使用注解配置属性注入
	@Autowired
	private ICustomerDao customerDao;

	@Test
	public void testFindCustomerById() {
		Customer c = customerDao.findCustomerById(1L);
		System.out.println(c);
	}
}
