package model;

import java.util.ArrayList;
import model.Produto;

public class Fabrica {

    private String nome;
    private ArrayList<Produto> produtos;
    private int qtProdutos;

    public Fabrica(String nome){
        this.nome       = nome;
        this.produtos   = new ArrayList<>();
        this.qtProdutos = 0;
    }

    public boolean acrescentaProduto(int nSerie){

        for(Produto a: produtos){           //verificar se o produto ja existe
            if(a.getnSerie() == nSerie){
                return false;
            }
        }

        Produto p = new Produto(nSerie);

        produtos.add(p);
        qtProdutos++;
        return true;
    }

    public Produto pesquisaProduto(int nSerie){

        for(Produto a: produtos){           //verificar se o produto ja existe
            if(a.getnSerie() == nSerie){
                return a;
            }
        }

        return null;
    }

    public boolean eliminaProduto(int nSerie){
        for(Produto a: produtos){
            if(a.getnSerie() == nSerie){
                qtProdutos--;
                return produtos.remove(a);
            }
        }

        return false;
    }

    public void eliminaReprovados(){
        produtos.removeIf(p -> p.getEstado().equalsIgnoreCase("REPROVADO"));
        qtProdutos = produtos.size();
    }

    public void testaUnidades(){

        for(Produto a: produtos){
            if(a.testaUnidade()){
                a.setEstado("Aprovado");
            }else a.setEstado("Reprovado");
        }
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        //sb.append(String.format("Fabrica: %s\r\n\r\n", nome));
        sb.append("Produtos:\r\n");
        if(qtProdutos > 0){
            for(int i = 0; i < qtProdutos; i++){
                sb.append(String.format("%d: \r\n", i + 1));
                sb.append(produtos.get(i).toString());
            }
        }else sb.append("Não existem produtos de momento");

        return sb.toString();
    }
}
