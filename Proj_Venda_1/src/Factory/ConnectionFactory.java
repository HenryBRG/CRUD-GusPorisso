/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
/**
 *
 * @author conta
 */
public class ConnectionFactory {
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/vendas","root","guitarhero");
        }
        catch(SQLException excecao){
            throw new RuntimeException(excecao);
        }
    }
    
}
