package gmbh.conteco;
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
		Button deleteButton = new Button("Löschen");
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

	private void saveStudent() {
		String matriculationNumber = this.matriculationNumberTextField.getText();
		String surname = this.surnameTextField.getText();
		String firstname = this.firstnameTextField.getText();
		String street = this.streetTextField.getText();
		String residence = this.residenceTextField.getText();
		if (surname.isEmpty() || firstname.isEmpty() || street.isEmpty() || residence.isEmpty()) {
			this.statusBarLabel.setText("Bitte alle Textfelder ausfüllen");
			return;
		}

		final String path = ".\\Studenten\\";
		final String filename = "Student" + matriculationNumber + ".csv";

		String outString = surname + ";" + firstname + ";" + street + ";" + residence;
		File file = new File(path + filename);
		if (!file.exists()) {
			try {
				FileWriter out = new FileWriter(path + filename);
				out.write(outString, 0, outString.length());
				out.close();
				this.statusBarLabel.setText("Datei wurde gespeichert");
				this.matriculationNumberTextField.setText("");
				this.surnameTextField.setText("");
				this.firstnameTextField.setText("");
				this.streetTextField.setText("");
				this.residenceTextField.setText("");
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
			return;
		}
		ButtonType result = ButtonType.NO;

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
		        "Die Datei ist bereits vorhanden. Möchten Sie diese ersetzen?", ButtonType.YES, ButtonType.NO);
		result = alert.showAndWait().orElse(ButtonType.NO);
		if (result.equals(ButtonType.YES)) {
			try {
				FileWriter out = new FileWriter(path + filename);
				out.write(outString, 0, outString.length());
				out.close();
				this.statusBarLabel.setText("Datei wurde überschrieben.");
				this.matriculationNumberTextField.setText("");
				this.surnameTextField.setText("");
				this.firstnameTextField.setText("");
				this.streetTextField.setText("");
				this.residenceTextField.setText("");
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
		} else {
			this.statusBarLabel.setText("Datei wurde nicht gespeichert");
		}
	}

//	private List<Student> readAllStudents() {
//		List<Student> studentList = new ArrayList<>();
//		try (Stream<Path> paths = Files.walk(Paths.get(PATH))) {
//			List<Path> collect = paths.filter(Files::isRegularFile).collect(Collectors.toList());
//			collect.stream().forEach(file -> {
//
//				try {
//					List<String> students = Files.readAllLines(file, StandardCharsets.UTF_8);
//					// Hier muss noch logik ergänzt werden!
//				} catch (Exception e1) {
//					this.statusBarLabel.setText("Es ist ein Fehler beim lesen der Studenten aufgetreten!");
//					e1.printStackTrace();
//				}
//			});
//		} catch (IOException iOException) {
//			this.statusBarLabel.setText("Es ist ein Fehler beim lesen der Studenten aufgetreten!");
//		}
//		return studentList;
//	}

	private void deleteStudent() {
		String matriculationNumber = this.matriculationNumberTextField.getText();
		final String path = ".\\Studenten\\";
		final String filename = "Student" + matriculationNumber + ".csv";
		File file = new File(path + filename);
		ButtonType result = ButtonType.NO;

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Möchten Sie die Datei wirklich löschen?", ButtonType.YES,
		        ButtonType.NO);
		if (!file.exists()) {
			this.statusBarLabel.setText("Datei existiert nicht");
		} else {
			result = alert.showAndWait().orElse(ButtonType.NO);
			if (result.equals(ButtonType.YES)) {
				file.delete();
				this.statusBarLabel.setText("Datei wurde gelöscht");
			} else {
				this.statusBarLabel.setText("Datei wurde nicht gelöscht");
			}
		}
	}

	private void closeProgramm() {
		System.exit(0);
	}
}
