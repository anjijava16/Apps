package studentapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import studentapp.form.Student;
import studentapp.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getAllEmployees(ModelMap map){
		map.addAttribute("student", new Student());
		map.addAttribute("studentList", studentService.getAllStudents());
		return "studentList";
	}
	
	@RequestMapping("/edit/{id}")
	public String editStudent(@PathVariable("id") Integer id, Map<String,Object> map){
		map.put("student", studentService.getStudentById(id));
		map.put("studentList", studentService.getAllStudents());
		return "studentList";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addStudent(@ModelAttribute(value="student") Student student, BindingResult result){
		//if(null == student.getId()){
			studentService.addStudent(student);
		//}
		//else{
			//studentService.updateStudent(student);
		//}
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id){
		studentService.deleteStudent(id);
		return "redirect:/";
	}
	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
}

