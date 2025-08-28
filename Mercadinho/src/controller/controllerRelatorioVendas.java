package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ProdutoDAO;
import dao.RelatorioVendaDAO;
import dao.VendaDAO;
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
import model.Produto;
import model.RelatorioVenda;
import model.Venda;

public class controllerRelatorioVendas implements Initializable{
    @FXML
    private Button btClientes;

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
    private TableColumn<RelatorioVenda, String> columnDataVenda;

    @FXML
    private TableColumn<RelatorioVenda, String> columnId;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeCliente;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeFuncionario;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeProduto;

    @FXML
    private TableColumn<RelatorioVenda, String> columnPrecoTotal;

    @FXML
    private TableColumn<RelatorioVenda, String> columnQuantidade;

    @FXML
    private TableView<RelatorioVenda> tableVendas;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Text txtUser;

   

    @FXML
    void actionClientes(ActionEvent event) throws IOException {
     Main.TelaCliente(); 
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
			carregarTableVenda();
		}else {
			pesquisarTableVenda();


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
    void actionVendas(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());
		carregarTableVenda();
		
	}
	ObservableList<RelatorioVenda> arrayVendas;
	public void carregarTableVenda() {


		RelatorioVendaDAO relaDAO = new RelatorioVendaDAO();
		arrayVendas = FXCollections.observableArrayList(relaDAO.read());

		columnId.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableVendas.setItems(arrayVendas);


	}
	
	public void pesquisarTableVenda() {	
		 RelatorioVendaDAO relaDAO = new RelatorioVendaDAO();
		arrayVendas = FXCollections.observableArrayList(relaDAO.search(txtPesquisar.getText()));

		columnId.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnDataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		tableVendas.setItems(arrayVendas);


	}

}
