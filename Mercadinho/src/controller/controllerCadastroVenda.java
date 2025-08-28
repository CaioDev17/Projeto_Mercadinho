package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import dao.VendaProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import model.RelatorioVenda;
import model.Venda;
import model.VendaProduto;

public class controllerCadastroVenda implements Initializable {

	@FXML
	private TableColumn<VendaProduto,String> ColumnPrecoTtl;

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btSalvar;

	@FXML
	private TableColumn<VendaProduto,String> columnId;

	@FXML
	private TableColumn<VendaProduto,String> columnNomePdt;

	@FXML
	private TableColumn<VendaProduto,String> columnPrecoUn;

	@FXML
	private TableColumn<VendaProduto,String> columnQtd;

	@FXML
	private TableColumn<VendaProduto,String> columnTipoUn;
	
	@FXML
	private TableColumn<VendaProduto,String> columnPrecoTtl;

	@FXML
	private TableView<VendaProduto> tableVenda;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtDesconto;

	@FXML
	private TextField txtNome;

	@FXML
	private ChoiceBox<String> txtPagamento;

	@FXML
	private TextField txtPrecoUn;

	@FXML
	private TextField txtQtd;

	@FXML
	private ChoiceBox<String> txtTipoUn;

	@FXML
	private TextField txtTotalCompra;

	@FXML
	private TextField txtVendedor;
	@FXML
	private TextField txtProduto;

