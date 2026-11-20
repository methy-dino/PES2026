package edu.pes.escola;

import com.badlogic.gdx.ApplicationAdapter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Skin skinAtual;
    private Stage telaAtual;
    @Override
    public void create(){
            skinAtual = new Skin(Gdx.files.internal("ui/uiskin.json"));
    }
    @Override
    public void render() {
        ScreenUtils.clear(0f,0f,0f,1f);
        //telaAtual.act(Gdx.graphics.getDeltaTime());
        //telaAtual.render();
    }
    @Override
    public void resize(int width, int height){
    }
    @Override
    public void dispose() {
    }
}
