
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    Connection  conn = null;
    
    public Connection connectDB(){
        
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","rootTeste123");
            System.out.println("teste deu certo");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public boolean disconnectDB(){
        
        try{
            
        
           if(conn != null && !conn.isClosed()){
              
               conn.close();
               return true;
           }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao desconectar do banco" + sqle.getMessage());
            return false;
        }
        return false;
    }
    
}
