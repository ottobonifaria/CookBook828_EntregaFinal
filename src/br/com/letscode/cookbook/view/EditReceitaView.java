package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.domain.Ingrediente;
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
                System.out.println("Digite o nome da receita");
                nomeOpcao = scanner.next();
                receita.setNome(nomeOpcao);
                return receita;
            case "C":
                System.out.println("Digite a categoria");
                nomeOpcao = scanner.next();
                receita.setCategoria(Categoria.valueOf(nomeOpcao.toUpperCase()));
                return receita;
            case "T":
                System.out.println("Digite o tempo de preparo");
                tempo = scanner.nextDouble();
                receita.setTempoPreparo(tempo);
                return receita;
            case "R":
                addrendimento();
                break;
            case "I":
                addIngrediente();
                break;
            case "M":
                System.out.println("Digite o modo de preparo");
                nomeOpcao = scanner.next();
                receita.setNome(nomeOpcao);
                return receita;
        }
        return null;

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

         input = ConsoleUtils.getUserOption("Deseja salvar a receita? %nS - Sim   N - Não", "S", "N");
        if (input.equalsIgnoreCase("S")) {
            Receita rc = new Receita(name,categoria,tempo,rendimento,listaIngrediente,null);
            return receita;
        }else {
            return null;
        }
    }
    private Ingrediente addIngrediente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual o nome do Ingrediente?");
        String nome = scanner.next();

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
        int minimo = scanner.nextByte();
        System.out.println("Digite Rendimento maximo");
        int maximo = scanner.nextByte();

        Rendimento rendimento = new Rendimento(minimo, maximo, tipoRendimento);
        return rendimento;
    }
}
