package src;

import src.Colaboradores.Colaboradores;
import src.CustomExceptions.TituloDisponivelException;

import java.util.ArrayList;
import java.util.Scanner;


public class Projetos {
    private String titulo;
    private String dataInicio, dataTermino;
    private String agenciaFinanciadora;
    private double valorFinanciado;
    private String objetivo;
    private String descricao;
    private String status;
    private boolean trocaStatus = false;
    private ArrayList<Colaboradores> participantes = new ArrayList<Colaboradores>();
    private ArrayList<Publicacoes> publicacoes = new ArrayList<Publicacoes>();
    static ArrayList<Projetos> projetos = new ArrayList<Projetos>();
    private static int numeroProjetosElaboracao = 0;
    private static int numeroProjetosAndamento = 0;
    private static int numeroProjetosConcluido = 0;
    private static int numeroProjetos = 0;


    static Scanner teclado = new Scanner(System.in);

    public Projetos(String titulo, String dataInicio, String dataTermino, String agenciaFinanciadora, double valorFinanciado, String objetivo, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.status = "Em elaboração";
    }

    public Projetos() {

    }

    public ArrayList<Colaboradores> getParticipantes() {
        return participantes;
    }

    public static int getNumeroProjetos() {
        return numeroProjetos;
    }

    public static int getNumeroProjetosAndamento() {
        return numeroProjetosAndamento;
    }

    public static int getNumeroProjetosConcluido() {
        return numeroProjetosConcluido;
    }

    public static int getNumeroProjetosElaboracao() {
        return numeroProjetosElaboracao;
    }

    public void setPublicacoes(Publicacoes publicacoes) {
        this.publicacoes.add(publicacoes);
    }

