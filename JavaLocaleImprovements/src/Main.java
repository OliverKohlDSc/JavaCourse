import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

	public static void main(String[] args) {
		//en_US
		//en-US
		Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
		
		Locale en_us = Locale.forLanguageTag("en-US");
		
		Locale de_de = new Locale("de", "DE");
		
		ResourceBundle lables = ResourceBundle.getBundle("i18n.labels", de_de);
		System.out.println(lables.getString("Welcome-Message"));
		
		
		// ISO 639 alpha-2
		// DE
		// EN
		// IT
		
		
		// ISO 639 alpha-3
		// KOK
		
		// Cyrl
		// Latn
		
		// FR
		// DE
		// US
		// 029
		
		//de_DE
		//th_TH_TH_#u-nu-thai
		
		//de_de.getISOCountries()
		
		// MyBundle.properties
		// MyBundle_de.properties
		// MyBundle_fr.properties
	}

}
