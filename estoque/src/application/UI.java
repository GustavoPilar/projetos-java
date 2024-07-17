package application;

import model.entities.Carrinho;
import model.entities.Departamento;
import model.entities.Produto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UI {
    static Set<Produto> produtos = new HashSet<>();
    static File path = new File("C:\\Users\\pilar\\OneDrive\\Documentos\\java\\projetos\\estoque\\market_products.txt");

    public static void init() throws IOException, InterruptedException {
        int escolha;
        do {
            clearScreen();
            System.out.println("\n|---------------------|");
            System.out.println("|     Bem vindo(a)    |");
            System.out.println("|---------------------|");
            System.out.println("|  [1] - Comprar      |");
            System.out.println("|  [2] - Carrinho     |");
            System.out.println("|  [3] - Gerar Nota   |");
            System.out.println("|  [4] - Sair         |");
            System.out.println("|---------------------|");
            System.out.print("Escolha: ");
            escolha = Main.sc.nextInt();
            if (escolha == 1) {
                clearScreen();
                comprar();
            }
            if (escolha == 2) {
                clearScreen();
                Carrinho.verCarrinho();
            }
            if (escolha == 3) {
                clearScreen();
                Carrinho.gerarNotaFiscal(new File(path.getParent() + "\\notaFiscal.txt"));
                break;
            }
        } while (escolha != 4);


        System.out.println("Obrigado por comprar! Volte sempre.");
    }

    public static void clearScreen() throws IOException, InterruptedException {
        if(System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
        }
    }

    public static void comprar() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                produtos.add(new Produto(fields[0].toUpperCase(), Double.valueOf(fields[1]), new Departamento(fields[2].toUpperCase()), Integer.valueOf(fields[3])));

                line = br.readLine();
            }

            int escolha;
            do {
                clearScreen();
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
                System.out.println("|  [7] - Carrinho     |");
                System.out.println("|  [8] - Voltar       |");
                System.out.println("|---------------------|");
                System.out.print("Escolha: ");
                escolha = Main.sc.nextInt();

                while (escolha < 1 || escolha > 8) {
                    System.out.print("Valor inválido, escolha outro: ");
                    escolha = Main.sc.nextInt();
                }

                if (escolha == 1) {
                    showProducts("PADARIA");
                }
                if (escolha == 2) {
                    showProducts("BEBIDAS");
                }
                if (escolha == 3) {
                    showProducts("CARNES");
                }
                if (escolha == 4) {
                    showProducts("HIGIENE");
                }
                if (escolha == 5) {
                    showProducts("FRUTAS");
                }
                if (escolha == 6) {
                    showProducts("FRIOS");
                }
                if (escolha == 7) {
                    Carrinho.verCarrinho();
                }
            } while (escolha != 8);


        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showProducts(String departamento) {
        System.out.println("\nProdutos: ");

        for (Produto p : produtos) {
            if(p.getDepartamento().getNomeDepartamento().equals(departamento)) {
                System.out.println("||" + p);
            }
        }

        System.out.print("Digite o nome do produto: ");
        Main.sc.nextLine();
        String nomeProduto = Main.sc.nextLine().toUpperCase();

        for (Produto p : produtos) {
            if(p.getNome().equals(nomeProduto)) {
                System.out.print("Quantidade: ");
                int quantidade = Main.sc.nextInt();
                Produto produto = new Produto(p.getNome(), p.getPreco(), p.getDepartamento(), quantidade);
                Carrinho.addCarrinho(produto, produto.getQuantidade());
                p.setQuantidade(p.getQuantidade() - quantidade);

                System.out.println("Adicionado ao carrinho!");
            }
        }
    }
}