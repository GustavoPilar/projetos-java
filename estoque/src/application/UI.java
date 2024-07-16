package application;

import model.entities.Carrinho;
import model.entities.Departamento;
import model.entities.Produto;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UI {
    static Set<Produto> produtos = new HashSet<>();

    public static void init() {
        int escolha;
        System.out.println("|---------------------|");
        System.out.println("|     Bem vindo(a)    |");
        System.out.println("|---------------------|");
        System.out.println("|  [1] - Comprar      |");
        System.out.println("|  [2] - Gerar Nota   |");
        System.out.println("|  [3] - Sair         |");
        System.out.println("|---------------------|");
        System.out.print("Escolha: ");
        escolha = Main.sc.nextInt();
        if (escolha == 1) {
            comprar();
        }
        if (escolha == 2) {
            Carrinho.gerarNotaFiscal(new File("C:\\Users\\pilar\\OneDrive\\Documentos\\java\\projetos\\estoque\\notaFiscal.txt"));
            System.out.println("Nota fiscal gerada!");
            return;
        }


        System.out.println("Obrigado por comprar! Volte sempre.");
    }

    public static void comprar() {
        File path = new File("C:\\Users\\pilar\\OneDrive\\Documentos\\java\\projetos\\estoque\\products_with_departments.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                int quantidade = 1;
                Departamento departamento = new Departamento(fields[3]);
                Produto produto = new Produto(fields[0], Double.valueOf(fields[1]), LocalDate.parse(fields[2], fmt), departamento, quantidade);
                if (!produtos.contains(produto)) {
                    produtos.add(produto);
                } else {
                    for (Produto p : produtos) {
                        if (p.equals(produto)) {
                            p.setQuantidade(p.getQuantidade() + 1);
                        }
                    }
                }

                line = br.readLine();
            }

            System.out.println("\n|---------------------|");
            System.out.println("|     Departamento    |");
            System.out.println("|---------------------|");
            System.out.println("|  [1] - Padaria      |");
            System.out.println("|  [2] - bebidas      |");
            System.out.println("|  [3] - carnes       |");
            System.out.println("|  [4] - higiene      |");
            System.out.println("|  [5] - frutas       |");
            System.out.println("|  [6] - frios        |");
            System.out.println("|---------------------|");
            System.out.print("Escolha: ");
            int escolha = Main.sc.nextInt();

            while (escolha < 1 && escolha > 6) {
                System.out.print("Valor inválido, escolha outro: ");
                escolha = Main.sc.nextInt();
            }

            if (escolha == 1) {
                showProducts("padaria");
            }
            if (escolha == 2) {
                showProducts("bebidas");
            }
            if (escolha == 3) {
                showProducts("carnes");
            }
            if (escolha == 4) {
                showProducts("higiene");
            }
            if (escolha == 5) {
                showProducts("frutas");
            }
            if (escolha == 6) {
                showProducts("frios");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showProducts(String departamento) {
        System.out.println("\nProdutos: ");
        Set<Produto> temp = new TreeSet<>(produtos);
        temp.removeIf(x -> !x.getDepartamento().getNomeDepartamento().equals(departamento));
        int num = 1;
        for(Produto p : temp) {
            System.out.println("[" + num + "] - " + p);
            num++;
        }

        System.out.print("Digite o nome do produto: ");
        Main.sc.nextLine();
        String nomeProduto = Main.sc.nextLine();

        System.out.print("Qual a quantidade desejada? ");
        int quantidade = Main.sc.nextInt();

        for(Produto p : temp) {
            if(p.getNome().equals(nomeProduto)) {
                while (quantidade < 0 || quantidade < p.getQuantidade()) {
                    System.out.println("quantidade inválida. Digite novamente. ");
                    quantidade = Main.sc.nextInt();
                }
                p.setQuantidade(quantidade);
                Carrinho.addCarrinho(p, quantidade);
                System.out.println("Adicionado ao carrinho.");
            }
        }
    }
}