/**
 * 
 */
package studentapp.dao;

import java.util.List;

import studentapp.form.Student;

/**
 * @author Home
 *
 */
public interface StudentDAO {
	public List<Student> getAllStudents();
	public void addStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Integer id);
	public Student getStudentById(Integer id);
}
