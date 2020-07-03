package conteco.gmbh;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class EventHandlingController {
	
	@FXML
	private Button myButton;
	
	@FXML
	private CheckBox myCheckBox;
	
	@FXML
	private ComboBox<Person> myComboBox;
	private ObservableList<Person> myComboBoxData = FXCollections.observableArrayList();
	
	@FXML
	private Hyperlink myHyperlink;
	
	@FXML
	private Slider mySlider;
	
	@FXML
	private TextField myTextField;
	
	@FXML
	private ListView<Person> myListView;
	private ObservableList<Person> listViewData = FXCollections.observableArrayList();
	
	@FXML
	private TextArea outputTextArea;
	
	
	/**
	 * The constructor (is called before the initialize()-method).
	 */
	public EventHandlingController() {
		// Create some sample data for the ComboBox and ListView.
		myComboBoxData.add(new Person("Hans", "Muster"));
		myComboBoxData.add(new Person("Ruth", "Mueller"));
		myComboBoxData.add(new Person("Heinz", "Kurz"));
		myComboBoxData.add(new Person("Cornelia", "Meier"));
		myComboBoxData.add(new Person("Werner", "Meyer"));
		
		listViewData.add(new Person("Lydia", "Kunz"));
		listViewData.add(new Person("Anna", "Best"));
		listViewData.add(new Person("Stefan", "Meier"));
		listViewData.add(new Person("Martin", "Mueller"));
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Handle Button event.
		myButton.setOnAction((event) -> {
			outputTextArea.appendText("Button Action\n");
		});
		
		// Handle CheckBox event.
		myCheckBox.setOnAction((event) -> {
			boolean selected = myCheckBox.isSelected();
			outputTextArea.appendText("CheckBox Action (selected: " + selected + ")\n");
		});
		
		// Init ComboBox items.
		myComboBox.setItems(myComboBoxData);
		
		// Define rendering of the list of values in ComboBox drop down. 
		myComboBox.setCellFactory((comboBox) -> {
			return new ListCell<Person>() {
				@Override
				protected void updateItem(Person item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getFirstName() + " " + item.getLastName());
					}
				}
			};
		});
		
		// Define rendering of selected value shown in ComboBox.
		myComboBox.setConverter(new StringConverter<Person>() {
			@Override
			public String toString(Person person) {
				if (person == null) {
					return null;
				} else {
					return person.getFirstName() + " " + person.getLastName();
				}
			}

			@Override
			public Person fromString(String personString) {
				return null; // No conversion fromString needed.
			}
		});
		
		// Handle ComboBox event.
		myComboBox.setOnAction((event) -> {
			Person selectedPerson = myComboBox.getSelectionModel().getSelectedItem();
			outputTextArea.appendText("ComboBox Action (selected: " + selectedPerson.toString() + ")\n");
		});
		
		// Handle Hyperlink event.
		myHyperlink.setOnAction((event) -> {
			outputTextArea.appendText("Hyperlink Action\n");
		});
		
		// Init ListView.
		myListView.setItems(listViewData);
		myListView.setCellFactory((list) -> {
			return new ListCell<Person>() {
				@Override
				protected void updateItem(Person item, boolean empty) {
					super.updateItem(item, empty);
					
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getFirstName() + " " + item.getLastName());
					}
				}
			};
		});
		
		// Handle ListView selection changes.
		myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			outputTextArea.appendText("ListView Selection Changed (selected: " + newValue.toString() + ")\n");
		});
		
		// Handle Slider value change events.
		mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
		});
		
		// Handle TextField text changes.
		myTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			outputTextArea.appendText("TextField Text Changed (newValue: " + newValue + ")\n");
		});
		
		// Handle TextField enter key event.
		myTextField.setOnAction((event) -> {
			outputTextArea.appendText("TextField Action\n");
		});
	}
}