package src.Colaboradores.Strategy;

import src.Colaboradores.Colaboradores;
import src.Colaboradores.Pesquisadores;

public class CriaPesquisador implements CriaColaborador {
    Colaboradores colaborador = new Colaboradores();

    public Colaboradores criaColaborador() {
        Pesquisadores pesquisador = new Pesquisadores();
        colaborador.dadosColaborador(pesquisador);
        colaborador.setTipoColaborador("Pesquisador", pesquisador);
        System.out.println("Pesquisador inserido com sucesso!");
        return pesquisador;
    }
}
