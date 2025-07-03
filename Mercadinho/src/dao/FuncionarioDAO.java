package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Funcionario;


public class FuncionarioDAO {
	public void create(Funcionario funcionario){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO Cliente values(?,?,?,?,?,?,?)");
			stmt.setString(1, funcionario.getNomeFuncionario());
			stmt.setString(2, funcionario.getCpfFuncionario());
			stmt.setString(3, funcionario.getDataNasc());
			stmt.setString(4, funcionario.getTelefone());
			stmt.setString(5, funcionario.getEmail());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setString(7, funcionario.getGenero());
			stmt.execute();
			System.out.println("Funcionario cadastrado");
		} catch (SQLException e) {

			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {

			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void update(Funcionario funcionario) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;


		try {
			stmt = con.prepareStatement("UPDATE Funcionario SET nomeFuncionario = ?, cpfFuncionario = ?,"
					+"dataNasc = ?,telefone = ?,email = ?,endereco = ?,genero = ?"+"where cpfCliente = ?");
			stmt.setString(1, funcionario.getNomeFuncionario());
			stmt.setString(2, funcionario.getCpfFuncionario());
			stmt.setString(3, funcionario.getDataNasc());
			stmt.setString(4, funcionario.getTelefone());
			stmt.setString(5, funcionario.getEmail());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setString(7, funcionario.getGenero());
			stmt.setString(8, funcionario.getCpfFuncionario());
			stmt.execute();
			System.out.println("Funcionario cadastrado");
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}


	}
	public void delete(String cpf){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;	

		try {
			stmt = con.prepareStatement("DELETE FROM Funcionario where cpfFuncionario = ?");
			stmt.setString(1, cpf);
			stmt.execute();
			System.out.println("Funcionario excluido");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public ArrayList<Funcionario> read(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> funcionarios = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM Funcionario");
			rs = stmt.executeQuery();

			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getString("idFuncionario"));
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
				funcionario.setDataNasc(rs.getString("dataNasc"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setGenero(rs.getString("genero"));
				funcionario.setNivel(rs.getString("nivel"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return funcionarios;
	}
	public ArrayList<Funcionario> search(String pesquisa){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> funcionarios = new ArrayList();
		try {
			stmt = con.prepareStatement("SELECT * FROM Funcionario where nomeFuncionario like ? or cpfFuncionario like ?");
			stmt.setString(1,"%"+pesquisa+"%");
			stmt.setString(2,"%"+pesquisa+"%");
			rs = stmt.executeQuery();

			while(rs.next()){
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getString("idFuncionario"));
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
				funcionario.setDataNasc(rs.getString("dataNasc"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setGenero(rs.getString("genero"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		return funcionarios;
	}
}
