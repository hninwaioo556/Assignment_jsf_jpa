package com.mmit.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.entity.Course;
import com.mmit.entity.Student;

@Stateless
public class StudentService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void saveStudent(Student student) {
		if(student.getId()==0)
			em.persist(student); // persist
			
		else
			em.merge(student); //edit
	}
	
	public List<Student> findAll() {		
			
			return em.createNamedQuery("Student.getAll",Student.class).getResultList();
		}
	
		public Student findById(String id) {
			
			return em.find(Student.class, Integer.parseInt(id));
		}
		
		public void deleteStudent(int id) {
			Student c = em.find(Student.class, id); // managed state		
			em.remove(c);
			
		}

}
