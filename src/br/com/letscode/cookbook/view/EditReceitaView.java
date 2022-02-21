package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.domain.ModoPreparo;
import br.com.letscode.cookbook.domain.Receita;
import br.com.letscode.cookbook.domain.Rendimento;
import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditReceitaView {
    Scanner scanner = new Scanner(System.in);
    private Receita receita;
    private double tempo;

    public EditReceitaView() {
    }

    public EditReceitaView(Receita receita) {

        if (receita != null){
            this.receita = new Receita(receita.getNome(),receita.getCategoria(),
                    receita.getTempoPreparo(), receita.getRendimento(),receita.getIngredientes(),receita.getPreparo());
        }
    }

    public Receita MenuEditReceitaView(){
        Scanner scanner = new Scanner(System.in);
        String nomeOpcao;
        String opcao = ConsoleUtils.getUserOption("O que deseja alterar? %nN - Nome        "+
                        "C - Categoria     T - Tempo de Preparo%n" +"R - Rendimento  I - Ingredientes  M - Modo de preparo ",
                "N", "C","T","R","I", "M").toUpperCase();

        switch (opcao) {
            case "N":
                return editNomeReceita(receita);
            case "C":
                return editCategoriaReceita(receita);
            case "T":
                return editTempoPreparo(receita);
            case "R":
                return editRendimentoReceita(receita);
            case "I":
                return editIngredientesReceita(receita);
            case "M":
                return editModoPreparoReceita(receita);

        }
        return null;

    }

    private Receita editModoPreparoReceita(Receita receita) {
        List<ModoPreparo> listaModoPreparo = receita.getPreparo();
        ReceitaView view = new ReceitaView(receita);
        view.preparoView();
        String input = ConsoleUtils.getUserOption("Incluir, Editar ou eXcluir o modo de preparo? " +
                "%nI - Incluir  E - Editar  X - Excluir", "I", "E", "X");
        if( input.equalsIgnoreCase("E")){
            System.out.println("Digite numero do passo a ser editado");
            int idPasso = scanner.nextInt()-1;
            scanner.nextLine();
            ModoPreparo modoPreparo = addModoPreparo(idPasso);
            listaModoPreparo.set(idPasso,modoPreparo);
            receita.setPreparo(listaModoPreparo);
        }else if(input.equalsIgnoreCase("I")){
            System.out.println("Digite numero do passo a ser incluido");
            int idPasso = scanner.nextInt()-1;
            scanner.nextLine();
            ModoPreparo modoPreparo = addModoPreparo(idPasso);
            listaModoPreparo.add(idPasso,modoPreparo);
            receita.setPreparo(listaModoPreparo);
        }else{
            System.out.println("Digite numero do passo a ser excluido");
            int idPasso = scanner.nextInt()-1;
            scanner.nextLine();
            listaModoPreparo.remove(idPasso);
        }
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }
    }

    private Receita editIngredientesReceita(Receita receita) {
        List<Ingrediente> listaIngredientes = receita.getIngredientes();
        ReceitaView view = new ReceitaView(receita);
        view.ingredientesView();
        String input = ConsoleUtils.getUserOption("Incluir, Editar ou eXcluir o ingrediente?" +
                " %nI - Incluir  E - Editar  X Excluir", "I", "E", "X");
        if(input.equalsIgnoreCase("E")){
            System.out.println("Digite a Posição que deseja editar o ingrediente");
            int idIngrediente = scanner.nextInt()-1;
            scanner.nextLine();
            Ingrediente ingrediente = addIngrediente();
            listaIngredientes.set(idIngrediente,ingrediente);
            receita.setIngredientes(listaIngredientes);
        }else if(input.equalsIgnoreCase("I")){
            System.out.println("Digite a posição do ingrediente a ser incluido");
            int idIngrediente = scanner.nextInt()-1;
            scanner.nextLine();
            Ingrediente ingrediente = addIngrediente();
            listaIngredientes.add(idIngrediente,ingrediente);
            receita.setIngredientes(listaIngredientes);
        }else{
            System.out.println("Digite a posição do ingrediente a ser excluido");
            int idIngrediente = scanner.nextInt()-1;
            scanner.nextLine();
            listaIngredientes.remove(idIngrediente);
        }
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }
    }

    private Receita editRendimentoReceita(Receita receita) {
        Rendimento rendimento = addrendimento();
        receita.setRendimento(rendimento);
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }
    }

    private Receita editTempoPreparo(Receita receita) {
        System.out.println("Digite o tempo de preparo");
        tempo = scanner.nextDouble();
        receita.setTempoPreparo(tempo);
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }
    }

    private Receita editCategoriaReceita(Receita receita) {
        Categoria categoria = ConsoleUtils.listaCategoria();
        receita.setCategoria(categoria);
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }
    }

    private Receita editNomeReceita(Receita receita) {
        String nomeOpcao;
        System.out.println("Digite o nome da receita");
        nomeOpcao = scanner.nextLine();
        receita.setNome(nomeOpcao);
        if (ConsoleUtils.salvaReceita()) {
            return receita;
        }else {
            return null;
        }

    }

    public Receita addReceita(String name) {

        Categoria categoria = ConsoleUtils.listaCategoria();
        System.out.println("Digite o tempo de preparo");
        tempo = scanner.nextDouble();
        Rendimento rendimento = addrendimento();
        System.out.println("Quantos ingredientes tem a receita");
        int cont = scanner.nextInt();
        List<Ingrediente> listaIngrediente = new ArrayList<>();
        for(int i = 1;i<=cont;i++){
            listaIngrediente.add(addIngrediente());
        }
         String input ="S";
        List<ModoPreparo> listaPreparo = new ArrayList<>();
        int i= 1;
        scanner.nextLine();
        while (input.equalsIgnoreCase("S")){
            listaPreparo.add(addModoPreparo(i));
            input = ConsoleUtils.getUserOption("Deseja incluir mais um modo de preparo? %nS - Sim   N - Não", "S", "N");
            i++;
        }
        input = ConsoleUtils.getUserOption("Deseja salvar a receita? %nS - Sim   N - Não", "S", "N");
        if (input.equalsIgnoreCase("S")) {
            Receita receita = new Receita(name,categoria,tempo,rendimento,listaIngrediente,listaPreparo);
            return receita;
        }else {
            return null;
        }
    }
    private Ingrediente addIngrediente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o nome do Ingrediente?");
        String nome = scanner.nextLine();

        TipoMedida tipoMedida = ConsoleUtils.listaMedida();
        System.out.println("Qual a quantidade?");
        Double quantidade = scanner.nextDouble();

        Ingrediente ingrediente = new Ingrediente(nome, quantidade, tipoMedida);
        return ingrediente;
    }
    private Rendimento addrendimento() {
        TipoRendimento tipoRendimento = ConsoleUtils.listaRendimento();

        System.out.println("Digite Rendimento minimo");
        int minimo = scanner.nextInt();
        System.out.println("Digite Rendimento maximo");
        int maximo = scanner.nextInt();

        Rendimento rendimento = new Rendimento(minimo, maximo, tipoRendimento);
        return rendimento;
    }
    public ModoPreparo addModoPreparo( int passo){
        ModoPreparo modoPreparo = new ModoPreparo();

        System.out.println("Digite descrição do passo");
        String descricaoPasso = scanner.nextLine();

        modoPreparo.setPasso(passo);
        modoPreparo.setDescriçãoPasso(descricaoPasso);
        return modoPreparo;
    }

}