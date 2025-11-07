package fatec.poo.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Nicolas Ap
 */
public class Paciente extends Pessoa {
    
    private LocalDate dataNascimento;
    private double altura;
    private double peso;
    private ArrayList<Consulta> consultas;

    public Paciente(String cpf, String nome, LocalDate dataNascimento) {
        super(cpf, nome);
        this.dataNascimento = dataNascimento;
        this.consultas = new ArrayList<>();
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDataNascimento() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatoData);
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }
    
    public double calcIMC(){
        return peso/Math.pow(altura, 2);
    }
    
    public int calcIdade(LocalDate data){
        return Period.between(dataNascimento, data).getYears();
    }
    
    public void addConsulta(Consulta consulta){
        consultas.add(consulta);
    }
}
