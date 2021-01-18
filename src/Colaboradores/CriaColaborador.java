package src.Colaboradores;

import src.Orientacoes;

public interface CriaColaborador {
    public Colaboradores criaColaborador();

}

class criaAluno implements CriaColaborador{
    Colaboradores colaborador = new Colaboradores();

    public Colaboradores criaColaborador() {
        Alunos aluno = Alunos.escolhaTipoAluno();
        colaborador.dadosColaborador(aluno);
        String tipo = "Aluno de " + aluno.getTipo();
        colaborador.setTipoColaborador(tipo, aluno);
        
        Orientacoes.incrementaNumeroAlunos();
        System.out.println(tipo + " inserido com sucesso!");
        return aluno;
    }
}

class criaProfessor implements CriaColaborador{
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

class criaPesquisador implements CriaColaborador{
    Colaboradores colaborador = new Colaboradores();

    public Colaboradores criaColaborador() {
        Pesquisadores pesquisador = new Pesquisadores();
        colaborador.dadosColaborador(pesquisador);
        colaborador.setTipoColaborador("Pesquisador", pesquisador);
        System.out.println("Pesquisador inserido com sucesso!");
        return pesquisador;
    }
}
