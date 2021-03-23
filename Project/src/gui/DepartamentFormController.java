package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbExcepiton;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Departament;
import model.services.DepartmanetServices;

public class DepartamentFormController implements Initializable{
	
	private Departament entity;
	private DepartmanetServices service;

	@FXML
	private TextField txtID;
	@FXML
	private TextField txtNome;
	@FXML
	private Label labelErroName;
	@FXML
	private Button BtSave;
	@FXML
	private Button BtCancel;
	
	
	public void setEntity(Departament entity) {
		this.entity = entity;
	}
	public void setdepartamentService (DepartmanetServices service) {
		this.service = service;
	}
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("service was null");

		}
		try{
		entity = getFormData();
		service.saveOrUpdate(entity);
		Utils.currentStage(event).close();
		
		}catch(DbExcepiton e) {
			Alerts.showAlert("Erro saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	private Departament getFormData() {
		Departament obj = new Departament();
		
		obj.setId(Utils.tryParseToInt(txtID.getText()));
		obj.setNome(txtNome.getText());
		
		
		return obj;
	}
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();

	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		InitializeNode();
		
	}
	private void InitializeNode() {
		Constraints.setTextFieldInteger(txtID);
		Constraints.setTextFieldMaxLength(txtNome, 35);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity null");
		}
		txtID.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
	}
}
