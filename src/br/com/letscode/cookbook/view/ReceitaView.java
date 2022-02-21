package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.controller.Catalogo;
import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.domain.ModoPreparo;
import br.com.letscode.cookbook.domain.Receita;

import java.io.PrintStream;

public class ReceitaView {
    private Receita receita;

    public ReceitaView(Receita receita) {
        this.receita = receita;
    }

    public void fullView(PrintStream out) {
        if (receita == null) {
            out.printf("%n%s%n%n", "Nenhuma receita encontrada!");
        } else {
            headerView(out);
            ingredientesView();
            preparoView();
        }
    }

    public void headerView(PrintStream out) {
        out.printf("%n--------------  %s  --------------%n%n", receita.getNome());
        out.printf("Indice da Receita %d%n", new Catalogo().getTotal());
        out.printf("Categoria: %s%n", receita.getCategoria().name());
        out.printf("Tempo de preparo: %s minutos %n", receita.getTempoPreparo());
        if (receita.getRendimento() != null) {
            if (receita.getRendimento().getMinimo() != receita.getRendimento().getMaximo()) {
                out.printf("Rendimento: de %s à %s %s%n", receita.getRendimento().getMinimo(), receita.getRendimento().getMaximo(), receita.getRendimento().getTipo().name());
            } else {
                out.printf("Rendimento: %s %s%n", receita.getRendimento().getMinimo(), receita.getRendimento().getTipo().name());
            }
        }
    }

    public void ingredientesView() {
        System.out.println("------------- Ingredientes -------------");
        if (receita.getIngredientes() == null || receita.getIngredientes().isEmpty()) {
            System.out.println( "Nenhum ingrediente encontrado!");
        } else {
            int i=0;
            for (Ingrediente ingrediente : receita.getIngredientes()) {
                System.out.printf("%s - %s %s de %s%n", (i+1),ingrediente.getQuantidade(), ingrediente.getTipo().name(), ingrediente.getNome());
                i++;
            }
        }
    }

    public void preparoView() {
        System.out.println("------------- Modo de Preparo ------------");
        if (receita.getPreparo() == null || receita.getPreparo().isEmpty()) {
            System.out.println("Nenhum preparo encontrado!");
        } else {
            int i = 0;
            for (ModoPreparo modoPreparo: receita.getPreparo()
                 ) {
                System.out.println( "Passo " + (i+1) + "-" + modoPreparo.getDescriçãoPasso());
                i++;
            }

        }
    }
}
