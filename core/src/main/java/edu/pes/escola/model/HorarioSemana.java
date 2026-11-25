package edu.pes.escola;
import java.lang.Integer;
/* acho que tá completa */
public class HorarioSemana {
    private static String nomeDias[] = {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};
    private int indiceDia;
    /* O conjunto é [minutoInicial, minutoFinal].*/
    private int minutoInicial;
    private int minutoFinal;
    public HorarioSemana(int dia_sem, int minInicial, int minFinal){
        indiceDia = dia_sem;
        minutoInicial = minInicial;
        minutoFinal = minFinal;
    }
    String StringIntervalo() {
        String retorno = "";
        retorno += Integer.toString(minutoInicial/60).toString();
        retorno += Integer.toString(minutoInicial%60).toString();
        retorno += " - ";
        retorno += Integer.toString(minutoFinal/60).toString();
        retorno += Integer.toString(minutoFinal%60).toString();
        return retorno;
    }
    public void SetInicio(int novoInicio) {
        minutoInicial = novoInicio;
    }
    public void SetFim(int novoFim) {
        minutoFinal = novoFim;
    }
    public int GetFim() {
        return minutoFinal;
    }
    public int GetInicio() {
        return minutoInicial;
    }
    public String GetNomeDia(){
        return nomeDias[indiceDia];
    }
    public int GetDia(){
        return indiceDia;
    }
}
