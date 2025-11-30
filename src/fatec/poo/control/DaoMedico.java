/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.control;

import fatec.poo.model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erica e Nicolas Ap
 */
public class DaoMedico {
    
    private Connection conn;

    public DaoMedico(Connection conn) {
        this.conn = conn;
    }
    
    public void inserirMedico (Medico medico){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("INSERT INTO tbMedico (CPF, Nome, Endereco, Telefone, CRM, Especialidade) VALUES (?,?,?,?,?,?)");
            ps.setString(1, medico.getCpf());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getEndereco());
            ps.setString(4, medico.getTelefone());
            ps.setString(5, medico.getCrm());
            ps.setString(6, medico.getEspecialidade());
            
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void alterarMedico (Medico medico){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("UPDATE tbMedico SET Nome = ?, Endereco = ?, Telefone = ?, CRM = ?, Especialidade = ? WHERE CPF = ?");
            
            ps.setString(1, medico.getNome());
            ps.setString(2, medico.getEndereco());
            ps.setString(3, medico.getTelefone());
            ps.setString(4, medico.getCrm());
            ps.setString(5, medico.getEspecialidade());
            ps.setString(6, medico.getCpf());
            
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString());  
        }
    }
    
    public Medico consultarMedico(String cpf){
        Medico medico = null;
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM tbMedico WHERE CPF = ?");
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){            
                medico = new Medico(rs.getString("CPF"), rs.getString("Nome"), rs.getString("CRM"), rs.getString("Especialidade"));
                medico.setEndereco(rs.getString("Endereco"));
                medico.setTelefone(rs.getString("Telefone"));
            }
        } catch (SQLException ex){
            System.out.println(ex.toString());  
        }
        
        return medico;
    }
    
    public void excluirMedico (String cpf){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("DELETE FROM tbMedico WHERE CPF = ?");
            ps.setString(1, cpf);
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString()); 
        }
    }
}
