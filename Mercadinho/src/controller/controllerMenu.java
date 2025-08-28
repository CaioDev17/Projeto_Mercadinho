package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Produto;

public class controllerMenu implements Initializable{

	@FXML
	private TableColumn<Produto, String> columnTipoUn;

	@FXML
	private Button btClientes;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btMenuPrincipal;

	@FXML
	private Button btProdutos;

	@FXML
	private Button btRegistrarVenda;

	@FXML
	private Button btSair;

	@FXML
	private Button btVendas;

	@FXML
	private TableColumn<Produto, String> columnCodBarra;

	@FXML
	private TableColumn<Produto, String> columnCodBarra2;

	@FXML
	private TableColumn<Produto, String> columnDataVal;

	@FXML
	private TableColumn<Produto, String> columnEstoque;

	@FXML
	private TableColumn<Produto, String> columnTipoUn2;

	@FXML
	private TableColumn<Produto, String> columnId;

	@FXML
	private TableColumn<Produto, String> columnId2;

	@FXML
	private TableColumn<Produto, String> columnNome;

	@FXML
	private TableColumn<Produto, String> columnNome2;

	@FXML
	private TableView<Produto> tableProdEB;

	@FXML
	private TableView<Produto> tableProdPV;

	@FXML
	private Text txtTotalVendas;

	@FXML
	private Text txtUser;

	@FXML
	private Text txtUser2;

	@FXML
	void actionClientes(ActionEvent event) throws IOException {
		Main.TelaCliente();

	}

	@FXML
	void actionFuncionario(ActionEvent event) {

	}

	@FXML
	void actionMenuPrincipal(ActionEvent event) throws IOException {
 Main.TelaRegistrarVenda();
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
		txtUser2.setText(controllerLogin.funcionario.getNomeFuncionario());
		carregarTableProdAV();
		carregarTableProdEB();
		
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		String TotalVendido = funcDAO.readTotalVendido(controllerLogin.funcionario.getCpfFuncionario());
		if(TotalVendido == null) {
			
			txtTotalVendas.setText("R$ 0,00");
		}else {
			
		double totalVenda = Double.parseDouble(TotalVendido);
		txtTotalVendas.setText("R$"+String.format("%.2f", totalVenda));
			
		}
		
		
		
		
	}
	private ObservableList<Produto> arrayProdutoAV;
	public void carregarTableProdAV() {

		ProdutoDAO prodDAO = new ProdutoDAO();
		arrayProdutoAV = FXCollections.observableArrayList(prodDAO.readProdAV());
		
		columnId2.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome2.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBarra2.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUn2.setCellValueFactory(new PropertyValueFactory<>("TipoUn"));
		columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		tableProdPV.setItems(arrayProdutoAV);
	}
	private ObservableList<Produto> arrayProdutoEB;
	public void carregarTableProdEB() {

		ProdutoDAO prodDAO = new ProdutoDAO();
		arrayProdutoEB = FXCollections.observableArrayList(prodDAO.readProdEB());
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("TipoUn"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		tableProdEB.setItems(arrayProdutoEB);
	}







}
