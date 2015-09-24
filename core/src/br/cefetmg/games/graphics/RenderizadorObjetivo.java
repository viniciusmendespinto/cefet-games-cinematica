package br.cefetmg.games.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class RenderizadorObjetivo {
    
    private final ShapeRenderer shapeRenderer;
    private final Camera camera;
    
    public RenderizadorObjetivo(Camera camera) {
        shapeRenderer = new ShapeRenderer();
        this.camera = camera;
    }
    public void desenha(Vector3 posicao) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.translate(posicao.x, posicao.y, 0);
        shapeRenderer.rotate(0, 0, 1, 45);
        shapeRenderer.rect(-3, -3, 6, 6);
        shapeRenderer.identity();
        shapeRenderer.end();
    }
}
