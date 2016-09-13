package br.cefetmg.games.graphics;

import br.cefetmg.games.movement.Agente;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import java.util.HashMap;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class RenderizadorAgente {

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Camera camera;

    private final HashMap<Agente, GlyphLayout> cacheNomes
            = new HashMap<Agente, GlyphLayout>();

    public static final float RAIO = 10;
    
    public RenderizadorAgente(Camera camera) {
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        batch = new SpriteBatch();
        this.camera = camera;
    }

    public void desenha(Agente agente) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(agente.cor);
        shapeRenderer.translate(
                agente.pose.posicao.x,
                agente.pose.posicao.y, 0);
        shapeRenderer.rotate(0, 0, 1,
                agente.pose.orientacao * ((float) (180.0f / Math.PI)));
        shapeRenderer.circle(0, 0, RAIO);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.triangle(10, 2, 16, 0, 10, -2);
        shapeRenderer.identity();
        shapeRenderer.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setTransformMatrix(new Matrix4()
                .setToTranslation(agente.pose.posicao));
        GlyphLayout layout = cacheNomes.get(agente);
        if (layout == null) {
            layout = new GlyphLayout(font, "" + agente.getBehaviorName());
            cacheNomes.put(agente, layout);
        }
        font.draw(batch, layout, -layout.width / 4.0f, layout.height / 2.0f);
        batch.end();
    }

}
