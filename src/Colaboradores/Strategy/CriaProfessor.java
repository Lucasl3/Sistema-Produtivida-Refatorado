package src.Colaboradores.Strategy;

import src.Orientacoes;
import src.Colaboradores.Colaboradores;
import src.Colaboradores.Professores;

public class CriaProfessor implements CriaColaborador {
    Colaboradores colaborador = new Colaboradores();

    public Colaboradores criaColaborador() {
        Professores professor = new Professores();
        colaborador.dadosColaborador(professor);
        colaborador.setTipoColaborador("Professor", professor);
        Orientacoes.incrementaNumeroProfessores();
        System.out.println("Professor inserido com sucesso!");
        return professor;
    }
}
