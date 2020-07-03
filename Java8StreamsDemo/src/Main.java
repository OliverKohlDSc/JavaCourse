import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Person> personen = new ArrayList<>();

		personen.add(new Person("Alfred","Maier"));
		personen.add(new Person("Jane","Doe"));
		personen.add(new Person("Joe","Miller"));
		personen.add(new Person("Albert","Schweizer"));
		personen.add(new Person("Lisa","Müller"));
		personen.add(new Person("Sarah","Smith"));
		
		// Java 8 Streams / Stream API
		personen.stream()
			.filter(element -> element.getVorname().equals("Jane"))
			
			.collect(Collectors.toList());
	}
}
