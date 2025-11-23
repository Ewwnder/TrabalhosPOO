package fatec.poo.control;

import fatec.poo.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Nicolas Ap
 */
public class DaoPaciente {
    
    private Connection conn;

    public DaoPaciente(Connection conn) {
        this.conn = conn;
    }
    
    public void inserirPaciente (Paciente paciente){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("INSERT INTO tbPaciente (CPF, Nome, Endereco, Telefone, DataNascimento, Altura, Peso) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getNome());
            ps.setString(3, paciente.getEndereco());
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getDataNascimento());
            ps.setDouble(6, paciente.getAltura());
            ps.setDouble(7, paciente.getPeso());
            
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void alterarPaciente (Paciente paciente){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("UPDATE tbPaciente SET Nome = ?, Endereco = ?, Telefone = ?, DataNascimento = ?, Altura = ?, Peso = ? WHERE CPF = ?");
            
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getEndereco());
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getDataNascimento());
            ps.setDouble(5, paciente.getAltura());
            ps.setDouble(6, paciente.getPeso());
            ps.setString(7, paciente.getCpf());
            
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString());  
        }
    }
    
    public Paciente consultarPaciente(String cpf){
        Paciente paciente = null;
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM tbPaciente WHERE CPF = ?");
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                
                String dataFormatoString = rs.getString("DataNascimento");
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataNascimento = LocalDate.parse(dataFormatoString, formato);
                
                paciente = new Paciente(rs.getString("CPF"), rs.getString("Nome"), dataNascimento);
                paciente.setEndereco(rs.getString("Endereco"));
                paciente.setTelefone(rs.getString("Telefone"));
                paciente.setAltura(rs.getDouble("Altura"));
                paciente.setPeso(rs.getDouble("Peso"));
            }
        } catch (SQLException ex){
            System.out.println(ex.toString());  
        }
        
        return paciente;
    }
    
    public void excluirPaciente(String cpf){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("DELETE FROM tbPaciente WHERE CPF = ?");
            ps.setString(1, cpf);
            ps.execute();
        } catch (SQLException ex){
            System.out.println(ex.toString()); 
        }
    }
}
