package model.entities;

import application.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Carrinho {

    public static Set<Produto> produtosCarrinho = new LinkedHashSet<>();

    private Carrinho() {
    }

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
        return produtosCarrinho.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
    }

    public static void verCarrinho() {
        String resposta;
        do {
            System.out.println(" ------ PRODUTOS ------ ");
            if(produtosCarrinho.isEmpty()) {
                System.out.println("Nenhum produto....");
            }
            else {
                produtosCarrinho.forEach(System.out::println);
            }
            System.out.println("------------------------");
            System.out.printf("R$%.2f%n", compraTotal());
            System.out.println("------------------------");
            System.out.println("[1] - VOLTAR");
            resposta = Main.sc.next();
        } while (resposta.isEmpty());
    }

    public static void gerarNotaFiscal(File file) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("====================================");
            bw.newLine();
            bw.write("             NOTA FISCAL            ");
            bw.newLine();
            bw.write("Comprador: " + System.getProperty("user.name").toUpperCase());
            bw.newLine();
            bw.write("Data: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
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
            System.out.println("Erro ao criar a nota fiscal: " + e.getMessage());
        }
    }

    public static void limparCarrinho() {
        produtosCarrinho.clear();
        System.out.println("Carrinho limpo.");
    }
}

