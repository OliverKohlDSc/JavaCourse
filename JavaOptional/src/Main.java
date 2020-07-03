import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Optional<String>> listOfOptionals = Arrays.asList(
				Optional.empty(),
				Optional.of("Doe"),
				Optional.of("Smith")
				);
		
		// Variante 1
		/*
		List<String> filteredList = listOfOptionals.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		*/
		
		// Variant 2
		/*
		List<String> filteredList = listOfOptionals.stream()
				.flatMap(element -> element.isPresent() ? Stream.of(element.get()) : Stream.empty())
				.collect(Collectors.toList());
		*/
		
		// Variant 3
		/*
		List<String> filteredList = listOfOptionals.stream()
				.flatMap(element -> element.map(Stream::of).orElseGet(Stream::empty))
				.collect(Collectors.toList());
		*/
		
		
		List<String> filteredList = listOfOptionals.stream()
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

}
