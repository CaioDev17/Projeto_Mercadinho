package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;
import model.RelatorioVenda;

public class RelatorioVendaDAO {
	public ArrayList<RelatorioVenda> read(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<RelatorioVenda> relatorioVendas = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_RelatorioVendas");
			rs = stmt.executeQuery();

			while(rs.next()){
				RelatorioVenda relatorioVenda = new RelatorioVenda();
				relatorioVenda.setIdVenda(rs.getString("idVenda"));
				relatorioVenda.setIdProduto(rs.getString("nomeProduto"));
				relatorioVenda.setIdFuncionario(rs.getString("nomeFuncionario"));
				relatorioVenda.setIdCliente(rs.getString("nomeCliente"));
				relatorioVenda.setPrecoUn(rs.getString("precoUn"));
				relatorioVenda.setQuantidade(rs.getString("quantidade"));
				relatorioVenda.setValorTotal(rs.getString("precoTotal"));;
				relatorioVenda.setDataVenda(rs.getString("dataVenda"));
			
				
				relatorioVendas.add(relatorioVenda);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return relatorioVendas;
	}
	public  ArrayList<RelatorioVenda> search(String pesquisa){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<RelatorioVenda> relatorioVendas = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_RelatorioVendas where nomeFuncionario like ?"
					+ "or nomeCliente like ?");
		
			stmt.setString(1,"%"+pesquisa+"%");
			stmt.setString(2,"%"+pesquisa+"%");
			rs = stmt.executeQuery();

			while(rs.next()){
				RelatorioVenda relatorioVenda = new RelatorioVenda();
				relatorioVenda.setIdVenda(rs.getString("idVEnda"));
				relatorioVenda.setIdProduto(rs.getString("idProduto"));
				relatorioVenda.setIdFuncionario(rs.getString("idFuncionario"));
				relatorioVenda.setIdCliente(rs.getString("nomeCliente"));
				relatorioVenda.setPrecoUn(rs.getString("precoUn"));
				relatorioVenda.setQuantidade(rs.getString("quantidade"));
				relatorioVenda.setValorTotal(rs.getString("precoTotal"));;
				relatorioVenda.setDataVenda(rs.getString("dataVenda"));
			
				
				relatorioVendas.add(relatorioVenda);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return relatorioVendas;
	}
}
