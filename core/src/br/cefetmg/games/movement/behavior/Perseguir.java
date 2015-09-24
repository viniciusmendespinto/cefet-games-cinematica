package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class Perseguir extends Algoritmo {

    private static final char NOME = 's';
    
    public Perseguir(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }
    
    protected Perseguir(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();

        // Calcula a direção
        output.velocidade = new Vector3(alvo.posicao);
        output.velocidade.sub(agente.posicao);

        
        if (output.velocidade.len2() > 0) {
            // normaliza a velocidade (fica |v| = 1)
            output.velocidade.nor();
            // multiplica velovidade pela velocidade tangencial
            output.velocidade.scl(maxVelocidade);
        }

        return output;
    }

}
