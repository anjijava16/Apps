/**
 * 
 */
package studentapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import studentapp.form.Student;

/**
 * @author Home
 *
 */
@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStudent(Student student) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(student);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents(){
		return this.sessionFactory.getCurrentSession().createQuery("from Student").list();
		
	}

	@Override
	public void deleteStudent(Integer id) {
		Student student = (Student) sessionFactory.getCurrentSession().load(Student.class, id);
		if(null != student){
			this.sessionFactory.getCurrentSession().delete(student);
		}
		
	}

	@Override
	public void updateStudent(Student student) {
		this.sessionFactory.getCurrentSession().update(student);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentById(Integer id) {
		List<Student> list = sessionFactory.getCurrentSession().createQuery("from Student s where s.id =:id").setParameter("id", id).list();
		return list.size() > 0 ? (Student)list.get(0): null;
	}
}
