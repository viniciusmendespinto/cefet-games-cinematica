package br.cefetmg.games;

import br.cefetmg.games.movement.Agente;
import br.cefetmg.games.movement.Alvo;
import br.cefetmg.games.graphics.RenderizadorAgente;
import br.cefetmg.games.graphics.RenderizadorObjetivo;
import br.cefetmg.games.movement.behavior.*;
import br.cefetmg.games.physics.Colisoes;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.Iterator;

public class Cinematica extends ApplicationAdapter {

    private Viewport viewport;
    private Camera camera;
    private ArrayList<Agente> agentes;
    private RenderizadorAgente renderizador;
    private RenderizadorObjetivo renderizadorObjetivo;

    private Alvo objetivo;
    private Perseguir perseguir;
    private Vagar vagar;
    private Fugir fugir;
    private Algoritmo algoritmoCorrente;

    public Vector3 posicaoAleatoria() {
        Vector3 posicao = new Vector3();
        posicao.x = camera.viewportWidth * (float) (Math.random() - 0.5f);
        posicao.y = camera.viewportHeight * (float) (Math.random() - 0.5f);
        return posicao;
    }

    public Agente novoAgente(Vector3 posicao) {
        Agente agente = new Agente(posicao,
                new Color(
                        (float) Math.random(),
                        (float) Math.random(),
                        (float) Math.random(), 1));
        agente.pose.orientacao = (float) (Math.random() * Math.PI * 2);
        agente.defineComportamento(algoritmoCorrente);

        agentes.add(agente);
        return agente;
    }

    @Override
    public void create() {
        // configura a janela e elementos gráficos
        camera = new OrthographicCamera(
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        viewport = new ScreenViewport(camera);
        renderizador = new RenderizadorAgente(camera);
        renderizadorObjetivo = new RenderizadorObjetivo(camera);

        // define o objetivo (perseguição, fuga) inicialmente no centro do mundo 
        objetivo = new Alvo(new Vector3(0, 0, 0));

        // comportamentos disponíveis
        perseguir = new Perseguir(40);
        perseguir.alvo = objetivo;
        vagar = new Vagar(40, 2);
        fugir = new Fugir(40);
        fugir.alvo = perseguir.alvo;
        algoritmoCorrente = vagar;

        agentes = new ArrayList<Agente>();
        Agente agente = novoAgente(posicaoAleatoria());
        agente.defineComportamento(perseguir);
        novoAgente(posicaoAleatoria());
        novoAgente(posicaoAleatoria());
        novoAgente(posicaoAleatoria());

        // escuta por eventos de interação
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                Vector3 clique = new Vector3(x, y, 0);
                viewport.unproject(clique);

                // Botão ESQUERDO: posiciona objetivo no mapa ou em um agente
                if (button == Buttons.LEFT) {
                    boolean definiuObjetivo = false;

                    // passo 1: verifica se o clique "acertou" um agente
                    Iterator<Agente> it = agentes.iterator();
                    Agente atual = null;
                    while (it.hasNext() && !definiuObjetivo) {
                        atual = it.next();
                        definiuObjetivo = Colisoes.colideCom(
                                new Circle(
                                        new Vector2(
                                                atual.pose.posicao.x,
                                                atual.pose.posicao.y),
                                        RenderizadorAgente.RAIO),
                                clique);
                    }
                    if (definiuObjetivo) {
                        objetivo.setObjetivo(atual);
                    } 
                    // passo 2: se não tiver acertado um agente, define o 
                    // objetivo no mapa                    
                    else {
                        objetivo.setObjetivo(clique);
                    }
                } // Botão DIREITO: novo agente
                else if (button == Buttons.RIGHT) {
                    novoAgente(clique);
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Keys.W:
                        algoritmoCorrente = vagar;
                        break;
                    case Keys.F:
                        algoritmoCorrente = fugir;
                        break;
                    case Keys.S:
                        algoritmoCorrente = perseguir;
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Atualiza o mundo virtual para ter as mesmas proporções que a janela.
     *
     * @param w Largura da janela.
     * @param h Altura da janela.
     */
    @Override
    public void resize(int w, int h) {
        viewport.update(w, h);
    }

    /**
     * Faz com que o mundo seja infinito. Quando um agente chega ao final do
     * mundo (1 dos 4 cantos), ele é teleportado para o outro lado.
     *
     * @param agente Agente
     */
    private void revolveCoordenadas(Agente agente) {
        float larguraMundo = camera.viewportWidth;
        float alturaMundo = camera.viewportHeight;
        float larguraMundo_2 = larguraMundo / 2.0f;
        float alturaMundo_2 = alturaMundo / 2.0f;

        if (agente.pose.posicao.x < -larguraMundo_2) {
            agente.pose.posicao.x += larguraMundo;
        } else if (agente.pose.posicao.x > larguraMundo_2) {
            agente.pose.posicao.x -= larguraMundo;
        }
        if (agente.pose.posicao.y < -alturaMundo_2) {
            agente.pose.posicao.y += alturaMundo;
        } else if (agente.pose.posicao.y > alturaMundo_2) {
            agente.pose.posicao.y -= alturaMundo;
        }
    }

    /**
     * Percorre a lista de agentes, atualiando sua lógica de movimentação.
     */
    private void atualizaAgentes() {

        // tempo desde a última atualização
        float delta = Gdx.graphics.getDeltaTime();

        // percorre a lista de agentes e os atualiza (agente.atualiza)
        Iterator<Agente> it = agentes.iterator();
        while (it.hasNext()) {
            Agente atual = it.next();
            // atualiza lógica
            atual.atualiza(delta);
            // contém os agentes dentro do mundo
            revolveCoordenadas(atual);
        }
    }

    /**
     * Percorre a lista de agentes, renderizando-os. Também invoca um método
     * (atualizaAgentes) para atualização da lógica de movimentação.
     */
    @Override
    public void render() {
        // limpa a tela
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // desenha os agentes
        Iterator<Agente> it = agentes.iterator();
        while (it.hasNext()) {
            renderizador.desenha(it.next());
        }

        // desenha o objetivo
        renderizadorObjetivo.desenha(objetivo);

        // atualiza a lógica de movimento dos agentes
        atualizaAgentes();

        renderizadorObjetivo.update(Gdx.graphics.getDeltaTime());
    }
}
