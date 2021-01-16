package src;

import java.util.Scanner;

import src.Colaboradores.Colaboradores;
import src.CustomExceptions.StringVaziaException;
public class Administrador {

    public static double lerNumero(){
        boolean loop = true;
        Scanner teclado = new Scanner(System.in);
        do{
            try{
                double numero = Double.parseDouble(teclado.nextLine());
                loop = false;
                return numero;
            }catch(NumberFormatException e){
                System.out.println(e);
                System.out.println("Input inválido.");
            }
        }while(loop);

        teclado.close();
        return 0;
    }

    public static String lerString(){
        Scanner teclado = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            try{
                String str = teclado.nextLine();
                if(str.length() < 1){
                    throw new StringVaziaException("String inválida para continuar a operação.");
                } else{
                    loop = false;
                    return str;
                }
            } catch(StringVaziaException e){
                System.out.println(e);
            }
        }

        teclado.close();
        return "";
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bem vindo ao sistema.\nDigite o nome do laboratório.");

        String nomeLaboratorio = lerString();
        LaboratorioPesquisa laboratorio = new LaboratorioPesquisa(nomeLaboratorio);
        Colaboradores colaboradores = new Colaboradores();
        Publicacoes publicacoes = new Publicacoes();

        boolean loop = true;
        
        while(loop){
            
            System.out.println("[1] - Adicionar novo colaborador.");
            System.out.println("[2] - Criar novo projeto.");
            System.out.println("[3] - Alocar colaborador a uma projeto.");
            System.out.println("[4] - Alterar status de um projeto.");
            System.out.println("[5] - Consultar projeto pelo titulo.");
            System.out.println("[6] - Consultar colaborador pelo nome.");
            System.out.println("[7] - Adicionar publicação.");
            System.out.println("[8] - Adicionar orientação");
            System.out.println("[9] - Relatório de produção acadêmica do laboratório");
            System.out.println("[0] - Sair.");
            
            int option = (int)lerNumero();

            switch(option){
                case 1:
                laboratorio.adicionarColaborador(colaboradores.adicionarColaborador());
                break;
                case 2:
                    Projetos.adicionarProjeto(laboratorio);
                    break;
                case 3:
                    Projetos.alocarColaborador(laboratorio);
                    break;
                case 4:
                    Projetos.alterarStatusProjeto();
                    break;
                case 5:
                    Projetos projeto = new Projetos();
                    projeto.buscarProjeto();
                    break;
                case 6:
                    colaboradores.buscarColaborador();
                    break;
                case 7:
                    publicacoes.adicionarPublicacao(laboratorio);
                    break;
                case 8:
                    Orientacoes orientacao = new Orientacoes();
                    orientacao.adicionarOrientacao();
                    break;
                case 9:
                    laboratorio.relatorioProducaoAcademica();
                    break;
                default:
                    loop = false;
                    break;
            }
            
        }
        teclado.close();

    }
}
