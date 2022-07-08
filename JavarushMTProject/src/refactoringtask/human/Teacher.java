package refactoringtask.human;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Human {

    private int numberOfStudents;
    private String university;

    public Teacher(String name, int age, int numberOfStudents) {
        super(name,age);
        this.name = name;
        this.age = age;
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String getPosition() {
    	return "Преподаватель";
    }
    public void live() {
        teach();
    }

    public void teach() {
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

}