package edu.pes.escola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TelaEdicaoCronograma extends Stage {
    private Stage telaAnterior;
    private Skin skin;

    private String[] dias = {"Seg", "Ter", "Qua", "Qui", "Sex"};
    private String[] horarios = {"07:10-08:00", "08:00-08:50", "08:50-09:40", "10:00-10:50", "10:50-11:40", "11:40-12:30"};

    private String[][] grade;
    private Table painelGrade;
    private HashMap<String, ArrayList<String>> disciplinasPorProfessor;

    public TelaEdicaoCronograma(Stage telaAnterior, Skin skin, ArrayList<String> listaTurmas, HashMap<String, ArrayList<String>> mapaProfessores) {
        this.telaAnterior = telaAnterior;
        this.skin = skin;

        grade = new String[horarios.length][dias.length];
        carregarGradeFixaInicial();
        carregarProfessores(mapaProfessores);

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(15);
        addActor(raiz);

        raiz.add(new Label("Cronograma Gerado", skin)).padBottom(10).row();

        final SelectBox<String> boxDia = new SelectBox<>(skin);
        boxDia.setItems(dias);

        final SelectBox<String> boxHorario = new SelectBox<>(skin);
        boxHorario.setItems(horarios);

        ArrayList<String> opcoesTurmas = new ArrayList<>();
        if (listaTurmas != null && !listaTurmas.isEmpty()) {
            opcoesTurmas.addAll(listaTurmas);
        } else {
            opcoesTurmas.add("Turma Exemplo");
        }
        final SelectBox<String> boxTurma = new SelectBox<>(skin);
        boxTurma.setItems(opcoesTurmas.toArray(new String[0]));

        final SelectBox<String> boxProfessor = new SelectBox<>(skin);
        ArrayList<String> nomesProf = new ArrayList<>(disciplinasPorProfessor.keySet());
        boxProfessor.setItems(nomesProf.toArray(new String[0]));

        final SelectBox<String> boxDisciplina = new SelectBox<>(skin);
        atualizarDisciplinasDoProfessor(boxProfessor.getSelected(), boxDisciplina);

        boxProfessor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                atualizarDisciplinasDoProfessor(boxProfessor.getSelected(), boxDisciplina);
            }
        });

        Table linha1 = new Table();
        linha1.add(new Label("Dia:", skin)).padRight(4);
        linha1.add(boxDia).padRight(10);
        linha1.add(new Label("Horario:", skin)).padRight(4);
        linha1.add(boxHorario).padRight(10);
        linha1.add(new Label("Turma:", skin)).padRight(4);
        linha1.add(boxTurma).width(150);
        raiz.add(linha1).padBottom(6).row();

        Table linha2 = new Table();
        linha2.add(new Label("Prof:", skin)).padRight(4);
        linha2.add(boxProfessor).width(150).padRight(10);
        linha2.add(new Label("Disc:", skin)).padRight(4);
        linha2.add(boxDisciplina).width(150);
        raiz.add(linha2).padBottom(8).row();

        final Label lblAviso = new Label("", skin);
        raiz.add(lblAviso).padBottom(8).row();

        painelGrade = new Table();
        atualizarGrade();
        raiz.add(painelGrade).center().padBottom(12).row();

        TextButton btnAplicar = new TextButton("Aplicar Alteracao", skin);
        btnAplicar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int dIdx = boxDia.getSelectedIndex();
                int hIdx = boxHorario.getSelectedIndex();
                String prof = boxProfessor.getSelected();
                String disc = boxDisciplina.getSelected();

                grade[hIdx][dIdx] = disc + " (" + prof + ")";
                lblAviso.setText("Alteracao aplicada com sucesso.");
                atualizarGrade();
            }
        });

        TextButton btnVoltar = new TextButton("Voltar", skin);
        btnVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (telaAnterior != null) {
                    Gdx.input.setInputProcessor(telaAnterior);
                }
            }
        });

        Table botoes = new Table();
        botoes.add(btnAplicar).padRight(12);
        botoes.add(btnVoltar);
        raiz.add(botoes);
    }

    private void carregarProfessores(HashMap<String, ArrayList<String>> mapaProfessores) {
		//aqui so vai gerar um cronograma padrao todas as vezes
		//pra demonstrar como vai ser mais ou menos
        disciplinasPorProfessor = new HashMap<>();

        ArrayList<String> discFulano = new ArrayList<>();
		//Professores cadastrados com 2 disciplinas, so pra ver
		//se as SelectBoxes tao dando certo
        discFulano.add("Matematica");
        discFulano.add("Fisica");
        disciplinasPorProfessor.put("Prof. Fulano", discFulano);

        ArrayList<String> discCiclano = new ArrayList<>();
        discCiclano.add("Portugues");
        discCiclano.add("Historia");
        disciplinasPorProfessor.put("Prof. Ciclano", discCiclano);

        ArrayList<String> discBeltrano = new ArrayList<>();
        discBeltrano.add("Fisica");
        discBeltrano.add("Quimica");
        disciplinasPorProfessor.put("Prof. Beltrano", discBeltrano);

        if (mapaProfessores != null) {
            for (String nome : mapaProfessores.keySet()) {
                disciplinasPorProfessor.put(nome, mapaProfessores.get(nome));
            }
        }
    }

    private void atualizarDisciplinasDoProfessor(String nomeProf, SelectBox<String> boxDisciplina) {
        ArrayList<String> lista = disciplinasPorProfessor.get(nomeProf);
        if (lista == null || lista.isEmpty()) {
            boxDisciplina.setItems("Sem disciplina");
            return;
        }
        boxDisciplina.setItems(lista.toArray(new String[0]));
    }

    private void carregarGradeFixaInicial() {
        for (int h = 0; h < horarios.length; h++) {
            for (int d = 0; d < dias.length; d++) {
                grade[h][d] = "-";
            }
        }
        grade[0][0] = "Matematica (Prof. Fulano)";
        grade[1][1] = "Portugues (Prof. Ciclano)";
        grade[2][3] = "Fisica (Prof. Beltrano)";
    }

    private void atualizarGrade() {
        painelGrade.clear();

        painelGrade.add(new Label("Horario", skin)).padRight(8).center();
        for (String dia : dias) {
            painelGrade.add(new Label(dia, skin)).padRight(8).center();
        }
        painelGrade.row();

        for (int h = 0; h < horarios.length; h++) {
            painelGrade.add(new Label(horarios[h], skin)).padRight(8).center();
            for (int d = 0; d < dias.length; d++) {
                painelGrade.add(new Label(grade[h][d], skin)).padRight(8).center();
            }
            painelGrade.row();
        }
    }
}
