package refactoringtask.human;

public class UniversityPerson extends Human {
	
	
    public UniversityPerson(String name, int age) {
		super(name, age);
	}

	private University university;
    
    
    
    
    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
