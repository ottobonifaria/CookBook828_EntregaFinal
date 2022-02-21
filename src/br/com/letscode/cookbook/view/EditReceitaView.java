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

    public Receita addReceita(String name) {
        Categoria categoria = ConsoleUtils.listaCategoria();
        System.out.println("Digite o tempo de preparo");
        tempo = scanner.nextDouble();
        Rendimento rendimento = addrendimento();
        System.out.println("Quantos ingredientes tem a receita");
        int cont = scanner.nextInt();
        List<Ingrediente> listaIngrediente = new ArrayList<>();
        for(int i = 1;i<=cont;i++){
            //System.out.printf("Qual o nome do Ingrediente %d ?%n",i);
            listaIngrediente.add(addIngrediente());
        }
        String input ="S";
        List<ModoPreparo> listaPreparo = new ArrayList<>();
        int i= 1;
        System.out.println("-------------Modo de Preparo------------");
        scanner.nextLine();
        while (input.equalsIgnoreCase("S")){
            System.out.printf("Digite a descrição do passo %d%n",i);
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

    private Receita editModoPreparoReceita(Receita receita) {
        List<ModoPreparo> listaModoPreparo = receita.getPreparo();
        ReceitaView view = new ReceitaView(receita);
        view.preparoView();
        String teste = "S";
        while (teste.equalsIgnoreCase("S")) {
            String input = ConsoleUtils.getUserOption("Incluir, Editar ou eXcluir o modo de preparo? " +
                "%nI - Incluir  E - Editar  X - Excluir", "I", "E", "X");
            switch (input.toUpperCase()){

                case "E":
                    System.out.println("Digite o numero do passo a ser editado");
                    int idPasso = scanner.nextInt()-1;
                    if(idPasso <= listaModoPreparo.size()-1) {
                        ModoPreparo modoPreparo = addModoPreparo(idPasso);
                        if(ConsoleUtils.salvaReceita()){
                            listaModoPreparo.set(idPasso,modoPreparo);
                            receita.setPreparo(listaModoPreparo);
                        }else{
                            return null;
                        }
                    }else{
                        System.out.println("Não existe mode de praparo nesta posição");
                    }
                    break;
                case "I":
                    ModoPreparo modoPreparo = addModoPreparo(listaModoPreparo.size()+1);
                    if(ConsoleUtils.salvaReceita()){
                        listaModoPreparo.add(listaModoPreparo.size(),modoPreparo);
                        receita.setPreparo(listaModoPreparo);
                    }else{
                        return null;
                    }
                    break;
                case"X":
                    System.out.println("Digite numero do passo que deseja excluir");
                    idPasso = scanner.nextInt()-1;
                    if( idPasso <= listaModoPreparo.size() - 1){
                        if (ConsoleUtils.salvaReceita()) {
                            listaModoPreparo.remove(idPasso);
                        } else {
                            return null;
                        }
                    }else{
                        System.out.println("Não tem ingrediente para ser excluido desta posição");
                    }
                    break;
                default:
                    System.out.println("Opção invalida !!!");
                }
            view.preparoView();
            teste = ConsoleUtils.getUserOption("Deseja continuar Editando Ingredientes?S - Sim ou N - Não", "S", "N");
            }
        return receita;
    }
    public ModoPreparo addModoPreparo( int passo){
        ModoPreparo modoPreparo = new ModoPreparo();
        String descricaoPasso = ConsoleUtils.getUserInput("Digite descrição do passo");
        modoPreparo.setPasso(passo);
        modoPreparo.setDescriçãoPasso(descricaoPasso);
        return modoPreparo;
    }


    private Receita editIngredientesReceita(Receita receita) {
        List<Ingrediente> listaIngredientes = receita.getIngredientes();
        ReceitaView view = new ReceitaView(receita);
        view.ingredientesView();
        String teste ="S";
        while (teste.equalsIgnoreCase("S")) {
            String input = ConsoleUtils.getUserOption("-------------Menu Ingredientes------------" +
                " %nI - Incluir  E - Editar  X - Excluir", "I", "E", "X");
            switch (input.toUpperCase()){
                case "E":
                    System.out.println("Digite a Posição do ingrediente que deseja editar");
                    int idIngrediente = scanner.nextInt() - 1;
                    if (idIngrediente <= listaIngredientes.size() - 1) {
                        Ingrediente ingrediente = addIngrediente();
                        if (ConsoleUtils.salvaReceita()) {
                            listaIngredientes.set(idIngrediente, ingrediente);
                            receita.setIngredientes(listaIngredientes);
                            //return receita;
                        } else {
                            return null;
                        }
                    }else{
                        System.out.println("Não existe ingrediente nesta posição para editar");
                    }
                    break;
                case "I":
                    Ingrediente ingrediente = addIngrediente();
                    if (ConsoleUtils.salvaReceita()) {
                        listaIngredientes.add(listaIngredientes.size(), ingrediente);
                        receita.setIngredientes(listaIngredientes);
                    } else {
                        return null;
                    }
                break;
                case "X":
                    System.out.println("Digite a posição do ingrediente que deseja excluir");
                    idIngrediente = scanner.nextInt()-1;
                    if (idIngrediente <= listaIngredientes.size() - 1) {
                        if (ConsoleUtils.salvaReceita()) {
                            listaIngredientes.remove(idIngrediente);
                        } else {
                            return null;
                        }
                    }else{
                        System.out.println("Não tem ingrediente para ser excluido desta posição");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!!!");
            }
            view.ingredientesView();
            teste = ConsoleUtils.getUserOption("Deseja continuar Editando Ingredientes?S - Sim ou N - Não", "S", "N");
        }return receita;
    }

    private Ingrediente addIngrediente() {
        String nome = ConsoleUtils.getUserInput("Qual o nome do Ingrediente?");
        StringBuilder sb = new StringBuilder("Qual o tipo de medida do Ingrediente?\n");
        String[] options = new String[TipoMedida.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoMedida.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoMedida tipoMedida = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoMedida = TipoMedida.values()[i];
               break;
            }
        }

        System.out.println("Qual a quantidade?");
        Double quantidade = scanner.nextDouble();

        Ingrediente ingrediente = new Ingrediente(nome, quantidade, tipoMedida);
        return ingrediente;
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

    private Rendimento addrendimento() {
        StringBuilder sb = new StringBuilder("Qual o tipo de Rendimento?\n");
        String[] options = new String[TipoRendimento.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoRendimento.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoRendimento tipoRendimento = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoRendimento = TipoRendimento.values()[i];
                break;

            }
        }

        System.out.println("Digite Rendimento minimo");
        int minimo = scanner.nextInt();
        System.out.println("Digite Rendimento maximo");
        int maximo = scanner.nextInt();

        Rendimento rendimento = new Rendimento(minimo, maximo, tipoRendimento);
        return rendimento;
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
}