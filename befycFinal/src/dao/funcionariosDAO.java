/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import dto.funcionariosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class funcionariosDAO {
    
  Connection conn;
  PreparedStatement pstm;
  ResultSet rs;
 ArrayList<funcionariosDTO> lista = new ArrayList<>();
  
  public void cadastrarfuncionarios(funcionariosDTO objfuncionarios){
     String sql = "insert into funcionarios (nome,nascimento,endereço,numero,email,senha) values (?,?,?,?,?,?)"; 
     
     conn = new conexao().conectaBD();
     
      try {
         
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, objfuncionarios.getNome());
        pstm.setString(2, objfuncionarios.getNascimento());
        pstm.setString(3, objfuncionarios.getEndereço());
        pstm.setString(4, objfuncionarios.getNumero());
        pstm.setString(5, objfuncionarios.getEmail());
        pstm.setString(6, objfuncionarios.getSenha());
        
        
        pstm.execute();
        pstm.close();
        
        JOptionPane.showMessageDialog(null,"Usuário adicionado com sucesso");
     

      } catch (Exception erro) {
          
         JOptionPane.showMessageDialog(null, "FuncioriosDAO" + erro);
      }
      
    }
  
    public ResultSet loginusuario(funcionariosDTO objfuncionarios){
      conn = new conexao().conectaBD();
      
      
      
        try {
            String sql = "Select * from funcionarios where email = ? and senha = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionarios.getEmail());
            pstm.setString(2, objfuncionarios.getSenha());
            
            
            ResultSet rs = pstm.executeQuery();
            return rs;
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "funcionariosDAO" + erro);
            return null;
          
        }
    }
    
    public ArrayList<funcionariosDTO> PesquisarFuncionario(){
            String sql = "select * from funcionarios";
            conn = new conexao().conectaBD();
        
            try {
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                
                while(rs.next()){
                    funcionariosDTO objfuncionariosDTO = new funcionariosDTO();
                    
                    objfuncionariosDTO.setCodigo(rs.getInt("codigo"));
                    objfuncionariosDTO.setNome(rs.getString("nome"));
                    objfuncionariosDTO.setNascimento(rs.getString("nascimento"));
                    objfuncionariosDTO.setEndereço(rs.getString("endereço"));
                    objfuncionariosDTO.setNumero(rs.getString("numero"));
                    objfuncionariosDTO.setEmail(rs.getString("email"));
                    objfuncionariosDTO.setSenha(rs.getString("senha"));
                    lista.add(objfuncionariosDTO);
                }
                
            }catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "FunionarioDAO pesquisar: " + erro);
            }
            return lista;
    }
    
    public void alterarFuncionario(funcionariosDTO objfuncionariosdto){
        String sql = "update funcionarios set nome= ?, nascimento= ?, endereço= ?, numero= ?, email= ?, senha= ? where codigo = ?"; 
    conn = new conexao().conectaBD();
     
      try {
         
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, objfuncionariosdto.getNome());
        pstm.setString(2, objfuncionariosdto.getNascimento());
        pstm.setString(3, objfuncionariosdto.getEndereço());
        pstm.setString(4, objfuncionariosdto.getNumero());
        pstm.setString(5, objfuncionariosdto.getEmail());
        pstm.setString(6, objfuncionariosdto.getSenha());
        pstm.setInt(7, objfuncionariosdto.getCodigo());
        
        
        pstm.execute();
        pstm.close();
        
        JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso");
     

      } catch (Exception erro) {
          
         JOptionPane.showMessageDialog(null, "FuncioriosDAO alterar" + erro);
      }
    }
    
    public void excluirFuncionario(funcionariosDTO objfuncionariosdto){
        String sql = "delete from funcionarios where codigo = ?"; 
        conn = new conexao().conectaBD();
     
        try {

          pstm = conn.prepareStatement(sql);
          pstm.setInt(1, objfuncionariosdto.getCodigo());


          pstm.execute();
          pstm.close();

          JOptionPane.showMessageDialog(null,"Usuário excluido com sucesso");


        } catch (Exception erro) {

           JOptionPane.showMessageDialog(null, "FuncioriosDAO excluir" + erro);
        }
    }
}
    