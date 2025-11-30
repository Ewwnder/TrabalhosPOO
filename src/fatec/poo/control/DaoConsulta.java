/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.control;

import fatec.poo.model.Consulta;
import fatec.poo.model.Exame;
import fatec.poo.model.Medico;
import fatec.poo.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LucasMorais
 */
public class DaoConsulta {
    
    private Connection conn;

    
    public DaoConsulta(Connection conn) {
        this.conn = conn;
    }
    
    public Consulta consultarConsulta(int codConsulta){
        PreparedStatement ps = null;
        Consulta consulta = null;
        try{
            ps = conn.prepareStatement("SELECT * FROM tbConsulta WHERE Codigo = ?");
            ps.setInt(1, codConsulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                consulta = new Consulta(codConsulta, rs.getString("Data"));
                consulta.setValor(rs.getDouble("Valor"));
                Medico medico = new DaoMedico(conn).consultarMedico(rs.getString("CPFMedico")); //ERICAAAAAAAAAAAAAAAAAA
                consulta.setMedico(medico);
                medico.addConsulta(consulta);
                //Esperando o Dimas responder:
                //Paciente paciente = new DaoPaciente(conn).consultarPaciente(rs.getString("CPFPaciente"));
                //consulta.setPaciente(
            }
        } catch(SQLException ex){
            System.out.println(ex.toString());
        }
        
        return consulta;
    }
    
    public void inserirConsulta(Consulta consulta){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("INSERT INTO tbConsulta (Codigo, CPFMedico, CPFPaciente, Data, Valor) VALUES (?, ?, ?, ?, ?");
            ps.setInt(1, consulta.getCodigo());
            ps.setString(2, consulta.getMedico().getCpf());
            //Esperando resposta do Dimas para manter o codigo abaixo:
            //ps.setString(3, consulta.getPaciente().getCpf());
            
            ps.setString(4, consulta.getData());
            ps.setDouble(5, consulta.getValor());
      
            consulta.getMedico().addConsulta(consulta);
            //Se o código de cima for válido, o de baixo também deveria ser. Aguardando Dimas F. Cardoso:
            //consulta.getPaciente().addConsulta(consulta);
            ps.execute();
        }catch(SQLException ex){ 
            System.out.println(ex.toString());
        }
        
    }
    
    public void alterarConsulta(Consulta consulta){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("UPDATE tbConsulta SET Data = ?, Valor = ? WHERE Codigo = ?");
            
            ps.setString(1, consulta.getData());
            ps.setDouble(2, consulta.getValor());
            ps.setInt(3, consulta.getCodigo());
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }  
    }
    
     public void excluirConsulta(Consulta consulta){
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("DELETE FROM tbConsulta WHERE Codigo = ?");
            ps.setInt(1, consulta.getCodigo());
            ps.execute();
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
}
