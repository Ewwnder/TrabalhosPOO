/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.control;

import fatec.poo.model.Exame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LucasMorais
 */
public class DaoExame {
    
    private Connection conn;
    private PreparedStatement ps = null;
    
    public DaoExame(Connection conn) {
        this.conn = conn;
    }
    
    public void inserirExame(Exame exame){
        try{
            ps = conn.prepareStatement("INSERT INTO tbExame (CodigoConsulta, Descricao, Data, Horario, Valor) VALUES (?, ?, ?, ?, ?");
            ps.setInt(1, exame.getConsulta().getCodigo());
            ps.setString(2, exame.getDescricao());
            ps.setString(3, exame.getData());
            ps.setString(4, exame.getHorario());
            ps.setDouble(5, exame.getValor());
            exame.getConsulta().addExame(exame); //precisa?
            ps.execute();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Houve um erro ao inserir exame: " + ex.getMessage());
            System.out.println(ex.toString());
        }
        
    }
    
    public Exame consultarExame(int codigo){
      Exame exame = null;
      
      
      try{
          ps = conn.prepareStatement("SELECT * FROM tbExame WHERE Codigo = ?");
          ps.setInt(1, codigo);
          ResultSet rs = ps.executeQuery();
          
          if(rs.next()){
              exame = new Exame(codigo, rs.getString("Descricao"));
 
              exame.setData(rs.getString("Data"));
              exame.setHorario(rs.getString("Horario"));
              exame.setValor(rs.getDouble("Valor"));
              //Pode usar o DaoConsulta ou fazer um inner join?
              //exame.setConsulta();
          }
      
      }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Houve um erro ao consultar exame: " + ex.getMessage());
          System.out.println(ex.toString());
      }
        return exame;
    }
    
    public void alterarExame(int codExame, String descricao, String data, String horario, double valor){
        try{
            ps = conn.prepareStatement("UPDATE tbExame SET Descricao = ?, Data = ?, Valor = ?, Horario = ? WHERE Codigo = ?");
            ps.setString(1, descricao);
            ps.setString(2, data);
            ps.setString(3, horario);
            ps.setDouble(4, valor);
            ps.setInt(5, codExame);
            ps.execute();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Houve um erro ao alterar exame: " + ex.getMessage());
            System.out.println(ex.toString());
        }  
    }
    
    public void excluirExame(int codExame){
        try{
            ps = conn.prepareStatement("DELETE FROM tbExame WHERE Codigo = ?");
            ps.setInt(1, codExame);
            ps.execute();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Houve um erro ao deletar exame: " + ex.getMessage());
            System.out.println(ex.toString());
        }
    }
    
}
