import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Sample1 {
	public static void call() throws Exception  {
		HttpClient httpClient = HttpClient.newHttpClient();
		System.out.println(httpClient.version());
		
		httpClient = HttpClient
				.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.connectTimeout(Duration.ofSeconds(10))
				.build();
		
		HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.uri(URI.create("https://httpbin.org/get"))
				.setHeader("User-Agent", "Testing GET-Request")
				.build();
		
		CompletableFuture<HttpResponse<String>> response =
		httpClient.sendAsync(request, 
				HttpResponse.BodyHandlers.ofString());
		
		String result = response
				.thenApply(HttpResponse::body)
				.get(5, TimeUnit.SECONDS);
		
		System.out.println(result);
	}
}
