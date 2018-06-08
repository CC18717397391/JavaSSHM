package com.imooc.a7xml.resource;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 * Resource&ResourceLoader是IOC加载资源文件【spring的bean配置文件】的时候用到的类，针对资源文件的统一接口
 * 通过spring加载资源文件的时候可以使用resources完成
 */
public class MoocResource implements ApplicationContextAware  {
	
	//通过实现ApplicationContextAware接口来获得applicationContext，
	//此处不明可参考Aware，重点在最下方resource.getFilename()
	private ApplicationContext applicationContext;
	
	//重写该方法之后初始化调用覆盖后的方法
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void resource() throws IOException {
		//通过applicationContext获取getResource
		Resource resource = applicationContext.getResource("config.xml");
			//"config.txt" 当没有前缀的时候，是依赖于applicationContext【本身就是根据classpath】，
			//"classpath:config.txt"	根据classpath
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}

}
