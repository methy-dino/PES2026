package edu.pes.escola;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/* acho que ta pronto*/
public class Professor {
    private ArrayList<ArrayList<HorarioSemana>> horariosProfessor;
    public Professor() {
        horariosProfessor = new ArrayList<ArrayList<HorarioSemana>>(7);
    }
    public void adicionarHorario(HorarioSemana hora) {
        /* deve juntar horários imediatamente sequentes */
        int dia = hora.GetDia();
        ArrayList<HorarioSemana> horariosDia = horariosProfessor.get(dia);
        int comecoNovo = hora.GetInicio();
        int fimNovo = hora.GetFim();
        for (int i = 0; i < horariosDia.size(); i++){
            /* se o começo do novo horário é menor ou igual ao final +1 ou o final do novo é maior ou igual ao começo -1. */
            int comecoAntigo = horariosDia.get(i).GetInicio();
            int fimAntigo = horariosDia.get(i).GetFim();
            if (comecoNovo <= fimAntigo+1 || fimNovo >= comecoAntigo-1) {
                /* modificar horário antigo */
                horariosDia.get(i).SetInicio(Math.min(comecoNovo, comecoAntigo));
                horariosDia.get(i).SetFim(Math.min(fimNovo, fimAntigo));
            }
        }
        horariosDia.add(hora);
        Comparator<HorarioSemana> a = new sortHora();
        Collections.sort(horariosDia, a);

    }
}
class sortHora implements Comparator<HorarioSemana> {
    public int compare(HorarioSemana left, HorarioSemana right) {
        return left.GetInicio() - right.GetInicio();
    }
}
