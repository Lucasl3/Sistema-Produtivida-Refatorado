package src.Colaboradores.Strategy;

import src.Orientacoes;
import src.Colaboradores.Alunos;
import src.Colaboradores.Colaboradores;

public class CriaAluno implements CriaColaborador {
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
