package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;

import java.util.Scanner;

public class ConsoleUtils {
    private static final String INVALID_OPTION_MSG = "Invalid option. Please try again!";

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getUserInput(String question) {
        return getUserOption(question);
    }

    public static String getUserOption(String message, String... options) {
        System.out.printf(message.concat("%n# : "));
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().trim();
        if (options.length > 0) {
            while (!isValid(option, options)) {
                System.out.printf("%s%n# : ", INVALID_OPTION_MSG);
                option = scanner.nextLine().trim();
            }
        }
        return option;
    }

    private static boolean isValid(String option, String... options) {
        for (String v : options) {
            if (v != null && v.equalsIgnoreCase(option)) {
                return true;
            }
        }
        return false;
    }
    public static boolean salvaReceita(){
        String input ="S";
        input = ConsoleUtils.getUserOption("Deseja salvar a receita? %nS - Sim   N - Não", "S", "N");
        if (input.equalsIgnoreCase("S")) {
            return true;
        }else {
            return false;
        }
    }
    public static Categoria listaCategoria(){

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
                 return categoria;
            }
        }
       return null;
    }
    public static TipoRendimento listaRendimento(){
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
                return tipoRendimento;

            }
        }
        return null;
    }
    public static TipoMedida listaMedida(){
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
                return tipoMedida;
            }
        }
        return null;
    }
}
