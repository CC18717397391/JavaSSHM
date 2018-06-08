package com.imooc.beanannotation.a6.jsr_inject_name;

import javax.inject.Named;

import org.springframework.stereotype.Repository;

@Named("jsrDAOTest")
public class JsrDAO {
	
	public void save() {
		System.out.println("a6.@resource_@inject JsrDAO调用了.");
	}
	
}
