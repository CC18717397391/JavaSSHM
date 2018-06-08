package com.clear.hibernate;

import org.hibernate.Session;

import com.clear.hibernate.entity.Grade;
import com.clear.hibernate.entity.Student;
import com.clear.hibernate.util.HibernateUtil;


/*
 * 单向多对一（学生--->班级）
 * 实际上已经建立了双向多对一
 * 当同时单项一对多和单项多对一就建立了双向的关系，可以从班级查找到学生，也可以从学生查找到班级
 */
public class A4_TestManyToOne {
	public static void main(String[] args) {
		findGradeByStudent();
	}
	
	//查询学生所在班级信息
	public static void findGradeByStudent(){
		Session session=HibernateUtil.getSession();
		Student stu = (Student) session.get(Student.class, 2);
		System.out.println(stu);
		Grade g = stu.getGrade();
		System.out.println(g);
		HibernateUtil.closeSession(session);
	}
	
}
