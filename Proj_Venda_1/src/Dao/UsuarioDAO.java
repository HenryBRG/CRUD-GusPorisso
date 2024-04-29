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
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3,cliente.getData_de_nascimento());
            stmt.setFloat(4, cliente.getPeso());
            stmt.setFloat(5,cliente.getAltura());
            stmt.executeUpdate();
            stmt.close();
                    
        }
        catch(SQLException and){
            throw new RuntimeException("Erro ao tentar atualizar os dados do cliente: " + and.getMessage());
        }
    }
    
    public List<Cliente> consultar() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from cliente";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(res.getString("cpf"));
                cliente.setNome(res.getString("nome"));
                cliente.setData_de_nascimento(res.getString("data_de_nascimento"));
                cliente.setPeso(res.getFloat("peso"));
                cliente.setAltura(res.getFloat("altura"));
                listaClientes.add(cliente);
            }
            res.close();
            stmt.close();
        }
        catch (SQLException and){
            throw new RuntimeException("Erro ao consultar os clientes: " + and.getMessage());
        }
        return listaClientes;
    }
    
 
    
}
