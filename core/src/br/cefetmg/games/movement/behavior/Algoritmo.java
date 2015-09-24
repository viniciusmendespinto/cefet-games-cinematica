package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.Alvo;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

/**
 *
 * @author Flávio Coutinho <fegemo@gmail.com>
 */
public abstract class Algoritmo {
    //public Pose agente;
    public float maxVelocidade;
    public Alvo alvo;
    public char nome;
    public boolean protegeOrientacao;
    
    public Algoritmo(char nome) {
        this(nome, false);
    }
    
    public Algoritmo(char nome, boolean protege) {
        this.nome = nome;
        this.protegeOrientacao = protege;
    }
  
    public abstract Direcionamento guiar(Pose agente);
}
