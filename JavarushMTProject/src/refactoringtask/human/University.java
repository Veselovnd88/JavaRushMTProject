package refactoringtask.human;

import java.util.ArrayList;
import java.util.List;

public class University {

	private List<Student> students = new ArrayList<>();
	private String name;
	private int age;

	public University(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudentWithAverageGrade(double value) {
		if (!students.isEmpty()) {
			for (Student s : students) {
				if (s.getAverageGrade() == value) {
					return s;
				}
			}
		}
		return null;
	}

	public Student getStudentWithMaxAverageGrade() {
		if (!students.isEmpty()) {
			Student max = students.get(0);
			for (Student s : students) {
				if (s.getAverageGrade() > max.getAverageGrade()) {
					max = s;
				}
			}
			return max;
		}
		return null;
	}

	public Student getStudentWithMinAverageGrade() {
		if (!students.isEmpty()) {
			Student min = students.get(0);
			for (Student s : students) {
				if (s.getAverageGrade() < min.getAverageGrade()) {
					min = s;
				}
			}
			return min;
		}
		return null;
	}

	public void expel(Student student) {
		students.remove(student);

	}
}