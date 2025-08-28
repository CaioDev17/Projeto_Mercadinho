package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class controllerCadastroCliente implements Initializable{

    @FXML
    private Button btCancelar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField txtCpf;

    @FXML
    private DatePicker txtDataNasc;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private ChoiceBox<String> txtGenero;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    void actionCancelar(ActionEvent event) {
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

    }
    @FXML
    void actionSalvar(ActionEvent event) {
    	Cliente cliente = new Cliente();
    	ClienteDAO cDAO = new ClienteDAO();
    	if(txtNome.equals("")|| txtDataNasc.getValue() == null
    			|| txtEndereco.equals("")){
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro");
    		erro.setContentText("Erro ao cadastrar! Verifique se os campos foram"+
    		"preenchidos corretamente e tente novamente");
    		erro.show();
    	}else if(!validarCPF(txtCpf.getText())) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro");
    		erro.setContentText("Erro ao cadastrar! Verifique se o cpf foi"+
    		"preenchido corretamente e tente novamente");
    		erro.show();
    	}else {
    	if(controllerRelatorioClientes.clienteEditar == null) {	
    	cliente.setNomeCliente(txtNome.getText());	
    	cliente.setCpfCliente(txtCpf.getText());	
    	cliente.setTelefone(txtTelefone.getText());	
    	cliente.setEmail(txtEmail.getText());	
    	cliente.setEndereco(txtEndereco.getText());	
    	cliente.setDataNasc(txtDataNasc.getValue().toString());	
    	cliente.setGenero(txtGenero.getValue());
    	cDAO.create(cliente);
    	
    	Alert msg = new Alert(AlertType.INFORMATION);
    	msg.setTitle("Sucesso");
    	msg.setContentText("Cliente cadastrado");
    	msg.show();
    	
    	Stage stage = (Stage) btSalvar.getScene().getWindow();
    	stage.close();
    	}else {
    	  	cliente.setNomeCliente(txtNome.getText());	
        	cliente.setCpfCliente(txtCpf.getText());	
        	cliente.setTelefone(txtTelefone.getText());	
        	cliente.setEmail(txtEmail.getText());	
        	cliente.setEndereco(txtEndereco.getText());	
        	cliente.setDataNasc(txtDataNasc.getValue().toString());	
        	cliente.setGenero(txtGenero.getValue());
        	cDAO.update(cliente);
        	
        	Alert msg = new Alert(AlertType.INFORMATION);
        	msg.setTitle("Sucesso");
        	msg.setContentText("Cliente Atualizado!");
        	msg.show();
        	
        	Stage stage = (Stage) btSalvar.getScene().getWindow();
        	stage.close();
    		
    		
    	
    	}
    	}

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	txtGenero.getItems().add("F");
	txtGenero.getItems().add("M");
	if(controllerRelatorioClientes.clienteEditar != null) {
		Cliente cliente = new Cliente();
		cliente = controllerRelatorioClientes.clienteEditar;
		
		txtNome.setText(cliente.getNomeCliente());
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
		txtEndereco.setText(cliente.getEndereco());
		txtCpf.setText(cliente.getCpfCliente());
		txtGenero.setValue(cliente.getGenero());
		String DataNasc = cliente.getDataNasc();
		DataNasc = DataNasc.replace("-", "/");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.parse(DataNasc,format);
		txtDataNasc.setValue(localDate);
		
		
		
	}
	
	
		
	}
	public static boolean validarCPF(String cpf) {
     
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

       
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
           
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = 11 - (soma % 11);
            int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

           
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = 11 - (soma % 11);
            int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

           
            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpf.charAt(10));

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
