package com.mmit.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.entity.Course;

@Stateless
public class CourseService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void saveCourse(Course course) {
		if(course.getId()==0)
			em.persist(course); // persist
			
		else
			em.merge(course); //edit
	}

	public List<Course> findAll() {		
		
		return em.createNamedQuery("Course.getAll",Course.class).getResultList();
	}

	public Course findById(String id) {
		
		return em.find(Course.class, Integer.parseInt(id));
	}

	public void deleteCourse(int id) {
		Course c = em.find(Course.class, id); // managed state		
		em.remove(c);
		
	}
}
