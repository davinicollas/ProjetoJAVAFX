package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Departament;

public class DepartamentFormController implements Initializable{
	
	private Departament entity;
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
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");

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
