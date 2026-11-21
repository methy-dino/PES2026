package edu.pes.escola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

public class TelaSelecaoTurma extends Stage {
    private Stage telaAnterior;
    private Skin skin;
    private ArrayList<String> listaTurmas;
    private Table painelTurmas;

    public TelaSelecaoTurma(Stage telaAnterior, Skin skin, ArrayList<String> listaTurmas) {
        this.telaAnterior = telaAnterior;
        this.skin = skin;
        this.listaTurmas = listaTurmas;

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(20);
        addActor(raiz);

        raiz.add(new Label("Gerenciamento de Turmas", skin)).left().padBottom(10).row();

        painelTurmas = new Table();
        atualizarLista();
        raiz.add(painelTurmas).left().padBottom(15).row();

        TextButton btnNova = new TextButton("Nova Turma", skin);
        btnNova.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(new TelaEdicaoTurma(TelaSelecaoTurma.this, skin, listaTurmas));
            }
        });

        TextButton btnVoltar = new TextButton("Voltar", skin);
        btnVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (telaAnterior != null) Gdx.input.setInputProcessor(telaAnterior);
            }
        });

        raiz.add(btnNova).padRight(10).left();
        raiz.add(btnVoltar).left();
    }

    public void atualizarLista() {
        painelTurmas.clear();
        if (listaTurmas == null || listaTurmas.isEmpty()) {
            painelTurmas.add(new Label("Nenhuma turma cadastrada ainda", skin)).left();
            return;
        }
        for (int i = 0; i < listaTurmas.size(); i++) {
			final int indice = i;
            TextButton btnRemover = new TextButton("Remover", skin);
            btnRemover.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    listaTurmas.remove(indice);
                    atualizarLista();
                }
            });
            painelTurmas.add(new Label(listaTurmas.get(i), skin)).padRight(10).left();
            painelTurmas.add(btnRemover).left().row();
        }
    }
}
