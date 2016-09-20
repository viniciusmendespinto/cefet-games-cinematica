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
        
        output.rotacao = randomBinomial()*maxAngular;

        output.velocidade = agente.getOrientacaoComoVetor(); 
        output.velocidade.nor().scl(maxVelocidade);
              
        return output;
    }

}
