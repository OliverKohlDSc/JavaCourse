import java.util.ListResourceBundle;

public class LocaleDemo extends ListResourceBundle {
	private Object[][] contents = {
			{ "price", 10.00},
			{ "currency", "USD" }
	};
	
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}
}
