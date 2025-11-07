
package fatec.poo.model;

/**
 *
 * @author erica
 */
public class Pessoa {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    
    public Pessoa(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }
 
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
 
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
 
    public String getCpf() {
        return cpf;
    }
 
    public String getNome() {
        return nome;
    }
 
    public String getEndereco() {
        return endereco;
    }
 
    public String getTelefone() {
        return telefone;
    }  
}

