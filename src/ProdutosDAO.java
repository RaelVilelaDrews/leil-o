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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
         
        conn = new conectaDAO().connectDB();
       try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3,produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "cadastro com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            
        }
       
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    public void  venderProduto(String id){
        
            try {
                prep = conn.prepareStatement("UPDATE produtos SET status = 'vendido' WHERE id = " + id);
                prep.executeUpdate();
            } catch (SQLException ex) {
               System.out.println("Erro ao conectar: " + ex.getMessage());

            }
        }
     public ArrayList<ProdutosDTO> listarvendas(){
          try {
             conn = new conectaDAO().connectDB();
             String sql = "SELECT * FROM produtos WHERE id = 'vendido'";
             resultset = prep.executeQuery();
             while(resultset.next()){
                 ProdutosDTO produto = new ProdutosDTO();
                 produto.setId(resultset.getInt("id"));
                 produto.setNome(resultset.getString("nome"));
                 produto.setValor(resultset.getInt("valor"));
                 produto.setStatus(resultset.getString("status"));
                 listagem.add(produto);
             }
             } catch (SQLException ex) {
                 System.out.println("Erro ao conectar: " + ex.getMessage());
             } finally{
             
         } return listagem;
         
     }
}

    
    
    
           