	@FXML
	void actionAdicionar(ActionEvent event) {
     if(txtPrecoUn.getText().isEmpty()|| txtNome.getText().isEmpty()||txtCpf.getText().isEmpty()
    		 ||txtQtd.getText().isEmpty()) {
    	 Alert aviso = new Alert(AlertType.ERROR);
    	 aviso.setTitle("Erro!");
    	 aviso.setContentText("Preencha os campos corretamente!");
    	 aviso.show();
     }else {
    	 VendaProduto vendaP = new VendaProduto();
    	 vendaP.setIdProduto(txtProduto.getText());
    	 vendaP.setPrecoUn(txtPrecoUn.getText());
    	 vendaP.setTipoUn(txtTipoUn.getValue());
    	 vendaP.setQuantidade(txtQtd.getText());
    	 
    	 double desconto = Double.parseDouble(txtDesconto.getText().replace(",", "."));
    	 double precoUni = Double.parseDouble(txtPrecoUn.getText().replace(",", "."));
    	 double quantidade = Double.parseDouble(txtQtd.getText());
    	 double precoTtl = precoUni * quantidade - desconto;
    	 vendaP.setPrecoTotal("R$ "+precoTtl);
    	 double TotalCompra = Double.parseDouble(txtTotalCompra.getText().replace(",", "."));
    	 TotalCompra = TotalCompra + precoTtl;
    	 txtTotalCompra.setText(""+ String.format("%.2f", TotalCompra));
    	 arrayProdutos.add(vendaP);
    	 carregarTableProdutos(arrayProdutos);
    	 txtProduto.setText("");
    	 txtPrecoUn.setText("");
    	 txtDesconto.setText("0,00");
    	 txtQtd.setText("");
    	 txtTipoUn.setValue("");
    	 
    	 
     }
	}

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();

	}

	@FXML
	void actionSalvar(ActionEvent event) {
		Venda venda = new Venda();
		Funcionario func = new Funcionario();
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		Cliente cliente = new Cliente();
		ClienteDAO clienteD = new ClienteDAO();
		cliente.setCpfCliente(txtCpf.getText());
		func.setCpfFuncionario(controllerLogin.funcionario.getCpfFuncionario());
		
		cliente = clienteD.search(cliente.getCpfCliente()).get(0);
		func = funcDAO.search(func.getCpfFuncionario()).get(0);
		
		venda.setIdFuncionario(func.getIdFuncionario());
		venda.setIdCliente(cliente.getIdCliente());
		venda.setValorTotal(txtTotalCompra.getText().replace(",", "."));
		venda.setQuantTotal(""+ arrayProdutos.size());
		VendaDAO vendaD = new VendaDAO();
		vendaD.create(venda);
		venda = vendaD.readLastId().get(0);
		for(int i = 0;i <= arrayProdutos.size();i++) {
			VendaProduto vendaP = new VendaProduto();
			VendaProdutoDAO vendaPDAO = new VendaProdutoDAO();
			
			vendaP = arrayProdutos.get(i);
			vendaP.setIdProduto(venda.getIdVenda());
			vendaP.setPrecoTotal(vendaP.getPrecoTotal().trim().replace("R$",""));
			Produto p = new Produto();
			ProdutoDAO pDAO = new ProdutoDAO();
			p.setNomeProduto(vendaP.getIdProduto());
			p = pDAO.search(p.getNomeProduto()).get(0);
			vendaP.setIdProduto(p.getIdProduto());
			vendaPDAO.create(vendaP);
			
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtTipoUn.getItems().addAll("UN","KG","LT","GM");
		txtPagamento.getItems().addAll("Pix","Cartao","Dinheiro");
		txtVendedor.setText(controllerLogin.funcionario.getNomeFuncionario());
		txtVendedor.setEditable(false);
		txtPrecoUn.setEditable(false);
		txtTotalCompra.setText("0.00");
		txtTotalCompra.setEditable(false);
		ClienteDAO clienteDAO = new ClienteDAO();
		String[] listaClientes = new String[clienteDAO.read().size()];
		ArrayList<Cliente> arrayClientes = clienteDAO.read();
		for(int i = 0; i < clienteDAO.read().size();i++) {
			Cliente cliente = new Cliente();
			cliente = arrayClientes.get(i);
			listaClientes[i] = cliente.getNomeCliente();
		}
		TextFields.bindAutoCompletion(txtNome,listaClientes).setOnAutoCompleted(event -> actionCPFClick(null));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		String[] listaProdutos = new String[produtoDAO.read().size()];
		ArrayList<Produto> arrayProdutos = produtoDAO.read();
		for(int i = 0; i < produtoDAO.read().size();i++) {
			Produto produto = new Produto();
			produto = arrayProdutos.get(i);
			listaProdutos[i] = produto.getNomeProduto();
		}
		TextFields.bindAutoCompletion(txtProduto,listaProdutos).setOnAutoCompleted(event -> actionProdutoClick(null));
	}
	@FXML
	void actionCPFClick(MouseEvent event) {
		if(txtNome.getText().length() > 3) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNomeCliente(txtNome.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();        
			clientes = clienteDAO.search(cliente.getNomeCliente());
			cliente = clientes.get(0);
			txtCpf.setText(cliente.getCpfCliente());
		}else {
			txtCpf.setText("");
		}
	}

	@FXML
	void actionCPFType(KeyEvent event) throws Exception {
		if(txtNome.getText().length() > 3) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNomeCliente(txtNome.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();        
			clientes = clienteDAO.search(cliente.getNomeCliente());
			cliente = clientes.get(0);
			txtCpf.setText(cliente.getCpfCliente());                        
		}else {
			txtCpf.setText("");
		}
	}
	@FXML
	void actionProdutoClick(MouseEvent event) {
		if(txtProduto.getText().length()> 3) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNomeProduto(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();        
			produtos = produtoDAO.search(produto.getNomeProduto());
			produto = produtos.get(0);
			if(produto!= null) {
				txtTipoUn.setValue(produto.getTipoUn());
				double precoUn = Double.parseDouble(produto.getPrecoUn());
				txtPrecoUn.setText(String.format("%.2f", precoUn));
			}
		}
	}

	@FXML
	void actionProdutoType(KeyEvent event) {
		if(txtProduto.getText().length()> 3) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNomeProduto(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();        
			produtos = produtoDAO.search(produto.getNomeProduto());
			produto = produtos.get(0);
			if(produto!= null) {
				txtTipoUn.setValue(produto.getTipoUn());
				double precoUn = Double.parseDouble(produto.getPrecoUn());
				txtPrecoUn.setText(String.format("%.2f", precoUn));
			}
		}

	}
	@FXML
	void actionQuantidade(KeyEvent event) {
		if(!txtQtd.getText().isEmpty()){
			double quantidade = Double.parseDouble(txtQtd.getText());
			double precoUni = Double.parseDouble(txtPrecoUn.getText().replace(",", "."));
			if(quantidade >= 15) {

				double desconto = (precoUni*quantidade)* 0.10;
				txtDesconto.setText("" + String.format("%.2f",desconto));  
			}else if(quantidade < 15) {
				txtDesconto.setText("0.00");
			}
		}
	}
	private ArrayList<VendaProduto> arrayProdutos = new ArrayList<>();
	public void carregarTableProdutos(ArrayList<VendaProduto> ArrayProdutos) {
ObservableList<VendaProduto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);
		 

		columnId.setCellValueFactory(new PropertyValueFactory<>("idVendaProduto"));
		columnNomePdt.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
		columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnPrecoTtl.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		
		
		tableVenda.setItems(produtosVendidos);


	}
}


