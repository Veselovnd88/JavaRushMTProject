package refactoringtask.human;


public class Worker extends Human {

    private double salary;
    public String company;

    public Worker(String name, int age) {
    	super(name,age);
    	}

    public void live() {

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
