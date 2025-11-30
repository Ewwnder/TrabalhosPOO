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
        private Medico medico; 
        private ArrayList<Medicacao> medicacoes;
        private ArrayList<Exame> exames; 

        public Consulta(int codigo, String data) {
            this.codigo=codigo;
            this.data=data;
            this.exames = new ArrayList<>();
            this.medicacoes = new ArrayList<>();
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

        public void addMedicacao(Medicacao medicacao){
            medicacoes.add(medicacao);
        }

        public double calcValorTotalPagar(){
            double valorExames = 0.0;
            for(Exame exame : exames){
                valorExames+=exame.getValor();
            }
            return (valorExames + valor);
        }

        public void setMedico(Medico medico){
            this.medico = medico;
        }

        public Medico getMedico(){
            return medico;
        }

        public ArrayList<Exame> getExames() {
            return exames;
        }

        public ArrayList<Medicacao> getMedicacoes() {
            return medicacoes;
        }

    }
