package com.imooc.beanannotation.a3.required;

import org.springframework.beans.factory.annotation.Required;
/**
 * 该注解适用于bean属性的setter方法上，它表明受影响的 bean属性在配置时必须放在 XML配置文件中并且给定value值
 * 
 */
public class Mail {

	private String postcode;// 邮编
	private String phone; // 电话

	public Mail() {
	}

	public Mail(String postcode, String phone, String address) {
		this.postcode = postcode;
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}
	
	@Required
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}
	
	@Required
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Mail [postcode=" + postcode + ", phone=" + phone + "]";
	}

}
