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
import javax.swing.table.DefaultTableModel;


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
    
    public DefaultTableModel getTabela() {
    String[] colunas = {"id", "produto", "valor", "status"};
    DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

    try {
        String sql = "SELECT id, nome, valor, status FROM produtos"; 

        conn = banco.connectDB();
        prep = conn.prepareStatement(sql); 
        
        resultset = prep.executeQuery(); 

        
        while (resultset.next()) {
            Object[] linha = {
                resultset.getInt("id"),
                resultset.getString("nome"), 
                resultset.getDouble("valor"),
                resultset.getString("status")
            };
            tabela.addRow(linha);
        }
        
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Erro com a listagem: " + sqle.getMessage());
    } finally {
        banco.disconnectDB(); 
    }

    return tabela;
}
     public DefaultTableModel getTabelaP(int id) {
    String[] colunas = {"id", "produto", "valor", "status"};
    DefaultTableModel tabela = new DefaultTableModel(colunas, 0);

    try {
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE id = ?"; 

        conn = banco.connectDB();
        prep = conn.prepareStatement(sql); 
        
        prep.setInt(1, id);
                
        resultset = prep.executeQuery(); 

        
        while (resultset.next()) {
            Object[] linha = {
                resultset.getInt("id"),
                resultset.getString("nome"), 
                resultset.getDouble("valor"),
                resultset.getString("status")
            };
            tabela.addRow(linha);
        }
        
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Erro com a listagem: " + sqle.getMessage());
    } finally {
        banco.disconnectDB(); 
    }

    return tabela;
}
    
    
}

