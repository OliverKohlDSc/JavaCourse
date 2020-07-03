import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private TextField matriculationNumberTextField;
	private TextField surnameTextField;
	private TextField firstnameTextField;
	private TextField streetTextField;
	private TextField residenceTextField;
	private Label statusBarLabel;

	@Override
	public void start(Stage primaryStage) {
		this.statusBarLabel = new Label("Statuszeile: leer");

		Scene scene = new Scene(createView(), 400, 250);
		primaryStage.setTitle("StudentenVerwaltung");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/*
	 * Habe mir mal erlaubt, die Erstellung der View aus der Start Methode in diese
	 * Methode zu extrahieren, damit die TN nicht wieder auf die Idee kommen, wenn
	 * die Trainer das machen, dann muss das ja gut sein und wir machen es auch. Sie
	 * sollten schon etwas den Code geeignet in Methoden aufteilen.
	 */
	private BorderPane createView() {
		VBox status = new VBox(1);
		status.getChildren().addAll(this.statusBarLabel);
		status.setPadding(new Insets(5, 5, 5, 5));

		Label matriculationNumberLabel = new Label("Matrikelnummer");
		Label labelSurname = new Label("Name");
		Label labelFirstname = new Label("Vorname");
		Label labelStreet = new Label("Strasse");
		Label labelResidence = new Label("Wohnort");
		VBox vboxLabel = new VBox(5);
		vboxLabel.getChildren().addAll(matriculationNumberLabel, labelSurname, labelFirstname, labelStreet,
		        labelResidence);
		vboxLabel.setPadding(new Insets(5, 5, 5, 5));
		vboxLabel.setSpacing(5);

		this.matriculationNumberTextField = new TextField();
		this.surnameTextField = new TextField();
		this.firstnameTextField = new TextField();
		this.streetTextField = new TextField();
		this.residenceTextField = new TextField();
		VBox vboxTextField = new VBox(5);
		vboxTextField.getChildren().addAll(this.matriculationNumberTextField, this.surnameTextField,
		        this.firstnameTextField, this.streetTextField, this.residenceTextField);
		vboxTextField.setAlignment(Pos.CENTER);
		vboxTextField.setPadding(new Insets(5, 5, 5, 5));

		Button saveButton = new Button("Speichern");
		Button deleteButton = new Button("Loeschen");
		Button exitButton = new Button("Beenden");

		saveButton.setMinSize(100, 30);
		deleteButton.setMinSize(100, 30);
		exitButton.setMinSize(100, 30);

		HBox hboxButton = new HBox(3);
		hboxButton.getChildren().addAll(saveButton, deleteButton, exitButton);
		hboxButton.setPadding(new Insets(5, 5, 5, 5));

		saveButton.setOnAction(e -> saveStudent());
		deleteButton.setOnAction(e -> deleteStudent());
		exitButton.setOnAction(e -> closeProgramm());

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(status);
		borderPane.setLeft(vboxLabel);
		borderPane.setCenter(vboxTextField);
		borderPane.setBottom(hboxButton);
		return borderPane;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * Warum ist die Methode public? 
	 */
	public void saveStudent() {
		String matriculationNumber = this.matriculationNumberTextField.getText();
		String surname = this.surnameTextField.getText();
		String firstname = this.firstnameTextField.getText();
		String street = this.streetTextField.getText();
		String residence = this.residenceTextField.getText();
		// Hätte hier lieber isEmpty() verwendet. Das kennen alle Teilnehmer. Oder
		// sollte das die Schwierigkeit sein?
		if ((surname.compareTo("") == 0) || (firstname.compareTo("") == 0) || (street.compareTo("") == 0)
		        || (residence.compareTo("") == 0)) {
			this.statusBarLabel.setText("Bitte alle Textfelder ausf�llen");
			return;
		}
		String path = ".\\Studenten\\";
		String filename = "Student" + matriculationNumber + ".csv";
		// Warum keine Dummy Klasse Student erzeugen und die Tostringmethode zum
		// speichern verwenden?
		String outString = surname + ";" + firstname + ";" + street + ";" + residence;
		File file = new File(path + filename);
		if (!file.exists()) {
			try {
				FileWriter out = new FileWriter(path + filename);
				out.write(outString, 0, outString.length());
				out.close();
				this.statusBarLabel.setText("Datei wurde gespeichert");
				// lieber eine ClearAllButtons-Methode statt sowas
				this.matriculationNumberTextField.setText("");
				this.surnameTextField.setText("");
				this.firstnameTextField.setText("");
				this.streetTextField.setText("");
				this.residenceTextField.setText("");
			} catch (IOException ex) {
				/*
				 * KEIN GUTER STIL EINEN CATCH BLOCK LEER ZU LASSEN. Sollten den TN sowas gar
				 * nicht erst beibringen!!
				 */
			}
			// Wofür ist das return?
			return;
		}
		// Variable mitten in der Klasse und ohne sichtbarkeit
		ButtonType result = ButtonType.NO;
		/*
		 * Alerts kennen noch nicht alle Teilnehmer! Ist nicht schlimm, wenn sie die
		 * nicht anpassen m�ssen und sie diese einfach nur akzeptieren sollen.
		 */
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
		        "Die Datei ist bereits vorhanden. M�chten Sie diese ersetzen?", ButtonType.YES, ButtonType.NO);
		result = alert.showAndWait().orElse(ButtonType.NO);
		if (ButtonType.YES.equals(result)) {
			try {
				// Gleiche logik wie oben. Sollte lieber eine Methode sein mit dem
				// Satsusbarlabeltext als übergabeparameter
				FileWriter out = new FileWriter(path + filename);
				out.write(outString, 0, outString.length());
				out.close();
				this.statusBarLabel.setText("Datei wurde �berschrieben.");
				this.matriculationNumberTextField.setText("");
				this.surnameTextField.setText("");
				this.firstnameTextField.setText("");
				this.streetTextField.setText("");
				this.residenceTextField.setText("");
			} catch (IOException ex) {
				/*
				 * KEIN GUTER STIL EINEN CATCH BLOCK LEER ZU LASSEN. Sollten den TN sowas gar
				 * nicht erst beibringen!!
				 */
			}
		} else {
			this.statusBarLabel.setText("Datei wurde nicht gespeichert");
		}
	}

	/*
	 * Ich w�rde die Methode nicht vorgeben, sie sollen selbst die Methode
	 * erstellen.
	 */
//	public void lesen() {
//			// ToDo
//	}

	/*
	 * Warum ist die Methode public?
	 */
	public void deleteStudent() {
		String matriculationNumber = this.matriculationNumberTextField.getText();
		// Die Pfade sollten lieber als Konstaten oder zumindest als Final oben
		// gespeichert werden.
		// Oder von den Teilnehmern dahin verschoben werden. Das ist sonst echt
		// ärgerlich, wenn der Pfad sich
		// mal ändert!
		String path = ".\\Studenten\\";
		String filename = "Student" + matriculationNumber + ".csv";
		File file = new File(path + filename);
		ButtonType result = ButtonType.NO;

		/*
		 * Alerts kennen noch nicht alle Teilnehmer! Ist nicht schlimm, wenn sie die
		 * nicht anpassen m�ssen und sie diese einfach nur akzeptieren sollen. Außerdem
		 * müsste der alter in den Elsefall. Da das sonst unnötig ist, da er ja nicht
		 * angezeigt wird.
		 */
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "M�chten Sie die Datei wirklich l�schen?", ButtonType.YES,
		        ButtonType.NO);
		if (!file.exists()) {
			this.statusBarLabel.setText("Datei existiert nicht");
		} else {
			result = alert.showAndWait().orElse(ButtonType.NO);
			if (result.equals(ButtonType.YES)) {
				file.delete();
				this.statusBarLabel.setText("Datei wurde gel�scht");
			} else {
				this.statusBarLabel.setText("Datei wurde nicht gel�scht");
			}
		}
	}

	/*
	 * Ich w�rde die Methode nicht vorgeben, sie sollen selbst die Methode
	 * erstellen.
	 */
//	public void reset() {
//		// ToDo
//	}

	/*
	 * Warum ist die Methode public?
	 */
	public void closeProgramm() {
		System.exit(0);
	}

}
