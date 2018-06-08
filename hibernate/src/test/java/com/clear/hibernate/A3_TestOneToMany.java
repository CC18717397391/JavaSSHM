package com.clear.hibernate;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.clear.hibernate.entity.Grade;
import com.clear.hibernate.entity.Student;
import com.clear.hibernate.util.HibernateUtil;


/*
 * 单向一对多关系关系（班级--->学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联的方向
 */
public class A3_TestOneToMany {
	
	public static void main(String[] args) {
		//add();
		//findStudentsByGrade();
		//update();
		//delete();	
		delete2();	
	}
	
	//将学生添加到班级
	public static void add(){
		Grade g=new Grade("Java一班", "Java软件开发一班");
		Student stu1=new Student("张三", "男");
		Student stu2=new Student("穆女神", "女");
		
		//如果希望在学生表中添加对应的班级编号，需要在班级中添加学生，建立关联关系
		g.getStudents().add(stu1);
		g.getStudents().add(stu2);
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();	//开启事务
		session.save(g);//因为是级联关系，所以可以全保存
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//查询班级中包含的学生
	public static void findStudentsByGrade(){
		Session session=HibernateUtil.getSession();
		
		Grade grade=(Grade) session.get(Grade.class, 1);
		System.out.println(grade.getGname()+","+grade.getGdesc());
		//通过班级信息反向拿到班级内的学生
		Set<Student> students=grade.getStudents();
		for(Student stu:students){
			System.out.println(stu.getSname()+","+stu.getSex());
		}
	}

	//修改学生信息
	public static void update(){
		Grade g=new Grade("Java二班", "Java软件开发二班");
		
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Student stu=(Student) session.get(Student.class, 1);
		g.getStudents().add(stu);
		session.save(g);
		tx.commit();
		HibernateUtil.closeSession(session);
	}

	//删除学生信息
	public static void delete(){
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Student stu=(Student) session.get(Student.class, 2);
		session.delete(stu);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//删除学生信息
	public static void delete2(){
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		Grade stu=(Grade) session.get(Grade.class, 3);
		session.delete(stu);//因为配置的级联是save-update，所以没有删除学生对象
		tx.commit();
		HibernateUtil.closeSession(session);
	}
}
