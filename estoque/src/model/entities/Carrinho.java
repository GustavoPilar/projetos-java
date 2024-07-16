package model.entities;

import java.util.LinkedHashSet;
import java.util.Set;

public final class Carrinho {

    public static Set<Produto> produtosCarrinho = new LinkedHashSet<>();

    public Carrinho() {
    }

    public static void addCarrinho(Produto produto, Integer quatidade) {
        if(produtosCarrinho.contains(produto)) {
            produto.setQuantidade(produto.getQuantidade() + quatidade);
        }
        else {
            produtosCarrinho.add(produto);
        }
    }

    public static Double compraTotal() {
        Double sum = 0.0;
        for(Produto p : produtosCarrinho) {
            sum += p.getPreco() * p.getQuantidade();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Valor total: R$" + String.format("%.2f", compraTotal());
    }
}

