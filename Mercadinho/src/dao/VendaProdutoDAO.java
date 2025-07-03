package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;
import model.Venda;
import model.VendaProduto;

public class VendaProdutoDAO {
	public void create(VendaProduto vendaproduto){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO VendaProduto values(?,?,?,?,?,?)");
			stmt.setString(1, vendaproduto.getIdVendaProduto());
			stmt.setString(2, vendaproduto.getIdVenda());
			stmt.setString(3, vendaproduto.getIdProduto());
			stmt.setString(4, vendaproduto.getPrecoUn());
			stmt.setString(5, vendaproduto.getQuantidade());
			stmt.setString(6, vendaproduto.getPrecoTotal());
			stmt.execute();
			System.out.println("Venda cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void update(VendaProduto vendaproduto) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE VendaProduto SET idVenda = ?, idProduto = ?,"
					+"precoUn = ?,quantidade = ?,precoTotal = ?"+"where idVendaProduto = ?");
			stmt.setString(1, vendaproduto.getIdVenda());
			stmt.setString(2, vendaproduto.getIdProduto());
			stmt.setString(3, vendaproduto.getPrecoUn());
			stmt.setString(4, vendaproduto.getQuantidade());
			stmt.setString(5, vendaproduto.getPrecoTotal());
			stmt.setString(6, vendaproduto.getIdVendaProduto());
			stmt.execute();
			System.out.println("VendaProduto cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void delete(String idVendaProduto){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;	

		try {
			stmt = con.prepareStatement("DELETE FROM VendaProduto where idVendaProduto = ?");
			stmt.setString(1, idVendaProduto);
			stmt.execute();
			System.out.println("VendaProduto excluido");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public ArrayList<VendaProduto> read(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<VendaProduto> vendaprodutos = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM VendaProduto");
			rs = stmt.executeQuery();

			while(rs.next()){
				VendaProduto vendaproduto = new VendaProduto();
				vendaproduto.setIdVendaProduto(rs.getString("idVendaProduto"));
				vendaproduto.setIdVenda(rs.getString("idVenda"));
				vendaproduto.setIdProduto(rs.getString("idProduto"));
				vendaproduto.setPrecoUn(rs.getString("precoUn"));
				vendaproduto.setQuantidade(rs.getString("quantidade"));
				vendaproduto.setPrecoTotal(rs.getString("precoTotal"));
				vendaprodutos.add(vendaproduto);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return vendaprodutos;
	}
   
	public ArrayList<VendaProduto> search(String pesquisa){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<VendaProduto> vendaprodutos = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM VendaProduto where idVendaProduto like ?");
			stmt.setString(1,"%"+pesquisa+"%");
			rs = stmt.executeQuery();
			while(rs.next()){
				VendaProduto vendaproduto = new VendaProduto();
				vendaproduto.setIdVendaProduto(rs.getString("idVendaProduto"));
				vendaproduto.setIdVenda(rs.getString("idVenda"));
				vendaproduto.setIdProduto(rs.getString("idProduto"));
				vendaproduto.setPrecoUn(rs.getString("precoUn"));
				vendaproduto.setQuantidade(rs.getString("quantidade"));
				vendaproduto.setPrecoTotal(rs.getString("precoTotal"));
				vendaprodutos.add(vendaproduto);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return vendaprodutos;
	}
}
