
package fatec.poo.model;

/**
 *
 * @author erica
 */
public class Consulta {
    private int codigo;
    private String data;
    private double valor;

    public Consulta(int codigo, String data, double valor) {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    public double calcValorPagar(){
        return setValor() * 
    }
}
