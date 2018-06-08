package com.imooc.beanannotation.a5.config_bean_value;

import org.springframework.context.annotation.Bean;

public class MyDriverManager {
	
	public MyDriverManager(String url, String userName, String password) {
		System.out.println("a5.@configuration_@bean_@value url : " + url);
		System.out.println("a5.@configuration_@bean_@value userName: " + userName);
		System.out.println("a5.@configuration_@bean_@value password: " + password);
	}

}
