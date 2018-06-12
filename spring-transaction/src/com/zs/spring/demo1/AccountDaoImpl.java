package com.zs.spring.demo1;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
	
	//继承该类会自动注入模板，在xml配置<property name="dataSource" ref="dataSource" />会自动创建jdbc的模板，然后再本类调用
	
	/**
	 * @param out	:转出账号
	 * @param money	:转账金额
	 */
	@Override
	public void outMoney(String out, Double money) {
		String sql = "update account set money = money-? where name = ?";
		this.getJdbcTemplate().update(sql, money, out);
	}

	/**
	 * @param in	:转入账号
	 * @param money	:转账金额
	 */
	@Override
	public void inMoney(String in, Double money) {
		String sql = "update account set money = money+? where name = ?";
		this.getJdbcTemplate().update(sql,money,in);
	}

}
