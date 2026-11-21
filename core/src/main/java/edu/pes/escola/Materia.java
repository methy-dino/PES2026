package edu.pes.escola;
import java.lang.Integer;
/* acho que tá completa */
public class Materia {
    private String nome;
    /* O conjunto é [minutoInicial, minutoFinal].*/
    private int horaAula;
    public Materia(String nomeMateria, int quantidadeAulas){
        nome = nomeMateria;
        horaAula = quantidadeAulas;
    }
    String StringMateria() {
        return nome + " (" + horaAula + " Horas aula)";
    }
    public void SetNome(String novoNome) {
        nome = novoNome;
    }
    public void SetHoraAula(int novaHora) {
        horaAula = novaHora;
    }
    public String GetNome(String novoNome) {
        return nome;
    }
    public int GetHoraAula() {
        return horaAula;
    }
}
