/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Factory.ConnectionFactory;
import Modelo.Cliente;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author conta
 */
public class UsuarioDAO {
    private Connection connection;
    
    public UsuarioDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Cliente cliente){
        String sql = "INSERT INTO cliente VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt= connection.prepareStatement(sql);
           //String id_aux=Integer.toStrig(cliente.getID());
           stmt.setString(1,cliente.getCpf());
           stmt.setString(2,cliente.getNome());
           stmt.setString(3,cliente.getData_de_nascimento());
           stmt.setFloat(4,cliente.getPeso());
           stmt.setFloat(5,cliente.getAltura());
           stmt.execute();
           stmt.close();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    /**
     *
     * @param cpf
     */
    public void deletar(String cpf) {
        String sql = "delete from cliente where cpf = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,cpf );
            stmt.executeUpdate();
            stmt.close();
        } 
        catch(SQLException and){
            throw new RuntimeException("Erro ao tentar excluir o seguinte cliente: "+ and.getMessage() );
        }
        
    }
    
    public void atualizar(Cliente cliente) {
    String sql = "update cliente set nome = ?, data_de_nascimento = ?, peso = ?, altura = ? where cpf = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getData_de_nascimento());
        stmt.setFloat(3, cliente.getPeso());
        stmt.setFloat(4, cliente.getAltura());
        stmt.setString(5, cliente.getCpf());
        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Nenhum registro foi atualizado.");
        }
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Registra o erro no console
        throw new RuntimeException("Erro ao tentar atualizar os dados do cliente: " + e.getMessage());
    }
    }
    
    public Cliente consultar(String cpf) {
    String sql = "SELECT * FROM cliente WHERE cpf = ?";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        
        // Se encontrou um registro, criar um objeto Cliente com os dados
        if (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setData_de_nascimento(rs.getString("data_de_nascimento"));
            cliente.setPeso(rs.getFloat("peso"));
            cliente.setAltura(rs.getFloat("altura"));
            return cliente;
        } else {
            // Se não encontrou, retornar null
            return null;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao consultar cliente: " + e.getMessage());
    }
}
}
