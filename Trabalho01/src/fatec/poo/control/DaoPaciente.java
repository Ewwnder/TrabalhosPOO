package fatec.poo.control;

import fatec.poo.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Nicolas Ap
 */
public class DaoPaciente {
    
    private Connection conn;

    public DaoPaciente(Connection conn) {
        this.conn = conn;
    }
    
    public void inserir(Paciente paciente){
        PreparedStatement ps = null;

    }
}
