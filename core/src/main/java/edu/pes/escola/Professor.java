package edu.pes.escola;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/* acho que ta pronto*/
public class Professor {
    private ArrayList<ArrayList<HorarioSemana>> horariosProfessor;
    private ArrayList<Materia> materiasLencionaveis;
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
                    fimNovo = Math.max(fimNovo, fimAntigo);
                    comecoNovo = Math.min(comecoNovo, comecoAntigo);
                    while (fimNovo >= comecoAntigo-1){
                        // remover elemento atual
                        fimNovo = Math.max(fimNovo, horariosDia.get(i).GetFim());
                        comecoNovo = Math.min(comecoNovo, horariosDia.get(i).GetInicio());
                        comecoAntigo = horariosDia.get(i).GetInicio();
                    }
                    comecoAntigo = horariosDia.get(i).GetInicio();
                    fimAntigo = horariosDia.get(i).GetFim();
                    horariosDia.get(i).SetInicio(comecoNovo);
                    horariosDia.get(i).SetFim(fimNovo);
                    return;
            }
            horariosDia.add(hora);
            Comparator<HorarioSemana> a = new sortHora();
            Collections.sort(horariosDia, a);
        }
    }
}
class sortHora implements Comparator<HorarioSemana> {
    public int compare(HorarioSemana left, HorarioSemana right) {
        return left.GetInicio() - right.GetInicio();
    }
}
