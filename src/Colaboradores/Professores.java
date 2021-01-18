package src.Colaboradores;

import src.Colaboradores.Strategy.CriaProfessor;

public class Professores extends Colaboradores {
    public Professores(){
        tentaCriarColaborador = new CriaProfessor();
        
    }
}
