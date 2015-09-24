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

        // Input: pose atual
        // ..
        // ..
        // ..
        // ..
        // Output: (a) uma velocidade angular no sentido horário ou anti 
        //             (output.rotacao)
        //         (b) o vetor velocidade com tamanho maxVelocidade, apontando 
        //             na direção da orientação do agente (agente.orientacaoVetor())
        
        return output;
    }

}
