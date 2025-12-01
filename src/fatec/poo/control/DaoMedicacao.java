package fatec.poo.control;

import fatec.poo.model.Consulta;
import fatec.poo.model.Medicacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nicolas Ap
 */
public class DaoMedicacao {
    
    private Connection conn;

    public DaoMedicacao(Connection conn) {
        this.conn = conn;
    }

    public void inserirMedicacao(Medicacao medicacao, Consulta consulta) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("INSERT INTO tbMedicacao (Nome, CodigoConsulta, Dosagem, QuantidadeDias) VALUES (?,?,?,?)");
            ps.setString(1, medicacao.getNome());
            ps.setInt(2, consulta.getCodigo());
            ps.setString(3, medicacao.getDosagem());
            ps.setInt(4, medicacao.getQtdeDias());
           
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void alterarMedicacao(Medicacao medicacao) {
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("UPDATE tbMedicacao SET Dosagem=?, QuantidadeDias=? WHERE Nome=?");
            ps.setString(1, medicacao.getDosagem());
            ps.setInt(2, medicacao.getQtdeDias());
            ps.setString(3, medicacao.getNome());
            
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro alterar Medicacao: " + e.toString());
        }
    }

    public Medicacao consultarMedicacao(String nome) {
        Medicacao medicacao = null;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM tbMedicacao WHERE Nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                medicacao = new Medicacao(nome);
                medicacao.setDosagem(rs.getString("Dosagem"));
                medicacao.setQtdeDias(rs.getInt("QuantidadeDias"));
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar Medicacao: " + e.toString());
        }

        return medicacao;
    }

    public void excluirMedicacao(String nome) {
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement("DELETE FROM tbMedicacao WHERE Nome=?");
            ps.setString(1, nome);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro excluir Medicacao: " + e.toString());
        }
    }
    
    public Consulta consultarConsulta(String nome){
        Consulta consulta = null;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM tbMedicacao WHERE Nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                consulta = new DaoConsulta(conn).consultarConsulta(rs.getInt("CodigoConsulta"));       
            }
        } catch (SQLException e) {
            System.out.println("Erro consultar consulta: " + e.toString());
        }

        return consulta;
    }
    
}
