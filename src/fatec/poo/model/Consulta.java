/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.model;

/**
 *
 * @author LucasMorais
 */
public class Consulta {
    private int codigo;
    private String data;
    private double valor;
    //private Medico medico; 
    //private Medicacao medicacoes; arraylist
    //private Exame exames; arraylist
    public Consulta(int codigo, String data) {
        this.codigo=codigo;
        this.data=data;
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

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /*public double calcValorTotalPagar(){
        double valorExames = 0.0;
        for(Exame exame : exames){
            valorExames+=exame.getValor();
        }
        return (valorExames + valor);
    }*/
}
