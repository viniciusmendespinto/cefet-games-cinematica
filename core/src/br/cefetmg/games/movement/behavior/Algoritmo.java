package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.Alvo;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;

/**
 * Um algoritmo de movimentação.
 * O principal método é o "guiar" que, dada uma pose de um agente, retorna
 * qual o direcionamento (velocidade) ele deve seguir.
 * 
 * @author Flávio Coutinho <fegemo@gmail.com>
 * @see Direcionamento
 */
public abstract class Algoritmo {
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
