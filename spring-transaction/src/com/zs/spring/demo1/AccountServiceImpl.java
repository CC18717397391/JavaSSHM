package com.zs.spring.demo1;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class AccountServiceImpl implements AccountService {
	
	//注入转账的DAO		xml配置<property name="accountDao" ref="accountDao" />
	private AccountDao accountDao;
	
	//注入事务管理的模板	<property name="transactionTemplate" ref="transactionTemplate" />
	private TransactionTemplate transactionTemplate;

	/**编程式事务管理需要在此处手动的修改代码
	 * @param out	:转出账号
	 * @param in	:转入账号
	 * @param money	:转账金额
	 */
	@Override
	public void transfer(final String out, final String in, final Double money) {
		
		//匿名内部类
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				accountDao.outMoney(out, money);	//匿名内部类中使用了外部的变量，外部变final就可以了
				//int i = 1/0;		
				accountDao.inMoney(in, money);
			}
			//使用事务将此三行代码绑定在一个事务当中，要么一起成功，要么一起失败回滚，错一个前面的都滚回去
		});
	}
	
	//两个属性的set方法
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
