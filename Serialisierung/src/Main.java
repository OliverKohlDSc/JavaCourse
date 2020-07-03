import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		// Serialisierung
		// Java -> Objekt
		// auf Dateisystem speichern
		
		MeinObjekt meinObject = new MeinObjekt("Dies ist mein Text",3 );
		
		File meinFile = new File("MeinObjekt.ser");
		//ObjectInputStream
		
		try (ObjectOutputStream meinStream = new ObjectOutputStream(new FileOutputStream(meinFile)))
		{
			meinStream.writeObject(meinObject);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		meinFile = new File("MeinObjekt.ser");
		
		try (ObjectInputStream meinStream = new ObjectInputStream(new FileInputStream(meinFile)))
		{
			meinObject = (MeinObjekt)meinStream.readObject();
			System.out.println(meinObject.getMeinText());
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

}
