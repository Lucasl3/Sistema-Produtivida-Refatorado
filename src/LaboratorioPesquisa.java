package src;

import src.Colaboradores.Colaboradores;
import java.util.ArrayList;


public class LaboratorioPesquisa {
    private String nomeLaboratorio;
    private ArrayList<Publicacoes> publicacoes = new ArrayList<Publicacoes>();
    public static ArrayList<Orientacoes> orientacoes = new ArrayList<Orientacoes>();
    public static ArrayList<Colaboradores> colaboradores = new ArrayList<Colaboradores>();

    public LaboratorioPesquisa(String nomeLaboratorio){
        this.nomeLaboratorio = nomeLaboratorio;
    }

    public ArrayList<Colaboradores> getColaboradores() {
        return colaboradores;
    }


    public void setOrientacoes(Orientacoes orientacao) {
        orientacoes.add(orientacao);
    }

    public void relatorioProducaoAcademica(){
        System.out.println("Número de colaboradores: " + Colaboradores.getNumeroColaboradores());
        System.out.println("Número de projetos em elaboração: " + Projetos.getNumeroProjetosElaboracao());
        System.out.println("Número de projetos em andamento: " + Projetos.getNumeroProjetosAndamento());
        System.out.println("Número de projetos concluídos: " + Projetos.getNumeroProjetosConcluido());
        System.out.println("Número total de projetos: " + Projetos.getNumeroProjetos());
        System.out.println("Número de publicações: " + Publicacoes.getNumeroPublicacoes());
        System.out.println("Número de orientações: " + Orientacoes.getNumeroOrientacoes() + "\n");
    }

    public void setPublicacoes(Publicacoes publicacao) {
        
        if(publicacoes.isEmpty()){
            this.publicacoes.add(publicacao);
        } else{
            for(int i = 0;i < publicacoes.size();i++){
                if(publicacao.getAnoPublicacao() > publicacoes.get(i).getAnoPublicacao()){
                    this.publicacoes.add(i, publicacao);
                    return;
                }
            }
            this.publicacoes.add(publicacao);
        }

    }

    public void getColaboradoresAlocamento() {
        for(int i = 0;i< colaboradores.size();i++){
            Colaboradores colaborador = colaboradores.get(i);
            System.out.println("Colaborador " + (i + 1));
            System.out.print("Nome: " + colaborador.getNome());
            System.out.print("| Email: " + colaborador.getEmail());
            System.out.println("| Tipo de colaborador: " + colaborador.getTipoColaborador() + "\n");
        }
    }

    public ArrayList<Colaboradores> getProfessores() {
        ArrayList<Colaboradores> professores = new ArrayList<Colaboradores>();

        for(int i = 0;i < colaboradores.size();i++){
            if(colaboradores.get(i).getTipoColaborador() == "Professor"){
                System.out.println(i);
                professores.add(colaboradores.get(i));
            }
        }
        return professores;
    }
    
    public LaboratorioPesquisa(){
        
    }
    
    public void adicionarColaborador(Colaboradores colaborador){
        colaboradores.add(colaborador);
    }
}
