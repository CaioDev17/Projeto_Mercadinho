package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Cliente;
import model.Produto;

public class controllerCadastroProduto implements Initializable {
	@FXML
	private ChoiceBox<String> txtTipoUn;
	@FXML
	private Button btCancelar;

	@FXML
	private Button btSalvar;

	@FXML
	private TextField txtCodBarra;

	@FXML
	private DatePicker txtDataFab;

	@FXML
	private DatePicker txtDataVal;

	@FXML
	private TextField txtEstoque;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPrecoUn;

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();

	}

	@FXML
	void actionSalvar(ActionEvent event) {
		Produto produto = new Produto();
		ProdutoDAO pDAO = new ProdutoDAO();
		if(txtNome.equals("")|| txtCodBarra.getText() == null
				|| txtPrecoUn.equals("")){
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setContentText("Erro ao cadastrar! Verifique se os campos foram"+
					"preenchidos corretamente e tente novamente");
			erro.show();
		}else {
			if(controllerRelatorioProdutos.produtoEditar == null) {	
				produto.setNomeProduto(txtNome.getText());	
				produto.setCodBarra(txtCodBarra.getText());	
				produto.setTipoUn(txtTipoUn.getValue());	
				produto.setDataFab(txtDataFab.getValue().toString());	
				produto.setDataVal(txtDataVal.getValue().toString());	
				produto.setEstoque(txtEstoque.getText());	
				produto.setPrecoUn(txtPrecoUn.getText());

				pDAO.create(produto);

				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso");
				msg.setContentText("Produto cadastrado");
				msg.show();

				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();
			}else {
				produto.setNomeProduto(txtNome.getText());	
				produto.setCodBarra(txtCodBarra.getText());	
				produto.setTipoUn(txtTipoUn.getValue());	
				produto.setDataFab(txtDataFab.getValue().toString());	
				produto.setDataVal(txtDataVal.getValue().toString());	
				produto.setEstoque(txtEstoque.getText());
				produto.setPrecoUn(txtPrecoUn.getText());


				pDAO.update(produto);

				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso");
				msg.setContentText("Produto Atualizado!");
				msg.show();

				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();


			}
		}



	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(controllerRelatorioProdutos.produtoEditar != null) {
			Produto produto = new Produto();
			produto = controllerRelatorioProdutos.produtoEditar;

			txtNome.setText(produto.getNomeProduto());
			txtTipoUn.setValue(produto.getTipoUn());
			txtCodBarra.setText(produto.getCodBarra());
			txtPrecoUn.setText(produto.getPrecoUn());
			txtEstoque.setText(produto.getEstoque());

			String DataNasc = produto.getDataFab();
			DataNasc = DataNasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(DataNasc,format);
			txtDataFab.setValue(localDate);
			String DataNasc1 = produto.getDataVal();
			DataNasc1 = DataNasc1.replace("-", "/");
			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate1 = LocalDate.parse(DataNasc1,format1);
			txtDataVal.setValue(localDate1);



		}



	}
	public static boolean isDouble(String valor) {
		if (valor == null || valor.trim().isEmpty()) {
		return false;
		}
		try {
		Double.parseDouble(valor);
		return true;
		} catch (NumberFormatException e) {
		return false;
		}
		}
}
