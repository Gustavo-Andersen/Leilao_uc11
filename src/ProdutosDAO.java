/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conectaDAO banco = new conectaDAO(); // Objeto para conexão com o banco
    
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = banco.connectDB(); // Estabelecendo conexão

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            
            ps.setString(1, produto.getNome());  
            ps.setInt(2, produto.getValor());   
            ps.setString(3, produto.getStatus()); 
        
            ps.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no banco: " + sqle.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    banco.disconnectDB();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }
}

