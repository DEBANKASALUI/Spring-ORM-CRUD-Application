package com.spring.orm.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entity.Student;

public class StudentDao {

	private HibernateTemplate hibernateTemplate;

//	INSERT data
	@Transactional
	public int insert(Student student) {
		Integer i = (Integer) this.hibernateTemplate.save(student);
		return i;
	}

//	 SELECT single student/object
	public Student getStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		return student;
	}

//	 SELECT multiple students/object
	public List<Student> getAllStudents() {
		List<Student> list = this.hibernateTemplate.loadAll(Student.class);
		return list;

	}

//	 DELETE data
	@Transactional
	public void delete(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		this.hibernateTemplate.delete(student);
	}

//	UPDATE data
	@Transactional
	public void change(Student student) {
		this.hibernateTemplate.update(student);
	}
	
//	UPDATE
	@Transactional
	public void updateStudent(int id, String name, String city)
	{
		Student student = this.hibernateTemplate.get(Student.class, id);
		student.setName(name);
		student.setCity(city);
		this.hibernateTemplate.save(student);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
