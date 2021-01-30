package com.mmit.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.mmit.entity.Course;
import com.mmit.service.CourseService;

@Named
@RequestScoped
public class CourseBean {
	
	private Course course;
	private List<Course> courseList;
	
	@EJB
	private CourseService service;
	
	@PostConstruct
	public void init() {
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		Map<String, String> params = context.getRequestParameterMap();
		String id = params.get("courseid");
		if(id==null) 
			course = new Course();
		else
			course=service.findById(id);
		courseList = service.findAll();
	}
	
	public String save () {
		//System.out.println("id: "+course.getId());
		//System.out.println("name: "+course.getName());
		service.saveCourse(course);
		return "home?faces-redirect=true";
	}
	
	public String delete (int id) {
		service.deleteCourse (id);
		return "home?faces-redirect=true";
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getcourseList() {
		return courseList;
	}
	public void setcourseList(List<Course> courseList) {
		this.courseList = courseList;
	}	

}
