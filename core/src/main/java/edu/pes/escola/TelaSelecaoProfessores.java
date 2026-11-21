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

public class TelaSelecaoProfessores extends Stage {
    private Stage telaAnterior;
    private Skin skin;
    private ArrayList<Professor> listaProf;
    private Table painelProf;

    public TelaSelecaoProfessores(Stage telaAnterior, Skin skin, ArrayList<Professor> listaProf) {
        this.telaAnterior = telaAnterior;
        this.skin = skin;
        this.listaProf = listaProf;

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(20);
        addActor(raiz);

        raiz.add(new Label("Professores Cadastrados", skin)).left().padBottom(10).row();

        painelProf = new Table();
        atualizarLista();
        raiz.add(painelProf).left().padBottom(15).row();

        TextButton btnNovo = new TextButton("Novo Professor", skin);
        btnNovo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(new TelaEdicaoProfessor(TelaSelecaoProfessores.this, skin, listaProf));
            }
        });

        TextButton btnVoltar = new TextButton("Voltar", skin);
        btnVoltar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (telaAnterior != null) Gdx.input.setInputProcessor(telaAnterior);
            }
        });

        raiz.add(btnNovo).padRight(10).left();
        raiz.add(btnVoltar).left();
    }

    public void atualizarLista() {
        painelProf.clear();
        if (listaProf == null || listaProf.isEmpty()) {
            painelProf.add(new Label("Nenhum professor cadastrado ainda.", skin)).left();
            return;
        }
        for (int i = 0; i < listaProf.size(); i++) {
            final int idx = i;
            TextButton btnRemover = new TextButton("Remover", skin);
            btnRemover.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    listaProf.remove(idx);
                    atualizarLista();
                }
            });
            painelProf.add(new Label("Professor " + (i + 1), skin)).padRight(10).left();
            painelProf.add(btnRemover).left().row();
        }
    }
}
