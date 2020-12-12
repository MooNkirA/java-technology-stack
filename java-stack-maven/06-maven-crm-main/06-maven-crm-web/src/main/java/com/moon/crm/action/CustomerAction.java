package com.moon.crm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.moon.crm.entity.Customer;
import com.moon.crm.service.ICustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户action类
 * 
 * @author MoonZero
 */
// 使用注解配置spring容器管理
@Controller
// 配置为多例模式
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private static final long serialVersionUID = 1L;
	// 使用注入service层
	@Autowired
	private ICustomerService customerService;

	// Customer模型驱动，封装页面提交数据
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	// 提供get/set方法
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 根据主键获取客户信息
	 * 
	 * @return String
	 */
	public String queryCustomer() {
		// 调用业务层方法，将查询的结果放到值栈
		if (customer.getCustId() != null) {
			customer = customerService.findCustomerById(customer.getCustId());
		}
		return "queryCustomer";
	}
}
