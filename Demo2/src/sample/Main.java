package sample;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TableView<Person> tv = new TableView<>(getPersonsScanner());
        // Gib mir die Daten aus der Person
        // was wollen wir haben? Welche Daten?
        // -> StringProperty -> String - Was ist das?
        // firstName -> Type StringProperty -> Property<String> -> String
        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        tv.getColumns().addAll(firstNameColumn, ageColumn);

        primaryStage.setTitle("Person Mgmt");
        primaryStage.setScene(new Scene(tv, 300, 275));
        primaryStage.show();
    }

    // BufferedReader

    /*
    public static ObservableList<Person> getPersonsStream() {
        String fileName = "C:\\Users\\Oliver\\IdeaProjects\\Demo2\\src\\sample\\Persons.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        }
        catch (IOException ex) {

        }

        return null;
    }
    */

    public static ObservableList<Person> getPersonsScanner() {
        ObservableList<Person> personList = FXCollections.observableArrayList();

        File myFile = new File("C:\\Users\\Oliver\\IdeaProjects\\Demo2\\src\\sample\\Persons.txt");
        try {
            Scanner scanner = new Scanner(myFile);

            while (scanner.hasNextLine()) {
                // Erstelle ein flexibles Array, welches in unserem Fall 2 Spalten hat
                String[] column = scanner.nextLine().split(";");
                personList.add(new Person(column[0], Integer.valueOf(column[1])));
            }
        }
        catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person Mgmt -> ERROR");
            alert.setHeaderText("ERROR: File not found");
            alert.setContentText("Please check the file name specified.");
            alert.showAndWait();
            return personList;
        }

        return personList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
