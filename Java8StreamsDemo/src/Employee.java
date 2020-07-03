// Plain old Java object
// POJO
public class Employee {
    private int id;
    private String name;
    private float salary;

    public Employee(int id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getSalary() {
        return this.salary;
    }

    public void incrementSalary(float amount) {
        if (amount <= 0)
            return;

        this.salary += amount;
    }
    @Override
    public String toString() {
        return "id: "+ this.id + ", name: " + this.name + ", sal: " + this.salary;
    }
}