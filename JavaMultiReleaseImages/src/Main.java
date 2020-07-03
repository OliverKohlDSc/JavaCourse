import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				new ImageDemo().displayUI();
			}
		};
		
		EventQueue.invokeLater(runnable);
	}

}
