package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class Vagar extends Algoritmo {

    private static final char NOME = 'w';
    private float maxAngular = 30f;

    public Vagar() {
        super(NOME, true);
    }

    public Vagar(float tangencial, float angular) {
        super(NOME, true);
        maxVelocidade = tangencial;
        maxAngular = angular;
    }

    private double randomBinomial() {
        return Math.random() - Math.random();
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();

        output.velocidade = agente.orientacaoVetor().scl(maxVelocidade);
        output.rotacao = randomBinomial() * maxAngular;

        return output;
    }

}
