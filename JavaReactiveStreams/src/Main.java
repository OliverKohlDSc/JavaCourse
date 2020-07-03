import java.util.concurrent.SubmissionPublisher;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// RxJava
		
		// Publisher
		// Subscriber
		// Subscription
		// Processor
		// java.util.concurrent.Flow
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		publisher.subscribe(new PrintSubscriber());
		
		System.out.println("Submitting items ...");
		
		for (int i = 0; i < 10; i++) {
			publisher.submit(i);
		}
		
		Thread.sleep(1000);
		publisher.close();
		
		
		
		
		SubmissionPublisher<Integer> myPublisher = new SubmissionPublisher<>();
		PlusTenProcessor processor = new PlusTenProcessor();
		PrintSubscriber subscriber = new PrintSubscriber();
		myPublisher.subscribe(processor);
		processor.subscribe(subscriber);
		
		System.out.println("Submitting items ...");
		
		for (int i = 0; i < 10; i++) {
			myPublisher.submit(i);
		}
		
		Thread.sleep(1000);
		myPublisher.close();
	}
}