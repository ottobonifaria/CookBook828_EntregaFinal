package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.domain.Receita;

public class EditReceitaView {

    private Receita receita;

    public EditReceitaView(Receita receita) {

        if (receita != null){
            this.receita = new Receita(receita.getNome(),receita.getCategoria());
        }

        //this.receita = new Receita(receita);
    }

    public Receita edit() {
        String opcao = ConsoleUtils.getUserOption("Deseja salvar a receita? %nS - Sim   N - Não", "S", "N");
        if (opcao.equalsIgnoreCase("S")) {
            return receita;
        }else {
            return null;
        }

    }
}
