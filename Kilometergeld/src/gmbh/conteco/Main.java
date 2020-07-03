package gmbh.conteco;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {
    private String resourceDirectory = "res";
    private File tripsFile = new File(resourceDirectory + "/Trips.txt");
    File driversFile = new File(resourceDirectory + "/Drivers.ser");
    File licensePlatesFile = new File(resourceDirectory + "/LicensePlates.ser");

    private CheckBox businessTripCheckBox = new CheckBox("Business trip?");
    private ComboBox<String> licensePlateComboBox = new ComboBox<>();
    private ComboBox<String> driverComboBox = new ComboBox<>();
    private DatePicker datePicker = new DatePicker();
    private TextField kmAtStartTxf = new TextField();
    private TextField kmAtEndTxf = new TextField();
    private TextArea purposeTxf = new TextArea();
    private TextArea routeTxf = new TextArea();

    private ObservableList<Trip> tripList = FXCollections.observableArrayList();
    private ObservableList<String> licensePlateList = FXCollections.observableArrayList();
    private ObservableList<String> driverList = FXCollections.observableArrayList();

    TableView<Trip> tripTableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createRoot(), 1100, 500));
        primaryStage.setTitle("Driver's logbook");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private Parent createRoot() {
        BorderPane root = new BorderPane();

        /* Labels Config */
        Label licensePlateLabel = new Label("License Plate");
        Label driverLabel = new Label("Driver");
        Label dateLabel = new Label("Date");
        Label kmAtStartLabel = new Label("KM at trip start");
        Label kmAtEndLabel = new Label("KM at trip end");
        Label purposeLabel = new Label("Purpose");
        Label routeLabel = new Label("Route");

        businessTripCheckBox.setSelected(true);

        /* Grid Config */
        GridPane grid = new GridPane();
        grid.add(licensePlateLabel, 0, 0);
        grid.add(licensePlateComboBox, 1, 0);
        grid.add(driverLabel, 0, 1);
        grid.add(driverComboBox, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(kmAtStartLabel, 0, 3);
        grid.add(kmAtStartTxf, 1, 3);
        grid.add(kmAtEndLabel, 0, 4);
        grid.add(kmAtEndTxf, 1, 4);
        grid.add(businessTripCheckBox, 1, 5);
        grid.add(purposeLabel, 0, 6);
        grid.add(purposeTxf, 1, 6);
        grid.add(routeLabel, 0, 7);
        grid.add(routeTxf, 1, 7);


        /* Save and Cancel Buttons */
        Button saveButton = new Button("Save Trip");
        saveButton.setOnAction(handler -> {
            saveTrip();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(handler -> {
            cancelCreation();
        });
        HBox saveAndCancel = new HBox(saveButton, cancelButton);
        grid.add(saveAndCancel, 1, 8);


        /* Layout Config */
        kmAtStartTxf.setMaxWidth(200);
        kmAtEndTxf.setMaxWidth(200);
        purposeTxf.setMaxWidth(200);
        routeTxf.setMaxWidth(200);
        saveButton.setMinWidth(80);
        cancelButton.setMinWidth(80);
        saveAndCancel.setSpacing(10);
        grid.setPadding(new Insets(20));
        grid.setHgap(40);
        grid.setVgap(10);

        root.setTop(createMenu());
        root.setLeft(grid);

        /* Data load */
        licensePlateComboBox.setItems(licensePlateList);
        driverComboBox.setItems(driverList);

        configureTable();
        root.setCenter(tripTableView);

        loadTrips();
        loadDrivers();
        loadLicensePlates();

        return root;
    }

    /* Create / Cancel Methods */
    private Trip createTrip() {
        String licensePlate = licensePlateComboBox.getSelectionModel().getSelectedItem();
        String driver = driverComboBox.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        long kmAtStart = Long.parseLong(kmAtStartTxf.getText());
        long kmAtEnd = Long.parseLong(kmAtEndTxf.getText());
        long kmDriven = kmAtEnd - kmAtStart;
        boolean isBusinessTrip = businessTripCheckBox.isSelected();
        String purpose = purposeTxf.getText();
        String route = routeTxf.getText();

        return new Trip(licensePlate, driver, date, kmAtStart, kmAtEnd, kmDriven, isBusinessTrip, purpose, route);
    }
    private void cancelCreation() {
        businessTripCheckBox.setSelected(true);
        licensePlateComboBox.getSelectionModel().clearSelection();
        driverComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(LocalDate.now());
        kmAtStartTxf.setText("");
        kmAtEndTxf.setText("");
        purposeTxf.setText("");
        routeTxf.setText("");
    }

    /* IO Methods */
    private void saveTrip() {
        try (BufferedWriter writer = Files.newBufferedWriter(tripsFile.toPath())) {
            Trip newTrip = createTrip();
            tripList.add(newTrip);
            for (Trip thisTrip : tripList) {
                writer.write(thisTrip.exportToCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadTrips() {

        if (!CheckFolderExists() || !tripsFile.exists())
            return;

        try (BufferedReader reader = Files.newBufferedReader(this.tripsFile.toPath())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tripList.add(Trip.importNewFromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDrivers() {
        try (ObjectOutputStream driversStream = new ObjectOutputStream(new FileOutputStream(driversFile))) {
            ArrayList<String> importList = new ArrayList<>(driverList);
            driversStream.writeObject(importList);
        } catch (NotSerializableException e) {
            System.out.println("Not everything was serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDrivers() {
        if (!CheckFolderExists() || !driversFile.exists())
            return;

        try (ObjectInputStream driversStream = new ObjectInputStream(new FileInputStream(driversFile))) {
            ArrayList<String> importList = (ArrayList<String>) driversStream.readObject();
            driverList.addAll(importList);
        } catch (NotSerializableException e) {
            System.out.println("Not everything was deserialized.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveLicensePlates() {
        try (ObjectOutputStream licensePlatesStream = new ObjectOutputStream(new FileOutputStream(licensePlatesFile))) {
            ArrayList<String> importList = new ArrayList<>(licensePlateList);
            licensePlatesStream.writeObject(importList);
        } catch (NotSerializableException e) {
            System.out.println("Not everything was serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadLicensePlates() {
        if (!CheckFolderExists() || !licensePlatesFile.exists())
            return;

        try (ObjectInputStream licensePlatesStream = new ObjectInputStream(new FileInputStream(licensePlatesFile))) {
            ArrayList<String> importList = (ArrayList<String>) licensePlatesStream.readObject();
            licensePlateList.addAll(importList);
        } catch (NotSerializableException e) {
            System.out.println("Not everything was deserialized.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean CheckFolderExists() {
        File resFolder = new File(this.resourceDirectory);
        if (!resFolder.exists()) {
            return resFolder.mkdir();
        }

        return true;
    }

    /* Table Config Methods */
    private void configureTable() {
        TableColumn<Trip, String> licensePlateColumn = createColumn("License Plate", "licensePlate");
        TableColumn<Trip, String> driverColumn = createColumn("Driver", "driver");
        TableColumn<Trip, String> dateColumn = createColumn("Date","dateAsString");
        TableColumn<Trip, String> kmAtStartColumn = createColumn("KM at start","kmAtStart");
        TableColumn<Trip, String> kmAtEndColumn = createColumn("KM at end","kmAtEnd");
        TableColumn<Trip, String> kmDrivenColumn = createColumn("Total km driven","kmDriven");
        TableColumn<Trip, String> isBusinessColumn = createColumn("Business trip?","isBusinessTrip");
        TableColumn<Trip, String> purposeColumn = createColumn("Purpose","purpose");
        TableColumn<Trip, String> routeColumn = createColumn("Route","route");

        tripTableView.getColumns().addAll(licensePlateColumn,driverColumn,dateColumn,kmAtStartColumn,kmAtEndColumn,kmDrivenColumn,isBusinessColumn,purposeColumn,routeColumn);
        tripTableView.setItems(tripList);
    }
    private <T> TableColumn<T, String> createColumn(String columnName, String propertyName) {
        TableColumn<T, String> newColumn = new TableColumn<>(columnName);
        newColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return newColumn;
    }

    /* Menu Config Methods */
    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        /* License Plate Management */
        Menu licensePlateMenu = new Menu("Manage License Plates");

        MenuItem createLicensePlateMenu = new MenuItem("Create New");
        createLicensePlateMenu.setOnAction(handler -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Dialog");
            dialog.setHeaderText("New License Plate");
            dialog.setContentText("Please enter a valid license plate number:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> licensePlateList.addAll(name));
        });

        MenuItem deleteLicensePlateMenu = new MenuItem("Delete");
        deleteLicensePlateMenu.setOnAction(handler -> {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(null, licensePlateList);
            dialog.setTitle("Dialog");
            dialog.setHeaderText("Delete License Plate");
            dialog.setContentText("Choose a license plate to delete:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(selection -> licensePlateList.remove(selection));
        });

        MenuItem saveCurrentPlatesMenu = new MenuItem("Save current");
        saveCurrentPlatesMenu.setOnAction(handler -> {
            saveLicensePlates();
        });

        licensePlateMenu.getItems().addAll(createLicensePlateMenu, deleteLicensePlateMenu, saveCurrentPlatesMenu);


        /* License Plate Management */
        Menu driverMenu = new Menu("Manage Drivers");

        MenuItem createDriver = new MenuItem("Create New");
        createDriver.setOnAction(handler -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Dialog");
            dialog.setHeaderText("New Driver");
            dialog.setContentText("Please enter a valid driver name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> driverList.addAll(name));
        });

        MenuItem deleteDriver = new MenuItem("Delete");
        deleteDriver.setOnAction(handler -> {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(null, driverList);
            dialog.setTitle("Dialog");
            dialog.setHeaderText("Delete Driver");
            dialog.setContentText("Choose a driver to delete:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(selection -> driverList.remove(selection));
        });

        MenuItem saveCurrentDriversMenu = new MenuItem("Save current");
        saveCurrentDriversMenu.setOnAction(handler -> {
            saveDrivers();
        });


        driverMenu.getItems().addAll(createDriver, deleteDriver, saveCurrentDriversMenu);

        menuBar.getMenus().addAll(licensePlateMenu, driverMenu);
        return menuBar;
    }
}