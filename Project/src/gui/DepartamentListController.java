package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtAction() {
		System.out.println("ONBTNCLICK");
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

}
