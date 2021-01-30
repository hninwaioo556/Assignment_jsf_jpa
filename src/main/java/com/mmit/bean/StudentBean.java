package com.mmit.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.mmit.entity.Student;
import com.mmit.service.StudentService;

@Named
@RequestScoped
public class StudentBean {

	private Student student;
	private List<Student> studentList;
	
	@EJB
	private StudentService service;
	
	@PostConstruct
	public void init() {
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		Map<String, String> params = context.getRequestParameterMap();
		String id = params.get("studentid");
		if(id==null) 
			student = new Student();
		else
			student=service.findById(id);
		studentList = service.findAll();
	}
	
	public String save () {
		//System.out.println("id: "+student.getId());
		//System.out.println("name: "+student.getName());
		service.saveStudent(student);
		return "student?faces-redirect=true";
	}
	
	public String delete (int id) {
		service.deleteStudent (id);
		return "student?faces-redirect=true";
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	
}
