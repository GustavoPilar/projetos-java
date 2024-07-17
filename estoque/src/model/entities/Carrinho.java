package model.entities;

import application.Main;
import application.UI;

import java.io.*;
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
            System.out.println(" ------ PRODUTOS ------ ");
            for(Produto p : produtosCarrinho) {
                System.out.println(p);
            }
            System.out.println("------------------------");
            System.out.println(String.format("R$%.2f", compraTotal()));
            System.out.println("------------------------");
            System.out.println("[1] - VOLTAR");
            resposta = Main.sc.nextInt();
        } while (resposta != 1);
    }

    public static void gerarNotaFiscal(File file) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("====================================");
            bw.newLine();
            bw.write("             NOTA FISCAL            ");
            bw.newLine();
            bw.write("Comprador: " + UI.user);
            bw.newLine();
            bw.write("====================================");
            for(Produto p : produtosCarrinho) {
                bw.newLine();
                bw.write("PRODUTO: " + p.getNome());
                bw.newLine();
                bw.write("VALOR: R$" + p.getPreco());
                bw.newLine();
                bw.write("QUANTIDADE: " + p.getQuantidade());
                bw.newLine();
                bw.write("---------------------");
                bw.newLine();
                bw.write("VALOR: R$" + String.format("%.2f", (p.getPreco() * p.getQuantidade())));
                bw.newLine();
                bw.write("====================================");
            }
            bw.newLine();
            bw.write("VALOR TOTAL: R$" + String.format("%.2f", compraTotal()));
            bw.newLine();
            bw.write("====================================");

            System.out.println("NOTA FISCAL GERADA.");

            System.out.println("\nVOCÊ PODE VER SUA NOTA EM (" + file.getAbsolutePath() + ")");
        }
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}