    public String getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }

    public double getValorFinanciado() {
        return valorFinanciado;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setTrocaStatus(boolean trocaStatus) {
        this.trocaStatus = trocaStatus;
    }

    public boolean getTrocaStatus(){
        return this.trocaStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public static void getParticipantesProjeto(ArrayList<Colaboradores> participantes) {

        System.out.println("Quantidade de participantes do projeto: " + participantes.size() + "\n");

        for (int i = 0; i < participantes.size(); i++) {
            Colaboradores participanteAtual = participantes.get(i);
            System.out.println("Colaborador " + (i + 1));
            System.out.println("Nome: " + participanteAtual.getNome());
            System.out.println("Email: " + participanteAtual.getEmail());
            System.out.println("Tipo de colaborador: " + participanteAtual.getTipoColaborador() + "\n");
        }
    }

    public void dadosProjetos(Projetos projeto){
        System.out.println("Título: " + projeto.titulo);
        System.out.println("Data de início: " + projeto.dataInicio + " até " + "data de término: " + projeto.dataTermino);
        System.out.println("Agência financiadora: " + projeto.agenciaFinanciadora);
        System.out.println("Valor financiado: " + projeto.valorFinanciado);
        System.out.println("Descrição: " + projeto.descricao);
        System.out.println("Objetivo: " + projeto.objetivo);
        System.out.println("Status do projeto: " + projeto.status + "\n");
    }

    public static void listaProfessoresLaboratorio(LaboratorioPesquisa lab, Projetos projetoAtual) {
        ArrayList<Colaboradores> professores = lab.getProfessores();

        System.out.println("O projeto precisa de ao menos um professor como participante.");
        System.out.println("Escolha um professor para alocar a esse projeto:\n");

        if (professores.size() == 0) {
            System.out.println(
                    "Não há professores colaboradores no laboratório, adicione algum professor para mudar o status do projeto.");
        } else {

            for (int i = 0; i < professores.size(); i++) {
                System.out.println("Professor " + (i + 1));
                System.out.println("Nome: " + professores.get(i).getNome());
                System.out.println("Email: " + professores.get(i).getEmail() + "\n");
            }

            System.out.println("Escolha pelo indice um professor para ser alocado a esse projeto.");

            int option = (int)Administrador.lerNumero() - 1;
            Colaboradores professor = professores.get(option);
            projetoAtual.participantes.add(professor);
            professor.addProjetosColaboradores(projetoAtual);
            projetoAtual.setTrocaStatus(true);

            System.out.println("Professor '" + professor.getNome() + "' alocado com sucesso para o projeto '"
                    + projetoAtual.titulo + "'.");

        }

    }

    public static boolean tituloDisponivel(String titulo) {

        for (int i = 0; i < projetos.size(); i++) {
            String tituloAtual = projetos.get(i).titulo;
            if (tituloAtual.equalsIgnoreCase(titulo)) {
                return false;
            }
        }

        return true;
    }

    public static boolean dataValida(String data){
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        
        return data != null && data.matches(regex);
    }

    public static void insereProjetoOrdenado(Projetos novoProjeto, LaboratorioPesquisa laboratorio){
        String[] data = novoProjeto.dataTermino.split("/");
        int ano = Integer.parseInt(data[2]);
        
        if(projetos.isEmpty()){
            projetos.add(novoProjeto);
        } else{
            for(int i=0;i<projetos.size();i++){
                String[] dataAtual = projetos.get(i).dataTermino.split("/");
                int anoAtual = Integer.parseInt(dataAtual[2]);

                if(ano > anoAtual){
                    numeroProjetos++;
                    numeroProjetosElaboracao++;
                    System.out.println("Projeto criado com sucesso!\n");
                    projetos.add(i, novoProjeto);
                    listaProfessoresLaboratorio(laboratorio, novoProjeto);
                    return;
                }
            }
            projetos.add(novoProjeto);
        }

        numeroProjetos++;
        numeroProjetosElaboracao++;
        System.out.println("Projeto criado com sucesso!\n");

        listaProfessoresLaboratorio(laboratorio, novoProjeto);
    }

    public static String tituloDisponivelException() {
        boolean loop = true;

        while (loop) {
            try {
                String titulo = Administrador.lerString();
                if (!tituloDisponivel(titulo)) {
                    throw new TituloDisponivelException("Título já usado em outro projeto");
                } else {
                    loop = false;
                    return titulo;
                }

            } catch (TituloDisponivelException e) {
                System.out.println(e);
                System.out.println("Digite outro título: ");
            }
        }

        return "";
    }

    public static void adicionarProjeto(LaboratorioPesquisa laboratorio) {
        System.out.println("Criação de um projeto:");

        System.out.print("Título do projeto: ");
        String titulo = tituloDisponivelException();

        System.out.print("Data de início, digite no formato 'dd/mm/yyyy': ");
        String dataInicio = Administrador.lerString();

        while(!dataValida(dataInicio)){
            System.out.print("Data inválida, digite outra: ");
            dataInicio = Administrador.lerString();
        }

        System.out.print("Data de término, digite no formato 'dd/mm/yyyy': ");
        String dataTermino = Administrador.lerString();

        while(!dataValida(dataTermino)){
            System.out.print("Data inválida, digite outra: ");
            dataTermino = Administrador.lerString();
        }

        System.out.print("Informe a agência financiadora: ");
        String agenciaFinanciadora = Administrador.lerString();

        System.out.print("Informe o valor financiado no projeto: ");
        double valorFinanciado = Administrador.lerNumero();

        System.out.println("Qual o objetivo do projeto: ");
        String objetivo = Administrador.lerString();

        System.out.println("Descrição do projeto: ");
        String descricao = Administrador.lerString();

        Projetos novoProjeto = new Projetos(titulo, dataInicio, dataTermino, agenciaFinanciadora, valorFinanciado,
                objetivo, descricao);

        insereProjetoOrdenado(novoProjeto, laboratorio);


    }

    public static void alocaColaboradorProjeto(Colaboradores colaborador, Projetos projeto){
        if(colaborador.getTipoColaborador().equals("Aluno de graduação") && colaborador.getNumeroProjetosAndamento() == 2 && projeto.getStatus().equals("Em andamento")){
            System.out.println("Esse aluno não pode ser alocado a mais um projeto em andamento. Esse aluno só pode participar de 2 projetos em andamento.");
            return;
        }

        if(projeto.getStatus().equals("Em andamento")){
            colaborador.setNumeroProjetosAndamento(1);
        }
        
        projeto.participantes.add(colaborador);
        colaborador.addProjetosColaboradores(projeto);

        System.out.println(colaborador.getNome() + " foi alocado para o projeto " + projeto.titulo);        

        if (colaborador.getTipoColaborador() == "Professor") projeto.setTrocaStatus(true);
    }

    public static void alocarColaborador(LaboratorioPesquisa lab) {
        if(projetos.size() == 0){
            System.out.println("Não há projetos disponíveis.");
            return;
        } else if(lab.getColaboradores().isEmpty()){
            System.out.println("Não há colaboradores disponíveis.");
            return;
        }

        lab.getColaboradoresAlocamento();

        System.out.println("Escolha o indice do colaborador que deseja alocar a um projeto: ");

        int option = (int)Administrador.lerNumero();
        Colaboradores colaborador = lab.getColaboradores().get(option - 1);

        listaProjetos();

        String tituloProcurado;
        int indexProjeto = -1;

        while(indexProjeto == -1){
            System.out.println("Digite o título do projeto que deseja associar " + colaborador.getNome());
            tituloProcurado = Administrador.lerString();

            for (int i = 0; i < projetos.size(); i++) {
                if (projetos.get(i).titulo.equalsIgnoreCase(tituloProcurado)) {
                    indexProjeto = i;
                }
            }
            if(indexProjeto == -1){
                System.out.println("Projeto inexistente");
            }
        }

        
        Projetos projeto = projetos.get(indexProjeto);

        alocaColaboradorProjeto(colaborador, projeto);
    }

    public static void listaProjetos() {
        for (int i = 0; i < projetos.size(); i++) {
            System.out.println("Projeto " + (i + 1));
            System.out.println("Título: " + projetos.get(i).titulo);
            System.out.println("Status: " + projetos.get(i).status);
        }
    }

    public static void mudaStatusProjeto(int option, Projetos projeto){
        if (option == 1 && projeto.trocaStatus) {
            if(alunoGraduacaoValido(projeto)) {
                numeroProjetosElaboracao--;
                numeroProjetosAndamento++;
                projeto.setStatus("Em andamento");
                System.out.println("Projeto " + projeto.titulo + ": status trocado para 'Em andamento'.");
            } else {
                System.out.println("Não é possível fazer a troca do status. Há aluno(s) de graduação em 2 projetos em andamento.");
            }
            return;
        } else if (option == 1 && !projeto.trocaStatus) {
            System.out.println("O projeto deve ter ao menos um professor para ter seu status trocado.");
        }

        if (option == 2 && projeto.publicacoes.size() != 0 && projeto.trocaStatus) {
            numeroProjetosAndamento--;
            numeroProjetosConcluido++;
            casoAlunoGraduacao(projeto);
            projeto.setStatus("Concluído");
            System.out.println("Projeto " + projeto.titulo + ": status trocado para '" + projeto.getStatus() + "'.");
        } else if (option == 2 && projeto.publicacoes.size() == 0 && projeto.trocaStatus) {
            System.out
                    .println("O projeto deve ter ao menos uma publicação para ter seu status alterado para concluído");
        }
    }

    public static void alterarStatusProjeto() {
        listaProjetos();
        String tituloProcurado;
        int indexProjeto = -1;

        while(indexProjeto == -1){
            System.out.print("Digite o título do projeto que deseja alterar o status: ");
            tituloProcurado = Administrador.lerString();

            for (int i = 0; i < projetos.size(); i++) {
                String tituloAtual = projetos.get(i).titulo;
                if (tituloAtual.equalsIgnoreCase(tituloProcurado)) {
                    indexProjeto = i;
                }
            }
            if(indexProjeto == -1){
                System.out.println("Projeto inexistente");
            }
        }

        Projetos projeto = projetos.get(indexProjeto);

        System.out.println("[1] Alterar status para 'Em andamento'.");
        System.out.println("[2] Alterar status para 'Concluído'.");

        int option = (int)Administrador.lerNumero();
        mudaStatusProjeto(option, projeto);
    }

    public static void casoAlunoGraduacao(Projetos projeto) {
        ArrayList<Colaboradores> participantes = projeto.participantes;

        for(int i = 0;i < participantes.size();i++){
            Colaboradores colaborador = participantes.get(i);
            if(colaborador.getTipoColaborador().equals("Aluno de graduação")){
                colaborador.setNumeroProjetosAndamento(-1);
            }
        }

    }

    public static boolean alunoGraduacaoValido(Projetos projeto) {
        int flag = 0;

        ArrayList<Colaboradores> participantesProjetoAtual = projeto.getParticipantes();

        for(int i = 0;i < participantesProjetoAtual.size();i++){
            Colaboradores participante = participantesProjetoAtual.get(i);
            if(participante.getTipoColaborador().equals("Aluno de graduação")){
                if(participante.getNumeroProjetosAndamento() < 2){
                    participante.setNumeroProjetosAndamento(1);
                } else {
                    flag = 1;
                }
            }
        }
        return flag == 0 ? true : false;
    }

    public void buscarProjeto() {
        System.out.println("Digite o título do projeto que deseja consultar.");

        String tituloProcurado = Administrador.lerString();
        int indexProjeto = -1;

        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).titulo.equalsIgnoreCase(tituloProcurado)) {
                indexProjeto = i;
            }
        }

        if (indexProjeto == -1) {
            System.out.println("Não existe nenhum projeto com o titulo '" + tituloProcurado + "'.");
            return;
        }
        Projetos projeto = projetos.get(indexProjeto);
        dadosProjetos(projeto);

        ArrayList<Colaboradores> participantesProjetoAtual = projeto.participantes;

        getParticipantesProjeto(participantesProjetoAtual);
        mostraPublicacoesProjeto(projeto);
    }

    public void mostraPublicacoesProjeto(Projetos projeto){
        ArrayList<Publicacoes> publicacoes = projeto.publicacoes;

        if(publicacoes.isEmpty()){
            System.out.println("Não há publicações associadas a esse projeto");
            return;
        }

        System.out.println("Publicações do projeto '" + projeto.titulo + "'");

        for(int i = 0;i < publicacoes.size();i++){
            System.out.println("Publicação " + (i + 1));
            ArrayList<Colaboradores> autores = publicacoes.get(i).getAutores();

            System.out.println("Autor(es) da publicação: ");
            for(int j = 0;j < autores.size();j++){
                System.out.println("Autor " + (j + 1));
                System.out.println("Nome: " + autores.get(j).getNome());
                System.out.println("Tipo de colaborador: " + autores.get(j).getTipoColaborador() + '\n');
            }

            System.out.println("Título: " + publicacoes.get(i).getTitulo());
            System.out.println("Nome da conferência: " + publicacoes.get(i).getNomeConferencia());
            System.out.println("Ano de publicação: " + publicacoes.get(i).getAnoPublicacao() + '\n');
        }
    }

}
