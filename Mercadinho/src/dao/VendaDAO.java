package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;
import model.Venda;

public class VendaDAO {
	
	public void create(Venda venda){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO Venda values(?,?,?,?,GETDATE())");
			stmt.setString(1, venda.getIdCliente());
			stmt.setString(2, venda.getIdFuncionario());
			stmt.setString(3, venda.getValorTotal());
			stmt.setString(4, venda.getQuantTotal());
			stmt.execute();
			System.out.println("Venda cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void update(Venda venda) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE Venda SET idCliente = ?, idFuncionario = ?,"
					+"valorTotal = ?,quantTotal = ?,dataVenda = ? "+"where idVenda = ?");
			stmt.setString(1, venda.getIdCliente());
			stmt.setString(2, venda.getIdFuncionario());
			stmt.setString(3, venda.getValorTotal());
			stmt.setString(4, venda.getQuantTotal());
			stmt.setString(5, venda.getDataVenda());
			stmt.setString(6, venda.getIdVenda());
			stmt.execute();
			System.out.println("venda atualizada");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void delete(String idVenda){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;	

		try {
			stmt = con.prepareStatement("DELETE FROM Venda where idVenda = ?");
			stmt.setString(1, idVenda);
			stmt.execute();
			System.out.println("Venda excluida");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public ArrayList<Venda> read(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> vendas = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM Venda");
			rs = stmt.executeQuery();
			while(rs.next()){
				Venda venda = new Venda();
				venda.setIdVenda(rs.getString("idVenda"));
				venda.setIdCliente(rs.getString("idCliente"));
				venda.setIdFuncionario(rs.getString("idFuncionario"));
				venda.setValorTotal(rs.getString("valorTotal"));
				venda.setQuantTotal(rs.getString("quantTotal"));
				venda.setDataVenda(rs.getString("dataVenda"));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao exibir!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return vendas;
	}
	public ArrayList<Venda> search(String pesquisa){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> vendas = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM Venda where idVenda like ?");
			stmt.setString(1,"%"+pesquisa+"%");
			rs = stmt.executeQuery();

			while(rs.next()){
				Venda venda = new Venda();
				venda.setIdVenda(rs.getString("idVenda"));
				venda.setIdCliente(rs.getString("idCliente"));
				venda.setIdFuncionario(rs.getString("idFuncionario"));
				venda.setValorTotal(rs.getString("valorTotal"));
				venda.setQuantTotal(rs.getString("quantTotal"));
				venda.setDataVenda(rs.getString("dataVenda"));
				
				vendas.add(venda);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao procurar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return vendas;
	}
	public ArrayList<Venda> readLastId(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> vendas = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM Venda order by IdVenda desc");
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Venda venda = new Venda();
				venda.setIdVenda(rs.getString("idVenda"));
				venda.setIdCliente(rs.getString("idCliente"));
				venda.setIdFuncionario(rs.getString("idFuncionario"));
				venda.setValorTotal(rs.getString("valorTotal"));
				venda.setQuantTotal(rs.getString("quantTotal"));
				venda.setDataVenda(rs.getString("dataVenda"));
				
				vendas.add(venda);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao procurar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return vendas;
	}
}