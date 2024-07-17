package model.entities;

import application.Main;
import application.UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Carrinho {

    public static Set<Produto> produtosCarrinho = new LinkedHashSet<>();

    public static void addCarrinho(Produto produto, Integer quantidade) {

        for(Produto p : produtosCarrinho) {
            if(p.getNome().equals(produto.getNome())) {
                p.setQuantidade(p.getQuantidade() + quantidade);
                produto = p;
                produtosCarrinho.removeIf(x -> x.getNome().equals(p.getNome()));
            }
        }

        produtosCarrinho.add(produto);
    }

    public static Double compraTotal() {
        double sum = 0.0;
        for(Produto p : produtosCarrinho) {
            sum += p.getPreco() * p.getQuantidade();
        }
        return sum;
    }

    public static void verCarrinho() {
        int resposta;
        do {
            System.out.println(" ------ Produtos ------ ");
            for(Produto p : produtosCarrinho) {
                System.out.println(p);
            }
            System.out.println("------------------------");
            System.out.println(String.format("R$%.2f", compraTotal()));
            System.out.println("------------------------");
            System.out.println("[1] - Voltar");
            resposta = Main.sc.nextInt();
        } while (resposta != 1);
    }

    public static void gerarNotaFiscal(File file) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("====================================");
            bw.newLine();
            bw.write("             NOTA FISCAL            ");
            bw.newLine();
            bw.write("====================================");
            for(Produto p : produtosCarrinho) {
                bw.newLine();
                bw.write("Produto: " + p.getNome());
                bw.newLine();
                bw.write("Valor: R$" + p.getPreco());
                bw.newLine();
                bw.write("Quantidade: " + p.getQuantidade());
                bw.newLine();
                bw.write("---------------------");
                bw.newLine();
                bw.write("Valor: R$" + String.format("%.2f", (p.getPreco() * p.getQuantidade())));
                bw.newLine();
                bw.write("====================================");
            }
            bw.newLine();
            bw.write("Valor total: R$" + String.format("%.2f", compraTotal()));
            bw.newLine();
            bw.write("====================================");

            System.out.println("Nota fiscal gerada!");
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

