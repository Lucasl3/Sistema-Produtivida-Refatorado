package src.Colaboradores;

import java.util.Scanner;

import src.Administrador;

public class Alunos extends Colaboradores { 
    public String tipo;

    static Scanner teclado = new Scanner(System.in);

    public Alunos(String tipo) {
        this.tipo = tipo;
        
    }
    
    public Alunos(){
        tentaCriarColaborador = new criaAluno();
        
    }

    public String getTipo() {
        return tipo;
    }

    static Alunos escolhaTipoAluno() {

        System.out.println("Qual o tipo de aluno?");
        System.out.println("[1] - Aluno de graduação");
        System.out.println("[2] - Aluno de mestrado");
        System.out.println("[3] - Aluno de doutorado");

        int option = (int)Administrador.lerNumero();

        Alunos aluno;

        switch(option){
            case 1:
                aluno = new Alunos("graduação");
                
                return aluno;
            case 2: 
                aluno = new Alunos("mestrado");
                return aluno;
            case 3:
                aluno = new Alunos("doutorado");
                return aluno;
            
            default:
                return null;
        }
    }
}
