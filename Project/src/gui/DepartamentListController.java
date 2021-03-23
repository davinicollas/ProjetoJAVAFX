package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Departament;
import model.services.DepartmanetServices;

public class DepartamentListController implements Initializable{
	
	private DepartmanetServices service;
	@FXML
	private TableView<Departament> tableViewDepartament;
	
	@FXML
	private TableColumn<Departament , Integer > tableColumnId;
	@FXML
	private TableColumn<Departament , String > tableColumnName;

	@FXML
	private Button btNew;
	private ObservableList<Departament> obsList;
	
	
	@FXML
	public void onBtAction(ActionEvent event) {
		
		Stage parentStage = Utils.currentStage(event);
		Departament obj = new Departament();
		createDialogForm(obj,"/gui/DepartamentForm.fxml",parentStage);
	}
	
	public void setDepartamentService(DepartmanetServices service) {
		this.service = service;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		initializeNodes();
	}
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));	
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));	
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());

	}
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service null");
		}
		List<Departament> list =service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartament.setItems(obsList);
	}
	private void createDialogForm(Departament obj,String absuluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absuluteName));
			Pane pane = loader.load();
			
			DepartamentFormController controller = loader.getController();
			controller.setEntity(obj);
			controller.setdepartamentService(new DepartmanetServices());
			controller.updateFormData();
			
			Stage dialogState = new Stage();
			
			dialogState.setTitle("Enter departament data");
			dialogState.setScene(new Scene(pane));
			dialogState.setResizable(false);
			dialogState.initOwner(parentStage);
			dialogState.initModality(Modality.WINDOW_MODAL);
			dialogState.showAndWait();
			
			
		}catch(IOException e) {
			Alerts.showAlert("IO Exeption", "Error ao loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
