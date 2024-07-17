package application;

import model.entities.Carrinho;
import model.entities.Departamento;
import model.entities.Produto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
                    clearScreen();
                    showProducts("PADARIA");
                }
                if (escolha == 2) {
                    clearScreen();
                    showProducts("BEBIDAS");
                }
                if (escolha == 3) {
                    clearScreen();
                    showProducts("CARNES");
                }
                if (escolha == 4) {
                    clearScreen();
                    showProducts("HIGIENE");
                }
                if (escolha == 5) {
                    clearScreen();
                    showProducts("FRUTAS");
                }
                if (escolha == 6) {
                    clearScreen();
                    showProducts("FRIOS");
                }
                if (escolha == 7) {
                    clearScreen();
                    Carrinho.verCarrinho();
                }
            } while (escolha != 8);


        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showProducts(String departamento) throws IOException, InterruptedException {
        Set<Produto> temp = new TreeSet<>(produtos);
        temp.removeIf(x -> !Objects.equals(x.getDepartamento().getNomeDepartamento(), departamento));
        Produto[] produtosTemp = new Produto[temp.size()];

        System.out.println("\nProdutos de " + departamento.toUpperCase() + ":");
        int i = 0;
        for(Produto p : temp) {
            produtosTemp[i] = p;
            System.out.println("[" + (i+1) + "] - " + p);
            i++;
        }

        System.out.print("Digite o codigo do produto: ");
        Main.sc.nextLine();
        int codeProduto = Main.sc.nextInt();

        while(!(codeProduto > 0 && codeProduto < produtosTemp.length)) {
            System.out.println("Valor inválido, digite novamente: ");
            codeProduto = Main.sc.nextInt();
        }

        clearScreen();
        System.out.println("\nProduto de " + departamento.toUpperCase() + ":");
        System.out.println("|| " + produtosTemp[codeProduto-1]);

        i = 0;
        for (Produto p : produtos) {
            if(p.getNome().equals(produtosTemp[codeProduto-1].getNome())) {
                System.out.print("Digite a quantidade de " + p.getNome());
                int quantidade = Main.sc.nextInt();
                Produto produto = new Produto(p.getNome(), p.getPreco(), p.getDepartamento(), quantidade);
                Carrinho.addCarrinho(produto, produto.getQuantidade());
                p.setQuantidade(p.getQuantidade() - quantidade);

                System.out.println("Adicionado ao carrinho!");
                Thread.sleep(1000);
            }
            i++;
        }
    }
}