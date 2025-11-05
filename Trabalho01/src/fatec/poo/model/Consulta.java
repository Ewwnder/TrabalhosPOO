/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.model;

import java.util.ArrayList;

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
    private ArrayList<Exame> exames; 
    
    public Consulta(int codigo, String data) {
        this.codigo=codigo;
        this.data=data;
        this.exames = new ArrayList<Exame>();
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
    
    public void addExame(Exame exame){
        exames.add(exame);
        exame.setConsulta(this);
    }
    
    public double calcValorTotalPagar(){
        double valorExames = 0.0;
        for(Exame exame : exames){
            valorExames+=exame.getValor();
        }
        return (valorExames + valor);
    }
    
}
