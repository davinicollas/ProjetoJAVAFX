package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmanetServices;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartament;
	@FXML
	private MenuItem menuItemAbout;

	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	public void onMenuItemDepartamentAction() {
		loadView("/gui/DepartamentList.fxml", (DepartamentListController controller ) -> {
			controller.setDepartamentService(new DepartmanetServices());
			controller.updateTableView();
		});
		
	}

	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x ->{});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private synchronized <T> void loadView(String absluteName, Consumer<T> initializingAction) {
		try {
			/* Criando a função para busca a view */
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absluteName));
			/* referenciando a view e carregando ela */
			VBox newVBox = loader.load();
			/* Pegando a cena principal, e e salvando ela na variavel */
			Scene mainScene = Main.getMainScene();
			/* Capturando o conteudo do scrollpane e deixando ele como v box */
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			/*
			 * Salvando o menu para que seja carregado novamente mudando apenas o conteudo
			 */
			Node mainMenu = mainVBox.getChildren().get(0);
			/* Limpando o conteudo antigo para pode ser acrescentado o novo */
			mainVBox.getChildren().clear();
			/* Adicionando o menu de volta */
			mainVBox.getChildren().add(mainMenu);
			/*
			 * Adicionando as informação das views carregadas sem perde o principal e o menu
			 */
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			/*Ativar a função que esta no parametro*/
			
			T controller= loader.getController();
			/*Inicial o carregamento do conteudo passado na função*/
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error Loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
