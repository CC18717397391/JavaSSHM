package com.imooc.beanannotation.a4.order_resource;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
//order指定顺序，指放入collection的顺序。只针对list有效，其他无效
//list输出顺序，便是放入顺序，hashset和hashmap的输出顺序是无序的，treemap和treeset的输出顺序是自动顺序，但也可自己实现排序
@Component
public class BeanImplOne implements BeanInterface {

}
