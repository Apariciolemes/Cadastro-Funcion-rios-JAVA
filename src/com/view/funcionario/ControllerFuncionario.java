package com.view.funcionario;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.entity.Funcionario;
import com.DAO.FuncionarioDAO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.sun.javafx.application.LauncherImpl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class ControllerFuncionario extends Application implements Initializable {

    @FXML
    private Button buttonInserir;

    @FXML
    private TextField textFieldNome;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonExcluir;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TextField textFieldAltura;

    @FXML
    private DatePicker dataPickerData;

    @FXML
    private TextArea textAreaListFuncionarios;

    @FXML
    private TextField textFieldBuscar;
    
    @FXML
    private Label labelID;

    @FXML
    private Label quantidadeFuncionario;

    @FXML
    void buscarFuncionario(ActionEvent event) {
    	String idString = textFieldBuscar.getText();
		Funcionario Funcionario = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				Funcionario = new FuncionarioDAO().findByID(id);
			} catch (Exception e) {
				
			}

			if (Funcionario != null) {
				labelID.setText(Funcionario.getId() + "");
				textFieldNome.setText(Funcionario.getNome());
				dataPickerData.setValue(Funcionario.getNascimento().toLocalDate());
				textFieldAltura.setText(Funcionario.getAltura() + "");
			}

		}
		textFieldBuscar.clear();
    }
    
   
	@FXML
    void AlterarFuncionario(ActionEvent event) {
    	Funcionario funcionario = pegaDadosID();
		int qtde = new FuncionarioDAO().alterar(funcionario);
		listarFuncionarios();
		limparCampos();
	}
    
    @FXML
    void deletarFuncionario(ActionEvent event) {
    	 	Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Deletar Produto");
	        alert.setHeaderText("Você está prestes a deletar um produto");
	        alert.setContentText("Tem certeza que deseja deletar o produto?");
	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK){
	            Funcionario funcionario = pegaDadosID();
	            int qtde = new FuncionarioDAO().deletar(funcionario.getId());
	            listarFuncionarios();
	        }
	        limparCampos();
    }

    @FXML
    void inserirFuncionario(ActionEvent event) {
    	Funcionario Funcionario = pegaDados();
		int qtde = new FuncionarioDAO().inserir(Funcionario);
		listarFuncionarios();
		limparCampos();
    }
	private Funcionario pegaDados() {
		return new Funcionario(textFieldNome.getText(), java.sql.Date.valueOf(dataPickerData.getValue()), java.lang.Float.parseFloat(textFieldAltura.getText()));
	}
	
	private Funcionario pegaDadosID() {		
		return new Funcionario(Integer.valueOf(labelID.getText()), textFieldNome.getText(), java.sql.Date.valueOf(dataPickerData.getValue()), java.lang.Float.parseFloat(textFieldAltura.getText()));
	}


	private void listarFuncionarios() {
		textAreaListFuncionarios.clear();
		List<Funcionario> listaFuncionario = new FuncionarioDAO().listAll();
		listaFuncionario.forEach(Funcionario -> {
			textAreaListFuncionarios.appendText(Funcionario.toString() + "\n");
		});
		quantidadeFuncionario.setText(String.valueOf(listaFuncionario.size()));
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Funcionarios.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarFuncionarios();
	}
	
	private void limparCampos() {
		textFieldAltura.clear();
		textFieldNome.clear();
		textFieldBuscar.clear();
		dataPickerData.setValue(null);
		labelID.setText("");
	}
}



