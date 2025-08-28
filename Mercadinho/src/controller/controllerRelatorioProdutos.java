package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ClienteDAO;
import dao.ProdutoDAO;
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
import model.Produto;

public class controllerRelatorioProdutos implements Initializable{

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
	private TableColumn<Produto, String> columnCodBarra;

	@FXML
	private TableColumn<Produto, String> columnDataFab;

	@FXML
	private TableColumn<Produto, String> columnDataVal;

	@FXML
	private TableColumn<Produto, String> columnEstoque;

	@FXML
	private TableColumn<Produto, String> columnId;

	@FXML
	private TableColumn<Produto, String> columnNome;

	@FXML
	private TableColumn<Produto, String> columnPrecoUn;

	@FXML
	private TableColumn<Produto, String> columnTipoUn;

	@FXML
	private TableView<Produto> tableProdutos;

	@FXML
	private TextField txtPesquisar;

	@FXML
	private Text txtUser;

	@FXML
	void actionCadastrar(ActionEvent event) throws IOException {
		produtoEditar = null;
		Main.TelaCadastroProduto();
		carregarTableProduto();

	}

	@FXML
	void actionClientes(ActionEvent event) throws IOException {
		Main.TelaCliente();
	}
	public static Produto produtoEditar = new Produto();
	@FXML
	void actionEditar(ActionEvent event) throws IOException {
        int linha = tableProdutos.getSelectionModel().getSelectedIndex();
    	
    	if(linha == -1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("erro ao excluir");
    		aviso.setContentText("Selecione um produto primeiro");
    		aviso.show();	
    	}else {
    		produtoEditar = tableProdutos.getItems().get(linha);
    		Main.TelaCadastroProduto();
    		
    	}
       carregarTableProduto();
    }
	

	@FXML
	void actionExcluir(ActionEvent event) {
		int linha = tableProdutos.getSelectionModel().getSelectedIndex();

		if(linha == -1) {
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("erro ao excluir");
			aviso.setContentText("Selecione um produto primeiro");
			aviso.show();	
		}else {

			Produto produto  = new Produto();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = tableProdutos.getItems().get(linha);

			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setHeaderText("Excluir Produto");
			msg.setContentText("Excluir");
			msg.setTitle("Deseja realmente excluir o Produto"+produto.getNomeProduto()+"?");
			Optional<ButtonType> confirmacao = msg.showAndWait();
			if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
				produtoDAO.delete(produto.getCodBarra());
				Alert aviso = new Alert(AlertType.INFORMATION);
				aviso.setTitle("Produto apagado!");
				aviso.setContentText("O Produto foi apagado com sucesso");
				aviso.show();
				carregarTableProduto();
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
			carregarTableProduto();
		}else {
			pesquisarTableProduto();


		}
	}

	@FXML
	void actionProdutos(ActionEvent event) {

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
		carregarTableProduto();
		produtoEditar = null;
	}
	ObservableList<Produto> arrayProdutos;
	public void carregarTableProduto() {


		ProdutoDAO produtoDAO = new ProdutoDAO();
		arrayProdutos = FXCollections.observableArrayList(produtoDAO.read());

		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnDataFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
		columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		tableProdutos.setItems(arrayProdutos);


	}
	public void pesquisarTableProduto() {	
		ProdutoDAO produtoDAO = new ProdutoDAO();
		arrayProdutos = FXCollections.observableArrayList(produtoDAO.search(txtPesquisar.getText()));

		columnId.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnDataFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
		columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		tableProdutos.setItems(arrayProdutos);


	}

}
