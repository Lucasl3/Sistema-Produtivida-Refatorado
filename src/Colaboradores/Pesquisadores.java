package src.Colaboradores;

import src.Colaboradores.Strategy.CriaPesquisador;

public class Pesquisadores extends Colaboradores {
    public Pesquisadores(){
        tentaCriarColaborador = new CriaPesquisador();
        
    }
}
