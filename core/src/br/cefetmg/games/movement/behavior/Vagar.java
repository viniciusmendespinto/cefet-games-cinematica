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

    /**
     * Retorna um número aleatório entre [-1,+1] com chances maiores de estar
     * próximos ao centro (valor 0) do que nas extremidades (-1, 1) - tipo uma
     * curva normal com centro em 0.
     * @return um número aleatório.
     */
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
        // Output: (a) uma velocidade angular no sentido horário ou anti* 
        //             (output.rotacao), escalado pela máxima velocidade angular
        //             (maxAngular)
        //         (b) o vetor velocidade com tamanho maxVelocidade, apontando 
        //             na direção da orientação do agente (agente.orientacaoVetor())
        //
        // *: a função randomBinomial retorna um número aleatório próximo que
        //    pode ir de [-1,+1], mas com alta concentração de números próximos
        //    de 0 (tipo uma curva normal com o centro em 0).
        //    Ela pode ser útil para se encontrar uma velocidade angular 
        //    (output.angular).
        
        return output;
    }

}
