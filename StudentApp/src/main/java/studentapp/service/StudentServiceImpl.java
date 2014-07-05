/**
 * 
 */
package studentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import studentapp.dao.StudentDAO;
import studentapp.form.Student;

/**
 * @author Home
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentDAO;

	@Override
	@Transactional
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
		
	}
	
	@Override
	@Transactional
	public List<Student> getAllStudents(){
		return studentDAO.getAllStudents();
	}

	@Override
	@Transactional
	public void deleteStudent(Integer id) {
		studentDAO.deleteStudent(id);
		
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
		
	}

	@Override
	@Transactional
	public Student getStudentById(Integer id) {
		return studentDAO.getStudentById(id);
	}
}
