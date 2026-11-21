package edu.pes.escola;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/* acho que tá completa */
public class Turma {
    private ArrayList<ArrayList<HorarioSemana>> horariosAula;
    int duracaoHoraAula;
    private ArrayList<Materia> curriculoTurma;
    public Turma() {
        horariosAula = new ArrayList<ArrayList<HorarioSemana>>(7);
        curriculoTurma = new ArrayList<Materia>(8);
    }
    public void adicionarHorario(HorarioSemana hora) {
        /* deve juntar horários imediatamente sequentes */
        if ((hora.GetFim() - hora.GetInicio() % duracaoHoraAula) != 0){
            /* erro */
        }
        int dia = hora.GetDia();
        ArrayList<HorarioSemana> horariosDia = horariosAula.get(dia);
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
