package application;
	
import java.sql.Connection;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<Produto> produtos = new ArrayList<>();
		produto.setNomeProduto("Pao de Forma");
		produto.setCodBarra("0000000000001");
		produto.setTipoUn("10");
		produto.setPrecoUn("5.99");
		produto.setEstoque("197");
		produto.setDataFab("2025-06-03");
		produto.setDataVal("2027-06-03");
		
		produtoDAO.create(produto);
		System.out.println("Produto adicionado");
		
		
		
		
		
		
		
		
		
		
		launch(args);
	}
}
