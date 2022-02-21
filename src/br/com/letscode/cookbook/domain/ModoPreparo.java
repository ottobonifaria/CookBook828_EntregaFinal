package br.com.letscode.cookbook.domain;

public class ModoPreparo {
      private int passo;
      private String descriçãoPasso;

    public ModoPreparo() {
    }

    public ModoPreparo(int passo, String descriçãoPasso) {
        this.passo = passo;
        this.descriçãoPasso = descriçãoPasso;
    }

    public double getPasso() {
        return passo;
    }

    public void setPasso(int passo) {
        this.passo = passo;
    }

    public String getDescriçãoPasso() {

        return descriçãoPasso;
    }

    public void setDescriçãoPasso(String descriçãoPasso) {
        this.descriçãoPasso = descriçãoPasso;
    }
}
