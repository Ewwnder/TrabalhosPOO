/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.control;

import fatec.poo.model.Consulta;
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
    
    
    public DaoExame(Connection conn) {
        this.conn = conn;
    }
    
    
    
    public Exame consultarExame(int codigo){
      PreparedStatement ps = null;
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
              
              //Fazendo a busca da consulta + associação binária
              Consulta consulta = new DaoConsulta(conn).consultarConsulta(rs.getInt("CodigoConsulta"));
              exame.setConsulta(consulta);
              
           
          }
      
      }catch(SQLException ex){
          System.out.println(ex.toString());
      }
        return exame;
    }
    
    public void inserirExame(Exame exame){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("INSERT INTO tbExame (Codigo, CodigoConsulta, Descricao, Data, Horario, Valor) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, exame.getCodigo());
            ps.setInt(2, exame.getConsulta().getCodigo());
            ps.setString(3, exame.getDescricao());
            ps.setString(4, exame.getData());
            ps.setString(5, exame.getHorario());
            ps.setDouble(6, exame.getValor());
            ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        
    }
    
    public void alterarExame(Exame exame){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("UPDATE tbExame SET Descricao = ?, Data = ?, Horario = ?, Valor = ? WHERE Codigo = ?");
            ps.setString(1, exame.getDescricao());
            ps.setString(2, exame.getData());
            ps.setString(3, exame.getHorario());
            ps.setDouble(4, exame.getValor());
            ps.setInt(5, exame.getCodigo());
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }  
    }
    
    public void excluirExame(Exame exame){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("DELETE FROM tbExame WHERE Codigo = ?");
            ps.setInt(1, exame.getCodigo());
            ps.execute();
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
}
