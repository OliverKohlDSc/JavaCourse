import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Sample2 {
	private static final ExecutorService executorService =
			Executors.newFixedThreadPool(3);
	
	private static final HttpClient httpClient = 
			HttpClient.newBuilder()
			.executor(executorService)
			.version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10))
			.build();
	
	public static void call() throws Exception {
		List<URI> targets = Arrays.asList(
				new URI("https://httpbin.org/get?name=Albert"),
				new URI("https://httpbin.org/get?name=Joseph"),
				new URI("https://httpbin.org/get?name=Martin")
			);
		
		List<CompletableFuture<String>> result = targets.stream()
				.map(uri -> httpClient.sendAsync(
						HttpRequest.newBuilder(uri)
							.GET()
							.setHeader("User-Agent", "Java")
							.build(),
						HttpResponse.BodyHandlers.ofString()
					).thenApply(response -> response.body()))
				.collect(Collectors.toList());
		
		for (CompletableFuture<String> future : result) {
			System.out.println(future.get());
		}
	}
}