import java.util.ListResourceBundle;

public class LocaleDemo_de extends ListResourceBundle {
	private Object[][] contents = {
			{ "price", 1.00},
			{ "currency", "EUR" }
	};
	
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}
}
