package src;

import java.util.ArrayList;
import java.util.Scanner;

import src.Colaboradores.Colaboradores;

public class Publicacoes {
    private String titulo;
    private String nomeConferencia;
    private int anoPublicacao;
    private ArrayList<Colaboradores> autores = new ArrayList<Colaboradores>();
    private Projetos projeto = null;
    private static int numeroPublicacoes = 0;

    Scanner teclado = new Scanner(System.in);

    public Publicacoes(){

    }

    public Publicacoes(String titulo, String nomeConferencia, int anoPublicacao, ArrayList<Colaboradores> autores){
        this.titulo = titulo;
        this.nomeConferencia = nomeConferencia;
        this.anoPublicacao = anoPublicacao;
        this.autores = autores;
    }

    public Publicacoes(String titulo, String nomeConferencia, int anoPublicacao, ArrayList<Colaboradores> autores, Projetos projeto){
        this.titulo = titulo;
        this.nomeConferencia = nomeConferencia;
        this.anoPublicacao = anoPublicacao;
        this.autores = autores;
        this.projeto = projeto;
    }

    public static int getNumeroPublicacoes() {
        return numeroPublicacoes;
    }

    public Projetos getProjeto() {
        return projeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeConferencia() {
        return nomeConferencia;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public ArrayList<Colaboradores> getAutores() {
        return autores;
    }

    public void publicacoesColaboradores(ArrayList<Colaboradores> autores, Publicacoes publicacao){
        for(int i = 0;i < autores.size();i++){
            autores.get(i).setPublicacoes(publicacao);
        }
    }

    public Publicacoes associaPublicacaoProjeto(String titulo, String nomeConferencia, int anoPublicacao, ArrayList<Colaboradores> autores){
        Publicacoes publicacao;
        
        if(!Projetos.projetos.isEmpty()){
            System.out.println("Deseja associar a publicação a um projeto? Digite 'sim' ou 'nao'.");
            String choice = Administrador.lerString();
            
    
            if(choice.equals("sim")){
                boolean loop = true;
                System.out.print("Digite o título do projeto que deseja associar à publicação: ");
                while(loop){
                    String tituloProjeto = Administrador.lerString();
                    int index = buscarProjeto(tituloProjeto);
                    if(index == -1){
                        System.out.println("Projeto não existente.");
                    } else if(!Projetos.projetos.get(index).getStatus().equals("Em andamento")){
                        System.out.println("Apenas projetos em andamento podem ser associados a publicações. Peça ao gerente para mudar o status do projeto.");
                        break;
                    } else {
                        publicacao = new Publicacoes(titulo, nomeConferencia, anoPublicacao, autores, Projetos.projetos.get(index));
                        publicacoesColaboradores(autores, publicacao);
                        Projetos.projetos.get(index).setPublicacoes(publicacao);
                        numeroPublicacoes++;
                        return publicacao;

                    }
                }
    
                
            }
            
        }

        numeroPublicacoes++;
        publicacao = new Publicacoes(titulo, nomeConferencia, anoPublicacao, autores);
        publicacoesColaboradores(autores, publicacao);
        return publicacao;
    }

    public Publicacoes dadosPublicacao(LaboratorioPesquisa lab) {
        System.out.println("Digite o(s) nome(s) do(s) autor(es) dessa publicação um por vez. Para finalizar a digitação digite 'sim'.");
        ArrayList<Colaboradores> autores = new ArrayList<Colaboradores>();

        
        boolean loop = true;
        
        while(loop){
            String autor = Administrador.lerString();
            int index = buscarColaborador(autor);
            if(autores.size() == 0 && autor.equals("sim")){
                System.out.println("A publicação precisa ter ao menos um autor.");

            } else if(autores.size() > 0 && autor.equals("sim")){
                loop = false;
            } else {
                if(index == -1){
                    System.out.println("Não existe esse colaborador");

                } else{
                    autores.add(lab.getColaboradores().get(index));
                }
            }
            
        }

        System.out.print("Digite o título da publicação: ");
        String titulo = Administrador.lerString();

        System.out.print("Digite o nome da conferência onde foi publicada: ");
        String nomeConferencia = Administrador.lerString();

        System.out.print("Digite o ano da publicação: ");
        int anoPublicacao = (int)Administrador.lerNumero();

        return associaPublicacaoProjeto(titulo, nomeConferencia, anoPublicacao, autores);   
    }
    
    public void adicionarPublicacao(LaboratorioPesquisa lab){
        if(lab.getColaboradores().isEmpty()){
            System.out.println("Você precisa de ao menos um colaborador no laboratória para adicionar uma publicação.");
            return;
        } 
        lab.setPublicacoes(dadosPublicacao(lab));
        System.out.println("Publicação feita com sucesso.\n");
    }


    public int buscarColaborador(String nomeProcurado){
        ArrayList<Colaboradores> colaboradores = LaboratorioPesquisa.colaboradores;
        int index = -1;

        for(int i = 0;i<colaboradores.size();i++){
            if(colaboradores.get(i).getNome().equalsIgnoreCase(nomeProcurado)){
                index = i;
                return index;
            }
        }
        return index;

    }

    public static int buscarProjeto(String tituloProcurado) {
        ArrayList<Projetos> projetos = Projetos.projetos;
        int indexProjeto = -1;

        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getTitulo().equalsIgnoreCase(tituloProcurado)) {
                indexProjeto = i;
                return indexProjeto;
            }
        }

        return indexProjeto;

    }
}
