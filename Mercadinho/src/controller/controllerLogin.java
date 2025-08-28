package controller;

import java.io.IOException;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Funcionario;

public class controllerLogin {

    @FXML
    private Button btLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;
     static   Funcionario funcionario = new Funcionario();
	FuncionarioDAO funcDao = new FuncionarioDAO();
    @FXML
    void actionLogin(ActionEvent event) throws IOException {
    	String usuario = txtUser.getText();
    	String password = txtPassword.getText();
    	
    	funcionario = funcDao.autenticarUser(usuario, password);
    	if(funcionario.getCpfFuncionario() != null) {
    	
    	
    	if(usuario.equals("")|| password.equals("")) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro no Login");
    		aviso.setContentText("Verifique se as informações estão corretas!");
    		aviso.show();
 	
    	}else if(funcionario.getCpfFuncionario().equals(usuario)&&funcionario.getSenha().equals(password)){ 
    Alert saudacao = new Alert(AlertType.INFORMATION);
    	saudacao.setHeaderText("Seja bem vindo");
    	saudacao.setContentText("Seja bem vindo de volta" + funcionario.getNomeFuncionario()+"!");
    	saudacao.show();
    	Main.TelaHome();
    	}	
    	}else {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro no Login");
    		aviso.setContentText("Verifique se as informações estão corretas!");
    		aviso.show();
    	}
    }

}
