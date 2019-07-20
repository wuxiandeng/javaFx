package com.spartajet.fxboot.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spartajet.fxboot.demo.MainApp;
import com.spartajet.fxboot.demo.mode.Person;
import com.spartajet.fxboot.demo.service.IServiceService;
import com.spartajet.fxboot.demo.util.DateUtil;
import com.spartajet.fxboot.demo.util.SpringUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {
	private IServiceService serviceService;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	serviceService = (IServiceService)SpringUtil.getBean("serviceService");
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setPerson(){
    	List<Map<String,String>> list = serviceService.queryList(new HashMap<String,String>());
    	ObservableList<Person> personData = FXCollections.observableArrayList();
    	if(list==null||list.isEmpty()){
    		return;
    	}
    	for(Map<String,String> map:list){
    		Person person = new Person();
    		person.setFirstName(map.get("firstName"));
    		person.setLastName(map.get("lastName"));
    		person.setPostalCode(Integer.parseInt(map.get("postalCode")));
    		person.setStreet(map.get("street"));
    		person.setCity(map.get("city"));
    		person.setBirthday(DateUtil.parse(map.get("birthday")));
    		person.setId(map.get("id"));
    		personData.add(person);
    	}
    	mainApp.setPersonData(personData);
        personTable.setItems(mainApp.getPersonData());
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        setPerson();
    }
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    @FXML
    private void handleDeletePerson() {
    	int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	Person tempPerson =mainApp.getPersonData().get(selectedIndex);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id", tempPerson.getId());
            serviceService.deletePerson(map);
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setContentText("请选择行");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	Map<String,String> map = new HashMap<String,String>();
            map.put("id", String.valueOf(System.currentTimeMillis()));
            map.put("firstName", tempPerson.getFirstName());
            map.put("lastName", tempPerson.getLastName());
            map.put("street", tempPerson.getStreet());
            map.put("postalCode", String.valueOf(tempPerson.getPostalCode()));
            map.put("city", tempPerson.getCity());
            map.put("birthday", DateUtil.format(tempPerson.getBirthday()));
            serviceService.insertPerson(map);
            mainApp.getPersonData().add(tempPerson);
//        	personTable.setItems( mainApp.getPersonData());
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
            	Map<String,String> map = new HashMap<String,String>();
                map.put("id", selectedPerson.getId());
                map.put("firstName", selectedPerson.getFirstName());
                map.put("lastName", selectedPerson.getLastName());
                map.put("street", selectedPerson.getStreet());
                map.put("postalCode", String.valueOf(selectedPerson.getPostalCode()));
                map.put("city", selectedPerson.getCity());
                map.put("birthday", DateUtil.format(selectedPerson.getBirthday()));
                serviceService.updatePerson(map);
                showPersonDetails(selectedPerson);
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setContentText("请选择行");

            alert.showAndWait();
        }
    }
}