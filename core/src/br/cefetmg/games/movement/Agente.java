package br.cefetmg.games.movement;

import br.cefetmg.games.movement.behavior.Algoritmo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class Agente {

    public Pose pose;
    private Algoritmo comportamento;

    public Color cor;

    public Agente(Vector3 posicao, Color cor) {
        this.pose = new Pose(posicao, 0);
        this.cor = cor;
    }

    public void atualiza(float delta) {
        if (comportamento != null) {
            // pergunta ao algoritmo de movimento (comportamento) 
            // para onde devemos ir
            Direcionamento direcionamento = comportamento.guiar(this.pose);

            // faz a simulação física usando novo estado da entidade cinemática
            pose.integra(direcionamento, delta);

            // força o agente a olhar na direção em que está andandando
            if (!comportamento.protegeOrientacao) {
                pose.olharNaDirecaoDaVelocidade(direcionamento.velocidade);
            }
        }
    }

    /**
     * @param comportamento the behavior to set
     */
    public void defineComportamento(Algoritmo comportamento) {
        this.comportamento = comportamento;
    }

    public Algoritmo getBehavior() {
        return comportamento;
    }
    
    public char getBehaviorName() {
        return comportamento != null ? comportamento.nome : '-';
    }
}
