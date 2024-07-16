package model.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void gerarNotaFiscal(File file) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("====================================");
            bw.newLine();
            bw.write("             NOTA FISCAL            ");
            bw.newLine();
            bw.write("====================================");
            bw.newLine();
            for(Produto p : Carrinho.produtosCarrinho) {
                bw.write("Produto: " + p.getNome());
                bw.newLine();
                bw.write("Valor: R$" + p.getPreco());
                bw.newLine();
                bw.write("Quantidade: " + p.getQuantidade());
                bw.newLine();
                bw.write("---------------------");
                bw.newLine();
                bw.write("====================================");
                bw.newLine();
                bw.write("Valor total: " + String.format("%.2f", Carrinho.compraTotal()));
                bw.newLine();
                bw.write("====================================");
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public String toString() {
        return "Valor total: R$" + String.format("%.2f", compraTotal());
    }
}

