import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Sample3 {
	private static List<CompletableFuture<Void>> asyncPushRequests = new ArrayList<CompletableFuture<Void>>();
	public static void call() {
		HttpClient client = HttpClient.newHttpClient();
		//HttpResponse 
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://http2.golang.org/serverpush")
				.build();
		
				client.sendAsync(request,
						   HttpResponse.BodyHandlers.ofString(), pushPromiseHandler())
						      .thenApply(HttpResponse::body)
						      .thenAccept((b) -> System.out.println("\nMain resource:\n" + b))
						      .join();
				
				asyncPushRequests.forEach(CompletableFuture::join);
				
				System.out.println("\nFetched a total of " +
						   asyncPushRequests.size() + " push requests");

		
	}
	
	private static HttpResponse.PushPromiseHandler<String> getPromiseHandler () {
		return (HttpRequest pushPromiseRequest,
	      Function<HttpResponse.BoHandler<String> ,
	      CompletableFuture<HttpResponse<String>>> acceptor) -> {
	      CompletableFuture<Void> pushcf =
	         acceptor.apply(HttpResponse.BodyHandlers.ofString())
	         .thenApply(HttpResponse::body);
	         .thenAccept((b) -> System.out.println(
	            "\nPushed resource body:\n " + b));
	      
	      asyncPushRequests.add(pushcf);
	      
	      System.out.println("Just got pushed, size = " + AsyncBoxView.size());
	      System.out.println("Just got pushed, URL = " + AsyncBoxView.uri());
	      System.out.println("Just got pushed, header = " + AsyncBoxView.header());
	}
}

