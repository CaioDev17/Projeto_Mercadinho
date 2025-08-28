package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	private static Stage stage;
	private static Scene main;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));
			stage.setTitle("Tela de login");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo.png")));
			main = new Scene(fxmlLogin);
			primaryStage.setScene(main);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void telaLogin() throws IOException{
		FXMLLoader fxmlLogin = new FXMLLoader();
		fxmlLogin.setLocation(Main.class.getResource("/view/viewLogin.fxml"));
		Parent TelaLogin = fxmlLogin.load();
		main = new Scene(TelaLogin);
		stage.setTitle("Tela de login");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaHome() throws IOException{
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/view/viewMenu.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		stage.setTitle("Mercado - Menu Principal");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();


	}

	public static void TelaCliente() throws IOException{
		FXMLLoader fxmlCliente = new FXMLLoader();
		fxmlCliente.setLocation(Main.class.getResource("/view/viewRelatorioClientes.fxml"));
		Parent TelaCliente = fxmlCliente.load();
		main = new Scene(TelaCliente);
		stage.setTitle("Mercado - Relatorio Cliente");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaProduto() throws IOException{
		FXMLLoader fxmlProduto = new FXMLLoader();
		fxmlProduto.setLocation(Main.class.getResource("/view/viewRelatorioProduto.fxml"));
		Parent TelaProduto = fxmlProduto.load();
		main = new Scene(TelaProduto);
		stage.setTitle("Mercado - Relatorio Produto");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	private static Stage cadCliente;
	public static void TelaCadastroCliente() throws IOException {

		FXMLLoader ClienteCadastro = new FXMLLoader();
		ClienteCadastro.setLocation(Main.class.getResource("/view/viewCadastroCliente.fxml"));
		Parent cadastroClin = ClienteCadastro.load();
		Scene scene2 = new Scene(cadastroClin);

		cadCliente = new Stage();
		cadCliente.setTitle("Cadastro/Edição do Cliente");
		cadCliente.initModality(Modality.WINDOW_MODAL);
		cadCliente.setScene(scene2);
		cadCliente.centerOnScreen();
		cadCliente.showAndWait();	
	}
	private static Stage cadProduto;
	public static void TelaCadastroProduto() throws IOException {

		FXMLLoader ProdutoCadastro = new FXMLLoader();
		ProdutoCadastro.setLocation(Main.class.getResource("/view/viewCadastroProduto.fxml"));
		Parent cadastroProd = ProdutoCadastro.load();
		Scene scene2 = new Scene(cadastroProd);

		cadProduto = new Stage();
		cadProduto.setTitle("Cadastro/Edição de Produto");
		cadProduto.initModality(Modality.WINDOW_MODAL);
		cadProduto.setScene(scene2);
		cadProduto.centerOnScreen();
		cadProduto.showAndWait();	
	}
	public static void TelaRegistrarVenda() throws IOException {

		FXMLLoader RegistrarVenda = new FXMLLoader();
		RegistrarVenda.setLocation(Main.class.getResource("/view/viewCadastroVenda.fxml"));
		Parent cadastroVenda = RegistrarVenda.load();
		Scene scene2 = new Scene(cadastroVenda);

		cadProduto = new Stage();
		cadProduto.setTitle("Cadastro Venda");
		cadProduto.initModality(Modality.WINDOW_MODAL);
		cadProduto.setScene(scene2);
		cadProduto.centerOnScreen();
		cadProduto.showAndWait();	
	}
	public static void TelaVenda() throws IOException{
		FXMLLoader fxmlVenda = new FXMLLoader();
		fxmlVenda.setLocation(Main.class.getResource("/view/viewRelatorioVendas.fxml"));
		Parent TelaVenda = fxmlVenda.load();
		main = new Scene(TelaVenda);
		stage.setTitle("Mercado - Relatorio Venda");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
