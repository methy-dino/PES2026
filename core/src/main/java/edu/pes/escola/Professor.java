package edu.pes.escola;
import java.util.ArrayList;
public class Professor {
    private ArrayList<ArrayList<HorarioSemana>> horariosProfessor;
    public Professor() {
        horariosProfessor = new ArrayList<ArrayList<HorarioSemana>>(7);
    }
    public Professor(ArrayList<ArrayList<HorarioSemana>> lista) {
        horariosProfessor = lista;
    }
    public void adicionarHorario(HorarioSemana hora) {
        /* deve juntar horários imediatamente sequentes */
        int dia = hora.GetDia();
    }
}
