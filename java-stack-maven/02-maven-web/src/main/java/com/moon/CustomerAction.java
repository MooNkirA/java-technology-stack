package com.moon;

import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport {
	private static final long serialVersionUID = 5613771945570318158L;

	private Long custId;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	// 查询客户信息
	public String queryCustomer() {
		System.out.println("请求客户id：" + custId);
		return SUCCESS;
	}
}
