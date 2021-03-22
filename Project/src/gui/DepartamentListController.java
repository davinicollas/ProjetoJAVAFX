package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Departament;

public class DepartamentListController implements Initializable{
	@FXML
	private TableView<Departament> tableViewDepartament;
	
	@FXML
	private TableColumn<Departament , Integer > tableColumnId;
	@FXML
	private TableColumn<Departament , String > tableColumnName;

	@FXML
	private Button btNew;

	@FXML
	public void onBtAction() {
		System.out.println("ONBTNCLICK");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		initializeNodes();
	}
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));	
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));	
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartament.prefHeightProperty().bind(stage.heightProperty());

	}

}
