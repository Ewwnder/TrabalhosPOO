
package fatec.poo.model;

import java.util.ArrayList;

/**
 *
 * @author erica
 */
public class Medico extends Pessoa{
    private String crm;
    private String especialidade;
    private ArrayList<Consulta> consultas;

    public Medico(String cpf, String nome, String crm, String especialidade) {
        super(cpf, nome);
        this.crm = crm;
        this.especialidade = especialidade;
        this.consultas = new ArrayList<>();
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    
    public void addConsulta(Consulta c) {
        consultas.add(c);
        c.setMedico(this);
    }  
}
