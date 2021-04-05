package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbExcepiton;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Departament;
import model.entities.Seller;
import model.exception.ValidationException;
import model.services.DepartmanetServices;
import model.services.SellerServices;

public class SellerFormController implements Initializable {

	private Seller entity;
	private SellerServices service;
	private DepartmanetServices departamentService;
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox<Departament> ComboBoxDepartament;
	@FXML
	private DatePicker dpBirthDate;
	@FXML
	private TextField txtBaseSalary;

	@FXML
	private Label labelErroName;
	@FXML
	private Label labelErroEmail;
	@FXML
	private Label labelErroBirthDate;
	@FXML
	private Label labelErroBaseSalary;
	@FXML
	private Button BtSave;
	@FXML
	private Button BtCancel;
	@FXML
	private List<DataChangeListener> dataChangeListerners = new ArrayList<>();
	@FXML
	private ObservableList<Departament> obsList;

	public void setEntity(Seller entity) {
		this.entity = entity;
	}

	public void setService(SellerServices service, DepartmanetServices departamentservice) {
		this.service = service;
		this.departamentService = departamentservice;
	}

	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("service was null");

		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListerns();
			Utils.currentStage(event).close();

		} catch (ValidationException e) {
			setErrorMensage(e.getErrors());
		} catch (DbExcepiton e) {
			Alerts.showAlert("Erro saving object", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private void notifyDataChangeListerns() {
		for (DataChangeListener listener : dataChangeListerners) {
			listener.OnDataChange();
		}
	}

	private Seller getFormData() {
		Seller obj = new Seller();
		ValidationException exepition = new ValidationException("Validation campo");
		obj.setId(Utils.tryParseToInt(txtID.getText()));
		if (txtNome.getText() == null || txtNome.getText().trim().equals(" ")) {
			exepition.adError("nome", "field is empyt");
		}
		obj.setNome(txtNome.getText());

		if (txtEmail.getText() == null || txtEmail.getText().trim().equals(" ")) {
			exepition.adError("email", "field is empyt");
		}
		obj.setEmail(txtEmail.getText());

		if(dpBirthDate.getValue() == null) {
			exepition.adError("BirthDate", "field is empyt");

		}else{
		Instant instant = Instant.from(dpBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()));
		obj.setBirthDate(Date.from(instant));
		
		}		
		if (txtBaseSalary.getText() == null || txtBaseSalary.getText().trim().equals(" ")) {
			exepition.adError("baseSalary", "field is empyt");
		}
		obj.setBaseSalary(Utils.tryParseToDouble(txtBaseSalary.getText()));

		obj.setDepartament(ComboBoxDepartament.getValue());
		if (exepition.getErrors().size() > 0) {
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
		Constraints.setTextFieldMaxLength(txtNome, 70);
		Constraints.setTextFieldDouble(txtBaseSalary);
		Constraints.setTextFieldMaxLength(txtEmail, 100);
		Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
		initializeComboBoxDepartment();
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity null");
		}
		txtID.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtEmail.setText(entity.getEmail());
		Locale.setDefault(Locale.US);
		txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
		if (entity.getBirthDate() != null) {
			dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
		}
		if(entity.getDepartament() == null) {
			ComboBoxDepartament.getSelectionModel().selectFirst();
		}
		ComboBoxDepartament.setValue(entity.getDepartament());
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListerners.add(listener);
	}

	private void setErrorMensage(Map<String, String> errors) {

		Set<String> fields = errors.keySet();
		labelErroName.setText((fields.contains("nome") ? errors.get("nome") : ""));
	    labelErroEmail.setText((fields.contains("email") ? errors.get("email") : "" ));
		labelErroBirthDate.setText((fields.contains("BirthDate") ? errors.get("BirthDate") : ""));
		labelErroBaseSalary.setText((fields.contains("baseSalary") ? errors.get("baseSalary") : ""));
			
	}

	public void loadAssociateObjects() {
		if (departamentService == null) {
			throw new IllegalStateException("DepartamentService was null");
		}
		List<Departament> list = departamentService.findAll();
		obsList = FXCollections.observableArrayList(list);
		ComboBoxDepartament.setItems(obsList);
	}

	private void initializeComboBoxDepartment() {
		Callback<ListView<Departament>, ListCell<Departament>> factory = lv -> new ListCell<Departament>() {
			@Override
			protected void updateItem(Departament item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		ComboBoxDepartament.setCellFactory(factory);
		ComboBoxDepartament.setButtonCell(factory.call(null));
	}

}
