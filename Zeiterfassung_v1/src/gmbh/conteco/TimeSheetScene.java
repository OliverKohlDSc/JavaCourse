package gmbh.conteco;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class TimeSheetScene  {	
	public static Scene getScene() {
		VBox vBox = new VBox();
		
		SplitPane contentSplitPane = new SplitPane();
		
		SplitPane listSplitPane = new SplitPane();
		listSplitPane.setOrientation(Orientation.VERTICAL);
		
		VBox upperListArea = new VBox();
		VBox lowerListArea = new VBox();
		
		ComboBox accountsComboBox = new ComboBox();
		accountsComboBox.setMaxWidth(Double.MAX_VALUE);
		accountsComboBox.getItems().addAll("  --- All Accounts ---  ", "Unallocated");
		
		upperListArea.getChildren().add(new Label("Accounts"));
		upperListArea.getChildren().add(accountsComboBox);
		
		listSplitPane.getItems().addAll(upperListArea, lowerListArea);
		
		VBox contentVBox = new VBox();
		
		HBox navigationArea = new HBox();
		VBox.setVgrow(navigationArea, Priority.NEVER);
		
		TableView<TimeSheetEntry> tableView = setTableView();
		VBox.setVgrow(tableView, Priority.ALWAYS);
		contentVBox.getChildren().addAll(navigationArea, tableView);

		contentSplitPane.getItems().add(listSplitPane);
		contentSplitPane.getItems().add(contentVBox);
		
		HBox statusBar = new HBox();
		Label leftStatus = new Label("COMPANY-NAME");
		HBox.setHgrow(leftStatus, Priority.ALWAYS);
		Pane middleAreaStatusBar = new Pane();
		HBox.setHgrow(middleAreaStatusBar, Priority.ALWAYS);
		Label rightStatus = new Label("DATETIME");
		HBox.setHgrow(rightStatus, Priority.NEVER);
		
		statusBar.getChildren().addAll(leftStatus, middleAreaStatusBar,rightStatus);
		
		vBox.getChildren().add(getMenuBar());
		vBox.getChildren().add(getButtonBar());
		vBox.getChildren().add(contentSplitPane);
		vBox.getChildren().add(statusBar);
		
		return new Scene(vBox);
	}
	
	// TODO: What needs to be done to get this stuff working?
	private static TableView<TimeSheetEntry> setTableView() {
		TableView<TimeSheetEntry> tableView = new TableView<>();

		ObservableList<TimeSheetEntry> sheet = FXCollections.observableArrayList();
		
		TimeSheetEntry entry = new TimeSheetEntry();
		entry.setNotes("Please complete this method!");
		sheet.add(entry);
		
		tableView.setItems(sheet);
		TableColumn<TimeSheetEntry,String> notes = new TableColumn<TimeSheetEntry,String>("notes");
		notes.setCellValueFactory(new PropertyValueFactory("notes"));
	
		return tableView;
	}
	
	private static ButtonBar getButtonBar() {
		ButtonBar buttonBar = new ButtonBar();
		
		ImageMenuButton newTaskButton = new ImageMenuButton("New Task");

		ButtonBar.setButtonData(newTaskButton, ButtonData.LEFT);
		MenuItem newBaseTaskButton = new MenuItem("New Base Task");
		newTaskButton.getItems().add(newBaseTaskButton);
		MenuItem newSubTaskButton = new MenuItem("New Sub Task");
		newTaskButton.getItems().add(newSubTaskButton);

		Button newEntryButton = new Button("New Entry");
		ButtonBar.setButtonData(newEntryButton, ButtonData.LEFT);
		
		Button previousButton = new Button();
		ButtonBar.setButtonData(previousButton, ButtonData.RIGHT);
		
		DatePicker datePicker = new DatePicker();
		ButtonBar.setButtonData(datePicker, ButtonData.RIGHT);
		
		Button nextButton = new Button();
		ButtonBar.setButtonData(nextButton, ButtonData.RIGHT);
		
		buttonBar.getButtons().addAll(newTaskButton, newEntryButton, previousButton, datePicker, nextButton);
		
		return buttonBar;
	}
	
	private static MenuBar getMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu helpMenu = new Menu("Help");
		MenuItem quitMenuItem = new MenuItem("Quit");
		MenuItem aboutMenuItem = new MenuItem("About");
		
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		fileMenu.getItems().add(quitMenuItem);
		helpMenu.getItems().add(aboutMenuItem);
		
		return menuBar;
	}
}