package edu.pes.escola;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends ApplicationAdapter {
    private Skin skin;
    private Stage telaAtual;
    private Stage menu;

    private ArrayList<String> turmas = new ArrayList<>();
    private ArrayList<Professor> professores = new ArrayList<>();
    private HashMap<String, ArrayList<String>> mapaProf = new HashMap<>();
    private FileHandle profFile;
    private FileHandle turmasFile;
    private FileHandle mapeamentoFile;
    @Override
    public void create() {
        profFile = Gdx.files.local("./professores.json");
        turmasFile = Gdx.files.local("./turmas.json");
        mapeamentoFile = Gdx.files.local("./mapeamento.json");
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        menu = criarMenu();
        telaAtual = menu;
        Gdx.input.setInputProcessor(telaAtual);
        salvar();
    }

    private Stage criarMenu() {
        Stage s = new Stage();
        Table t = new Table();
        t.setFillParent(true);
        s.addActor(t);

        TextButton bTurmas = new TextButton("Turmas", skin);
        bTurmas.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                salvar();
                Gdx.input.setInputProcessor(new TelaSelecaoTurma(menu, skin, turmas));
            }
        });

        TextButton bProf = new TextButton("Professores", skin);
        bProf.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
        salvar();
                Gdx.input.setInputProcessor(new TelaSelecaoProfessores(menu, skin, professores, mapaProf));
            }
        });

        TextButton bCrono = new TextButton("Cronograma", skin);
        bCrono.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
        salvar();
                Gdx.input.setInputProcessor(new TelaEdicaoCronograma(menu, skin, turmas, mapaProf));
            }
        });

        t.add(bTurmas).width(200).padBottom(10).row();
        t.add(bProf).width(200).padBottom(10).row();
        t.add(bCrono).width(200);
        return s;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.18f, 1f);
        InputProcessor p = Gdx.input.getInputProcessor();
        if (p instanceof Stage && p != telaAtual) {
            telaAtual = (Stage) p;
        }
        telaAtual.act(Gdx.graphics.getDeltaTime());
        telaAtual.draw();
    }

    @Override
    public void resize(int width, int height) {
        telaAtual.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        skin.dispose();
        menu.dispose();
        if (telaAtual != menu) {
            telaAtual.dispose();
        }
    }
    public void salvar(){
        //System.out.println("LEN " + professores.size());
        Json json = new Json();
        profFile.writeString(json.prettyPrint(professores), false);
        //profFile.writeString("BLAAAAAAAAAAAAA", false);
        mapeamentoFile.writeString(json.prettyPrint(mapaProf), false);
        turmasFile.writeString(json.prettyPrint(turmas), false);
    }

    public void carregar(){
        Json json = new Json();
        //professores = json.fromJson(ArrayList.class, file);
    }
}
