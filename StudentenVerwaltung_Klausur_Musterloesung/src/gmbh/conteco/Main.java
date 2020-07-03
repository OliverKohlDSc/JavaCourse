package gmbh.conteco;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private TextField matriculationNumberTextField = new TextField();
	private TextField surnameTextField = new TextField();
	private TextField firstnameTextField = new TextField();
	private TextField streetTextField = new TextField();
	private TextField residenceTextField = new TextField();
	private Label statusBarLabel = new Label("Statuszeile: leer");

	private static final String PATH = ".\\Studenten\\";
	private static final String FILE_BEGINNING = "Student";
	private static final String FILE_EXTENSION = ".csv";

	@Override
	public void start(Stage primaryStage) {
		this.statusBarLabel = new Label("Statuszeile: leer");
		Scene scene = new Scene(createView(), 500, 250);
		primaryStage.setTitle("StudentenVerwaltung");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private BorderPane createView() {
		VBox status = new VBox(5);
		status.getChildren().addAll(this.statusBarLabel);
		status.setPadding(new Insets(5, 5, 5, 5));

		Label labelMatrikelnummer = new Label("Matrikelnummer");
		Label labelName = new Label("Name");
		Label labelVorname = new Label("Vorname");
		Label labelStrasse = new Label("Strasse");
		Label labelWohnort = new Label("Wohnort");
		VBox vboxLabel = new VBox(5);
		vboxLabel.getChildren().addAll(labelMatrikelnummer, labelName, labelVorname, labelStrasse, labelWohnort);
		vboxLabel.setPadding(new Insets(20, 10, 10, 10));
		vboxLabel.setSpacing(15.0);
		VBox vboxTextfeld = new VBox(5);
		vboxTextfeld.getChildren().addAll(this.matriculationNumberTextField, this.surnameTextField,
		        this.firstnameTextField, this.streetTextField, this.residenceTextField);
		vboxTextfeld.setAlignment(Pos.CENTER);
		vboxTextfeld.setPadding(new Insets(7, 7, 7, 7));

		Button saveBtn = new Button("Speichern");
		Button readBtn = new Button("Lesen");
		Button deleteBtn = new Button("Löschen");
		Button resetBtn = new Button("Reset");
		Button closeBtn = new Button("Beenden");

		saveBtn.setMinSize(100, 30);
		readBtn.setMinSize(100, 30);
		deleteBtn.setMinSize(100, 30);
		resetBtn.setMinSize(100, 30);
		closeBtn.setMinSize(100, 30);

		HBox buttonHBox = new HBox(5);
		buttonHBox.getChildren().addAll(saveBtn, readBtn, deleteBtn, resetBtn, closeBtn);
		buttonHBox.setPadding(new Insets(0, 0, 7, 7));

		saveBtn.setOnAction(e -> saveStudent());
		readBtn.setOnAction(e -> readStudents());
		deleteBtn.setOnAction(e -> deleteStudent());
		resetBtn.setOnAction(e -> resetAllTextFields());
		closeBtn.setOnAction(e -> closeProgramm());

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(status);
		borderPane.setLeft(vboxLabel);
		borderPane.setCenter(vboxTextfeld);
		borderPane.setBottom(buttonHBox);
		return borderPane;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void saveStudent() {
		Student s = new Student(this.matriculationNumberTextField.getText(), this.surnameTextField.getText(),
		        this.firstnameTextField.getText(), this.streetTextField.getText(), this.residenceTextField.getText());
		if (s.getSurname().isEmpty() || s.getFirstname().isEmpty() || s.getStreet().isEmpty()
		        || s.getResidence().isEmpty()) {
			this.statusBarLabel.setText("Bitte alle Textfelder ausfüllen");
			return;
		}
		String fileName = FILE_BEGINNING + s.getMatriculationNumber() + FILE_EXTENSION;
		File file = new File(PATH + fileName);
		if (file.exists()) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
			        "Die Datei ist bereits vorhanden. Möchten Sie diese ersetzen?");
			if (!ButtonType.YES.equals(alert.showAndWait().get())) {
				this.statusBarLabel.setText("Datei wurde nicht gespeichert");
				return;
			}
		}
		saveStudentInData(s, fileName);
	}

	private void saveStudentInData(Student s, String fileName) {
		try {
			FileWriter out = new FileWriter(PATH + fileName);
			out.write(s.toString());
			out.close();
			resetAllTextFields();
			this.statusBarLabel.setText("Datei wurde überschrieben.");
		} catch (IOException ex) {
			this.statusBarLabel.setText("Die Datei konnte nicht gespeichert werden!");
		}
	}

	private void readStudents() {
		Stage listStage = new Stage();
		VBox vb = new VBox();
		ListView<Student> lv = new ListView<>();
		lv.getItems().addAll(readAllStudents());

		listStage.setScene(new Scene(vb, 400, 400));
		listStage.setResizable(false);

		Button readStudentBtn = new Button("Student lesen");
		readStudentBtn.setOnAction(e -> {
			fillStudent(lv.getSelectionModel().getSelectedItem());
			listStage.hide();
		});
		vb.getChildren().addAll(lv, readStudentBtn);
		listStage.setTitle("Alle Studenten");
		listStage.show();

	}

	private void fillStudent(Student student) {
		this.matriculationNumberTextField.setText(student.getMatriculationNumber());
		this.surnameTextField.setText(student.getSurname());
		this.firstnameTextField.setText(student.getFirstname());
		this.streetTextField.setText(student.getStreet());
		this.residenceTextField.setText(student.getResidence());
		this.statusBarLabel.setText("Student geladen!");
	}

	private List<Student> readAllStudents() {
		List<Student> studentList = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Paths.get(PATH))) {
			List<Path> collect = paths.filter(Files::isRegularFile).collect(Collectors.toList());
			collect.stream().forEach(file -> {

				try {
					List<String> students = Files.readAllLines(file, StandardCharsets.UTF_8);
					students.stream().forEach(studentString -> {
						String[] splittedStudent = studentString.split(";");
						studentList.add(new Student(splittedStudent[0], splittedStudent[1], splittedStudent[2],
						        splittedStudent[3], splittedStudent[4]));
					});
				} catch (Exception e1) {
					this.statusBarLabel.setText("Es ist ein Fehler beim Lesen der Studenten aufgetreten!");
					e1.printStackTrace();
				}
			});
		} catch (IOException iOException) {
			this.statusBarLabel.setText("Es ist ein Fehler beim Lesen der Studenten aufgetreten!");
		}
		return studentList;
	}

	public void resetAllTextFields() {
		this.matriculationNumberTextField.setText("");
		this.surnameTextField.setText("");
		this.firstnameTextField.setText("");
		this.streetTextField.setText("");
		this.residenceTextField.setText("");
		this.statusBarLabel.setText("Textfelder wurden geleert");
	}

	private void deleteStudent() {
		String matriculationNumber = this.matriculationNumberTextField.getText();

		String fileName = FILE_BEGINNING + matriculationNumber + FILE_EXTENSION;
		File file = new File(PATH + fileName);
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