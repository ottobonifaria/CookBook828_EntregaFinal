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
        //this.receita = new Receita(receita);
    }

    public Receita edit(){
        Scanner scanner = new Scanner(System.in);
        String nomeOpcao;

        String opcao = ConsoleUtils.getUserOption("O que deseja alterar? %nN - Nome   "
                        + "C - Categoria   T - Tempo de Preparo  R - Rendimento I - Ingredientes  M - Modo de preparo "   ,
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
        System.out.println("Incluir ou editar um passo?");

        String input = ConsoleUtils.getUserOption("Incluir ou editar um passo? %nI - Incluir  E - Editar", "I", "E");
        if( input.equalsIgnoreCase("E")){
            System.out.println("Digite numero do passo");
            int idPasso = scanner.nextInt()-1;
            scanner.nextLine();
            ModoPreparo modoPreparo = addModoPreparo(idPasso);
            listaModoPreparo.set(idPasso,modoPreparo);
            receita.setPreparo(listaModoPreparo);
        }else{
            System.out.println("Digite numero do passo");
            int idPasso = scanner.nextInt()-1;
            scanner.nextLine();
            ModoPreparo modoPreparo = addModoPreparo(idPasso);
            listaModoPreparo.add(idPasso,modoPreparo);
            receita.setPreparo(listaModoPreparo);
        }


        return receita;
    }

    private Receita editIngredientesReceita(Receita receita) {
        List<Ingrediente> listaIngredientes = receita.getIngredientes();
        ReceitaView view = new ReceitaView(receita);
        view.ingredientesView();

        System.out.println("Digite numero do ingrediente");
        int idIngrediente = scanner.nextInt()-1;
        scanner.nextLine();
        Ingrediente ingrediente = addIngrediente();
        listaIngredientes.set(idIngrediente,ingrediente);
        receita.setIngredientes(listaIngredientes);
        return receita;
    }

    private Receita editRendimentoReceita(Receita receita) {
        Rendimento rendimento = addrendimento();
        receita.setRendimento(rendimento);

        return receita;
    }

    private Receita editTempoPreparo(Receita receita) {
        System.out.println("Digite o tempo de preparo");
        tempo = scanner.nextDouble();
        receita.setTempoPreparo(tempo);
        return receita;
    }

    private Receita editCategoriaReceita(Receita receita) {
        String nomeOpcao;
        System.out.println("Digite a categoria");
        nomeOpcao = scanner.next();
        receita.setCategoria(Categoria.valueOf(nomeOpcao.toUpperCase()));
        return receita;
    }

    private Receita editNomeReceita(Receita receita) {
        String nomeOpcao;
        System.out.println("Digite o nome da receita");
        nomeOpcao = scanner.nextLine();
        receita.setNome(nomeOpcao);
        return receita;
    }

    public Receita addReceita(String name) {

        StringBuilder sb = new StringBuilder("Qual a categoria da nova receita?\n");
        String[] options = new String[Categoria.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, Categoria.values()[i]));
        }
        String input = ConsoleUtils.getUserOption(sb.toString(), options);
        Categoria categoria = null;
        for (int i = 0; i < options.length; i++) {
            if (input.equalsIgnoreCase(options[i])) {
                categoria = Categoria.values()[i];
                break;
            }
        }

        System.out.println("Digite o tempo de preparo");
        tempo = scanner.nextDouble();

        Rendimento rendimento = addrendimento();

        System.out.println("Quantos ingredientes tem a receita");
        int cont = scanner.nextInt();
        List<Ingrediente> listaIngrediente = new ArrayList<>();


        for(int i = 1;i<=cont;i++){
            listaIngrediente.add(addIngrediente());
        }

        input="S";
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
            Receita rc = new Receita(name,categoria,tempo,rendimento,listaIngrediente,listaPreparo);
            return rc;
        }else {
            return null;
        }
    }
    private Ingrediente addIngrediente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o nome do Ingrediente?");
        String nome = scanner.nextLine();

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
    private Rendimento addrendimento() {
        Scanner scanner = new Scanner(System.in);
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
    public ModoPreparo addModoPreparo( int passo){
        ModoPreparo modoPreparo = new ModoPreparo();

        System.out.println("Digite descrição do passo");
        String descricaoPasso = scanner.nextLine();

        modoPreparo.setPasso(passo);
        modoPreparo.setDescriçãoPasso(descricaoPasso);
        return modoPreparo;
    }
    
}
