
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
    
    public static boolean validarCPF(String cpf){
        
        //Fazendo o que pede no arquivo: O cpf deve ser inserido na base de dados sem a formatação da entrada de dados
        cpf = cpf.replaceAll("\\D", "");
        
        //Fazendo uma verificação para verificar entrar um CPF fora do tamanho padrão
        if (cpf.length()!=11){
            return false;
        }
        
        //Realizando a última condição, que foi proposto verificar se todos os números são iguais, por exemplo: 111.111.111-11
        if (cpf.matches("(\\d)\\1{10}")){
            return false;
        }
        
        //Lógica para pegar o primeiro digito
        
        int soma=0;
        
        for (int i=0;i<9;i++){
            int numero = Integer.parseInt(cpf.substring(i, i+1));
            soma+=numero*(i+1);
        }
        
        int resto = soma%11;
        int primeiroDigito = (resto==10) ? 0 : resto;
        
        //Lógica para pegar o segundo digito
        
        soma = 0;
        int regra = 11;
        
        for (int i=0;i<9;i++){
            int numero = Integer.parseInt(cpf.substring(i, i+1));
            soma+=numero*(regra-=1);
        }
        
        soma+=primeiroDigito*(regra-=1);
        
        resto = (soma*10)%11;
        int segundoDigito = (resto==10) ? 0 : resto;
        
        boolean verificaPrimeiroDigito = primeiroDigito == Integer.parseInt(cpf.substring(9, 10));
        boolean verificaSegundoDigito = segundoDigito == Integer.parseInt(cpf.substring(10, 11));
        
        return verificaPrimeiroDigito && verificaSegundoDigito;
    }
}

