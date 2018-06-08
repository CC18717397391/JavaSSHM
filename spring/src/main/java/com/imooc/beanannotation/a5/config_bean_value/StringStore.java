package com.imooc.beanannotation.a5.config_bean_value;

public class StringStore implements Store<String> {
	
	public void init() {
		System.out.println("a5.@bean init    : This is init.");
	}
	
	public void destroy() {
		System.out.println("a5.@bean destroy : This is destroy.");
	}
	
}
