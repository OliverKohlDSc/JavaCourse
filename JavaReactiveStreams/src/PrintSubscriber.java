import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class PrintSubscriber implements Subscriber<Integer> {

	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {
		System.out.println("Received item: " + item);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("Error: " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Completed 'print subscriber'.");
	}
}