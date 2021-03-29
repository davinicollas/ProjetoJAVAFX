package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbExcepiton;
import gui.listener.DataChangeListener;
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
import model.entities.Seller;
import model.exception.ValidationException;
import model.services.SellerServices;

public class SellerFormController implements Initializable{
	
	private Seller entity;
	private SellerServices service;

	@FXML
	private TextField txtID;
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtBirthDate;
	@FXML
	private TextField txtBaseSalary;
	
	
	@FXML
	private Label labelErroName;
	@FXML
	private Button BtSave;
	@FXML
	private Button BtCancel;
	
	
	private List<DataChangeListener> dataChangeListerners = new ArrayList<>();
	
	
	public void setEntity(Seller entity) {
		this.entity = entity;
	}
	public void setSellerService (SellerServices service) {
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
		notifyDataChangeListerns();
		Utils.currentStage(event).close();
		
		}catch(ValidationException e) {
			setErrorMensage(e.getErrors());
		}
		catch(DbExcepiton e) {
			Alerts.showAlert("Erro saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	private void notifyDataChangeListerns() {
		for(DataChangeListener listener : dataChangeListerners) {
			listener.OnDataChange();
		}
	}
	private Seller getFormData() {
		Seller obj = new Seller();
		ValidationException exepition = new ValidationException("Validation campo");
		obj.setId(Utils.tryParseToInt(txtID.getText()));
		if(txtNome.getText() == null || txtNome.getText().trim().equals(" ")){
			exepition.adError("name", "field is empyt");
		}
		obj.setNome(txtNome.getText());
		if(exepition.getErrors().size()>0) {
			throw exepition;
		}
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
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListerners.add(listener);
	}
	private void setErrorMensage(Map<String, String> errors) {
		
		Set<String> fields = errors.keySet();
		
		if(fields.contains("name")) {
			labelErroName.setText(errors.get("name"));
		}
	}

}
