package br.cefetmg.games.movement.behavior;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public class Fugir extends Perseguir {

    private static final char NOME = 'f';

    public Fugir(float maxVelocidade) {
        super(NOME, -maxVelocidade);
    }

}
