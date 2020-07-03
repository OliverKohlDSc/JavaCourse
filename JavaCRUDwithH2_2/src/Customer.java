import java.util.Date;

public class Customer {
	private int id;
	private String name;
	private int age;
	private String address;
	private int salary;
	private Date startDate;
	
	public Customer() {
		
	}
	
	public Customer(int id, String name, int age, String address, int salary, Date startDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.salary = salary;
		this.startDate = startDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public String toString() {
		return "[id:"+id+", name:"+name+", age:"+age+", address:"+address+", salary:"+salary+", startDate:" + startDate+"]"; 
	}
}