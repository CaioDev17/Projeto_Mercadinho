package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Cliente;

public class controllerRelatorioClientes implements Initializable{

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btClientes;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btMenuPrincipal;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btProdutos;

    @FXML
    private Button btRegistrarVenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btVendas;

    @FXML
    private TableColumn<Cliente, String> columnCpf;

    @FXML
    private TableColumn<Cliente, String> columnDataNasc;

    @FXML
    private TableColumn<Cliente, String> columnEmail;

    @FXML
    private TableColumn<Cliente, String> columnEnd;

    @FXML
    private TableColumn<Cliente, String> columnGenero;

    @FXML
    private TableColumn<Cliente, String> columnId;

    @FXML
    private TableColumn<Cliente, String> columnNome;

    @FXML
    private TableColumn<Cliente, String> columnTelefone;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Text txtUser;

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	Main.TelaCadastroCliente();
    	carregarTableCliente();

    }

    @FXML
    void actionClientes(ActionEvent event) throws IOException {
       Main.TelaCliente();
    }
  public static Cliente clienteEditar = new Cliente();
    @FXML
    void actionEditar(ActionEvent event) throws IOException {
        int linha = tableClientes.getSelectionModel().getSelectedIndex();
    	
    	if(linha == -1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("erro ao excluir");
    		aviso.setContentText("Selecione um cliente primeiro");
    		aviso.show();	
    	}else {
    		clienteEditar = tableClientes.getItems().get(linha);
    		Main.TelaCadastroCliente();
    		
    	}
       carregarTableCliente();
    }

    @FXML
    void actionExcluir(ActionEvent event) {
    	int linha = tableClientes.getSelectionModel().getSelectedIndex();
    	
    	if(linha == -1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("erro ao excluir");
    		aviso.setContentText("Selecione um cliente primeiro");
    		aviso.show();	
    	}else {
    		
    		Cliente cliente = new Cliente();
    		ClienteDAO clienteDAO = new ClienteDAO();
    		cliente = tableClientes.getItems().get(linha);
    		
    		Alert msg = new Alert(AlertType.CONFIRMATION);
    		msg.setHeaderText("Excluir Cliente");
    		msg.setContentText("Excluir");
    		msg.setTitle("Deseja realmente excluir o cliente"+cliente.getNomeCliente()+"?");
    		Optional<ButtonType> confirmacao = msg.showAndWait();
    		if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
    			clienteDAO.delete(cliente.getCpfCliente());
    			Alert aviso = new Alert(AlertType.INFORMATION);
    			aviso.setTitle("Cliente apagado!");
    			aviso.setContentText("O cliente foi apagado com sucesso");
    			aviso.show();
    			carregarTableCliente();	
    		}
    	}
    }

    @FXML
    void actionFuncionario(ActionEvent event) {

    }

    @FXML
    void actionMenuPrincipal(ActionEvent event) throws IOException {
       Main.TelaHome();
    }

    @FXML
    void actionPesquisar(ActionEvent event) {
    	if(txtPesquisar.getText().isEmpty()) {
     carregarTableCliente();
    	}else {
    		pesquisarTableCliente();
    		
    		
    	}
    }

    @FXML
    void actionProdutos(ActionEvent event) throws IOException {
    	Main.TelaProduto();

    }

    @FXML
    void actionRegistrarVenda(ActionEvent event) throws IOException {
Main.TelaRegistrarVenda();
    }

    @FXML
    void actionSair(ActionEvent event) throws IOException {
    	Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setHeaderText("Sair do Sistema");
		msg.setContentText("Deseja realmente sair do sistema?");
		msg.setTitle("Deseja sair do sistema?");
		Optional<ButtonType> sair = msg.showAndWait();
		if(sair.isPresent() && sair.get()== ButtonType.OK) {
			Main.telaLogin();
		}
    }

    @FXML
    void actionVendas(ActionEvent event) throws IOException {
Main.TelaVenda();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());
		carregarTableCliente();
		clienteEditar = null;
		
	}
	ObservableList<Cliente> arrayClientes;
	public void carregarTableCliente() {
		
		
		ClienteDAO clienteDAO = new ClienteDAO();
		arrayClientes = FXCollections.observableArrayList(clienteDAO.read());
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEnd.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		tableClientes.setItems(arrayClientes);
		
		
	}
public void pesquisarTableCliente() {	
		ClienteDAO clienteDAO = new ClienteDAO();
		arrayClientes = FXCollections.observableArrayList(clienteDAO.search(txtPesquisar.getText()));
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEnd.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		tableClientes.setItems(arrayClientes);
		
		
	}

}
