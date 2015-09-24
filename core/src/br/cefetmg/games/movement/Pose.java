package br.cefetmg.games.movement;

import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Flávio Coutinho
 */
public class Pose {

    public Vector3 posicao;
    public float orientacao;

    public Pose() {
        this(new Vector3(0, 0, 0), 0);
    }

    public Pose(Vector3 position) {
        this(position, 0);
    }

    public Pose(Vector3 posicao, float orientacao) {
        this.posicao = posicao;
        this.orientacao = orientacao;
    }

    public Vector3 orientacaoVetor() {
        return new Vector3((float) Math.cos(orientacao), (float) Math.sin(orientacao), 0);
    }

    public void olharParaVelocidade(Vector3 velocidade) {
        if (velocidade.len2() > 0) {
            orientacao = (float) Math.atan2(velocidade.y, velocidade.x);
        }
    }

    public void integra(Direcionamento guia, float delta) {
        posicao.x += guia.velocidade.x * delta;
        posicao.y += guia.velocidade.y * delta;
        posicao.z += guia.velocidade.z * delta;
        orientacao += guia.rotacao * delta;
        orientacao = orientacao % ((float) Math.PI * 2);
    }
}
