
package fatec.poo.model;

/**
 *
 * @author erica
 */
public class Medico extends Pessoa{
    private String crm;
    private String especialidade;

    public Medico(String crm, String especialidade, String cpf, String nome) {
        super(cpf, nome);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    
    
     
}
