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
import java.util.HashMap;

public class TelaSelecaoProfessores extends Stage {
    private Stage telaAnterior;
    private Skin skin;
    private ArrayList<Professor> listaProf;
    private HashMap<String, ArrayList<String>> mapaProfessores;
    private Table painelProf;

    public TelaSelecaoProfessores(Stage telaAnterior, Skin skin, ArrayList<Professor> listaProf, HashMap<String, ArrayList<String>> mapaProfessores) {
        this.telaAnterior = telaAnterior;
        this.skin = skin;
        this.listaProf = listaProf;
        this.mapaProfessores = mapaProfessores;

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
                Gdx.input.setInputProcessor(new TelaEdicaoProfessor(TelaSelecaoProfessores.this, skin, listaProf, mapaProfessores));
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
        if (mapaProfessores == null || mapaProfessores.isEmpty()) {
            painelProf.add(new Label("Nenhum professor cadastrado ainda.", skin)).left();
            return;
        }
        ArrayList<String> nomes = new ArrayList<>(mapaProfessores.keySet());
        for (int i = 0; i < nomes.size(); i++) {
            final String nome = nomes.get(i);
            final int idx = i;
            TextButton btnRemover = new TextButton("Remover", skin);
            btnRemover.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    mapaProfessores.remove(nome);
                    if (listaProf != null && idx < listaProf.size()) {
                        listaProf.remove(idx);
                    }
                    atualizarLista();
                }
            });
            painelProf.add(new Label(nome + " (" + mapaProfessores.get(nome).size() + " disc.)", skin)).padRight(10).left();
            painelProf.add(btnRemover).left().row();
        }
    }
}
