package br.com.letscode.cookbook;

import br.com.letscode.cookbook.controller.Catalogo;
import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.domain.ModoPreparo;
import br.com.letscode.cookbook.domain.Receita;
import br.com.letscode.cookbook.domain.Rendimento;
import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;
import br.com.letscode.cookbook.view.CatalogoView;

import java.util.ArrayList;
import java.util.List;

public class CookBook {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        //catalogo.add(new Receita("Cookies da Lara 1", Categoria.DOCE));
        //catalogo.add(new Receita("Cookies da Lara 2", Categoria.DOCE));
        //catalogo.add(new Receita("Cookies da Lara 3", Categoria.DOCE));
        //catalogo.add(new Receita("Cookies da Lara 4", Categoria.DOCE));
        String nome= "Suco de Laranja";
        Categoria categoria = Categoria.BEBIDA;
        double tempoPreparo = 20;
        Rendimento rendimento = new Rendimento(5,6,TipoRendimento.COPOS);

        List<Ingrediente> ingredientes = new ArrayList<>();
        Ingrediente primeiroIngredinte = new Ingrediente("Laranja",12, TipoMedida.UNIDADE);
        ingredientes.add(primeiroIngredinte);
        Ingrediente segundoIngredinte = new Ingrediente("Açucar",1, TipoMedida.XICARA);
        ingredientes.add(segundoIngredinte);

        List<ModoPreparo> listaModoPreparo = new ArrayList<>();
        ModoPreparo primeiroModo = new ModoPreparo(1,"Expremer as Laranjas");
        listaModoPreparo.add(primeiroModo);
        ModoPreparo segundoModo = new ModoPreparo(2,"Colocar o açucar");
        listaModoPreparo.add(segundoModo);

        Receita receita = new Receita(nome,categoria,tempoPreparo, rendimento, ingredientes,  listaModoPreparo);
        catalogo.add(receita);
        CatalogoView view = new CatalogoView(catalogo);
        view.view();
    }
}
