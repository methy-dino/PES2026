package edu.pes.escola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

public class TelaEdicaoTurma extends Stage {
    private Stage telaAnterior;

    public TelaEdicaoTurma(Stage telaAnterior, Skin skin, ArrayList<String> listaTurmas) {
        this.telaAnterior = telaAnterior;

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(20);
        addActor(raiz);

        raiz.add(new Label("Nova Turma", skin)).left().row();

        final TextField campoNome = new TextField("", skin);
        campoNome.setMessageText("Turma");

        final SelectBox<String> boxSerie = new SelectBox<>(skin);
        boxSerie.setItems("Selecione a série", "6° Ano", "7° Ano", "8° Ano", "9° Ano", "1º EM", "2º EM", "3º EM");

        final SelectBox<String> boxTurno = new SelectBox<>(skin);
        boxTurno.setItems("Manhã", "Tarde");

        String[] nomesDisciplinas = {"Matemática", "Português", "Física", "Química", "História", "Geografia"};
        final ArrayList<CheckBox> checks = new ArrayList<>();

        raiz.add(new Label("Nome da Turma", skin)).left().row();
        raiz.add(campoNome).width(240).padBottom(8).left().row();

        raiz.add(new Label("Série/Ano", skin)).left().row();
        raiz.add(boxSerie).width(240).padBottom(8).left().row();

        raiz.add(new Label("Turno", skin)).left().row();
        raiz.add(boxTurno).width(240).padBottom(8).left().row();

        raiz.add(new Label("Disciplinas", skin)).left().padBottom(4).row();
        for (String d : nomesDisciplinas) {
            CheckBox cb = new CheckBox(d, skin);
            checks.add(cb);
            raiz.add(cb).left().row();
        }

        final Label lblMsg = new Label("", skin);
        raiz.add(lblMsg).padTop(8).left().row();

        TextButton btnAdicionar = new TextButton("Adicionar Turma", skin);
        btnAdicionar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String nome = campoNome.getText().trim();
                if (nome.isEmpty()) {
                    lblMsg.setText("Informe o nome da turma.");
                    return;
                }
                ArrayList<String> escolhidas = new ArrayList<>();
                for (CheckBox cb : checks) {
                    if (cb.isChecked()) escolhidas.add(cb.getText().toString());
                }
                String resumo = nome + " - " + boxSerie.getSelected() + " (" + boxTurno.getSelected() + ", " + escolhidas.size() + " disc.)";
                listaTurmas.add(resumo);
                voltar();
            }
        });

        TextButton btnVoltar = new TextButton("Voltar", skin);
        btnVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                voltar();
            }
        });

        Table botoes = new Table();
        botoes.add(btnAdicionar).padRight(10);
        botoes.add(btnVoltar);
        raiz.add(botoes).padTop(10).left();
    }

    private void voltar() {
        if (telaAnterior instanceof TelaSelecaoTurma) {
            ((TelaSelecaoTurma) telaAnterior).atualizarLista();
        }
        if (telaAnterior != null) {
            Gdx.input.setInputProcessor(telaAnterior);
        }
    }
}
