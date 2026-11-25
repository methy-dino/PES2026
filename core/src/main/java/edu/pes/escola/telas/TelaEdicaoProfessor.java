package edu.pes.escola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TelaEdicaoProfessor extends Stage {
    private Stage telaAnterior;

    public TelaEdicaoProfessor(Stage telaAnterior, Skin skin, ArrayList<Professor> listaProf, HashMap<String, ArrayList<String>> mapaProfessores) {
        this.telaAnterior = telaAnterior;

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(20);
        addActor(raiz);

        raiz.add(new Label("Novo Professor", skin)).left().padBottom(8).row();

        final TextField campoNome = new TextField("", skin);
        campoNome.setMessageText("Nome");
        raiz.add(campoNome).width(240).padBottom(8).left().row();

        String[] disciplinas = {"Matemática", "Português", "Física", "Química", "História", "Geografia"};
        final ArrayList<CheckBox> checksDisc = new ArrayList<>();
        raiz.add(new Label("Disciplinas", skin)).left().row();
        for (String d : disciplinas) {
            CheckBox cb = new CheckBox(d, skin);
            checksDisc.add(cb);
            raiz.add(cb).left().row();
        }

        String[] dias = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};
        final ArrayList<CheckBox> checksDias = new ArrayList<>();
        raiz.add(new Label("Disponibilidade Semanal", skin)).left().padTop(8).row();
        for (String dia : dias) {
            CheckBox cb = new CheckBox(dia, skin);
            checksDias.add(cb);
            raiz.add(cb).left().row();
        }

        final Label lblMsg = new Label("", skin);
        raiz.add(lblMsg).padTop(8).left().row();

        TextButton btnAdd = new TextButton("Adicionar Professor", skin);
        btnAdd.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String nome = campoNome.getText().trim();
                if (nome.isEmpty()) {
                    lblMsg.setText("Informe o nome do professor.");
                    return;
                }
                Professor p = new Professor();
                for (int i = 0; i < checksDias.size(); i++) {
                    if (checksDias.get(i).isChecked()) {
                        try {
                            p.adicionarHorario(new HorarioSemana(i + 1, 420, 720));
                        } catch (Exception ignored) {
                        }
                    }
                }
                ArrayList<String> discEscolhidas = new ArrayList<>();
                for (CheckBox cb : checksDisc) {
                    if (cb.isChecked()) {
                        discEscolhidas.add(cb.getText().toString());
                    }
                }
                if (listaProf != null) listaProf.add(p);
                if (mapaProfessores != null) mapaProfessores.put(nome, discEscolhidas);

                if (telaAnterior instanceof TelaSelecaoProfessores) {
                    ((TelaSelecaoProfessores) telaAnterior).atualizarLista();
                }
                if (telaAnterior != null) Gdx.input.setInputProcessor(telaAnterior);
            }
        });

        TextButton btnVoltar = new TextButton("Voltar", skin);
        btnVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (telaAnterior != null) Gdx.input.setInputProcessor(telaAnterior);
            }
        });

        raiz.add(btnAdd).padRight(10).padTop(10).left();
        raiz.add(btnVoltar).padTop(10).left();
    }
}
