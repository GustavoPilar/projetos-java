package application;

import model.entities.Carrinho;
import model.entities.Departamento;
import model.entities.Produto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class UI {
    static Set<Produto> produtos = new HashSet<>();
    static File path;
    public static String user = System.getProperty("user.name");

    public static void init() throws IOException, InterruptedException {
        clearScreen();
        System.out.println("QUAL O CAMINHO (PATH) DO DOCUMENTO? ");
        System.out.println("EXEMPLO DE CAMINHO: C:\\temp\\arquivo.txt");
        System.out.println("O ARQUIVO DEVERÁ SER DO FORMATO .txt (TEXTO) E DO TIPO CSV (VALORES SEPARADOS POR VÍRGULA.");
        System.out.println("EXEMPLO DE DADOS DO ARQUIVO: Nome do produto,Preço,Departamento,Quantidade em estoque");
        System.out.println("EXEMPLO DE COMO DEVE SER O ARQUIVO: \n" +
                "Pão Francês,0,99,Padaria,33\n" +
                "Refrigerante Coca-Cola,7,99,Bebidas,95\n" +
                "(...)");
        System.out.println("REPARE QUE NÃO DEVERÁ CONTER ESPAÇOS ENTRE AS VÍGULAS COM AS PALAVRAS.");
        System.out.print("CAMINHO: ");
        path = new File(Main.sc.next());
        front();
    }

    public static void front() throws IOException, InterruptedException {
        int escolha;
        do {
            clearScreen();
            System.out.println("BEM VINDO, " + user);
            System.out.println("\n|---------------------|");
            System.out.println("|      MERCADINHO     |");
            System.out.println("|---------------------|");
            System.out.println("|  [1] - COMPRAR      |");
            System.out.println("|  [2] - CARRINHO     |");
            System.out.println("|  [3] - NOTA FISCAL  |");
            System.out.println("|  [4] - SAIR         |");
            System.out.println("|---------------------|");
            System.out.print("ESCOLHA: ");
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
                Carrinho.gerarNotaFiscal(new File("C:\\Users\\" + user + "\\Downloads\\notaFiscal.txt"));
                break;
            }
        } while (escolha != 4);


        System.out.println("OBRIGADO POR COMPRAR. VOLTE SEMPRE!");
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
                System.out.println("|     DEPARTAMENTO    |");
                System.out.println("|---------------------|");
                System.out.println("|  [1] - PADARIA      |");
                System.out.println("|  [2] - BEBIDAS      |");
                System.out.println("|  [3] - CARNES       |");
                System.out.println("|  [4] - HIGIENE      |");
                System.out.println("|  [5] - FRUTAS       |");
                System.out.println("|  [6] - FRIOS        |");
                System.out.println("|---------------------|");
                System.out.println("|  [7] - CARRINHO     |");
                System.out.println("|  [8] - VOLTAR       |");
                System.out.println("|---------------------|");
                System.out.print("ESOLHA: ");
                escolha = Main.sc.nextInt();

                while (escolha < 1 || escolha > 8) {
                    System.out.print("VALOR INVÁLIDO. DIGITE NOVAMENTE: ");
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
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void showProducts(String departamento) throws IOException, InterruptedException {
        Set<Produto> temp = new TreeSet<>(produtos);
        temp.removeIf(x -> !Objects.equals(x.getDepartamento().getNomeDepartamento(), departamento));
        Produto[] produtosTemp = new Produto[temp.size()];

        System.out.println("\nPRODUTOS DE " + departamento + ":");
        int i = 0;
        for(Produto p : temp) {
            if(p.getQuantidade() == 0) {
                temp.remove(p);
            }
            produtosTemp[i] = p;
            System.out.println("[" + (i+1) + "] - " + p);
            i++;
        }

        System.out.print("DIGITE O CÓDIGO DO PRODUTO: ");
        Main.sc.nextLine();
        int codeProduto = Main.sc.nextInt();

        while(!(codeProduto > 0 && codeProduto < produtosTemp.length)) {
            System.out.println("VALOR INVÁLIDO. DIGITE NOVAMENTE: ");
            codeProduto = Main.sc.nextInt();
        }

        clearScreen();
        System.out.println("\nPODUTO DE " + departamento + ":");
        System.out.println("|| " + produtosTemp[codeProduto-1]);

        for (Produto p : produtos) {
            if(p.getNome().equals(produtosTemp[codeProduto-1].getNome())) {
                System.out.print("DIGITE A QUANTIDADE DE " + p.getNome() + ": ");
                int quantidade = Main.sc.nextInt();
                while(!(quantidade > 0 && quantidade <= p.getQuantidade())) {
                    System.out.println("QUANTIDADE INVÁLIDA. DIGITE NOVAMENTE: ");
                    quantidade = Main.sc.nextInt();
                }

                Produto produto = new Produto(p.getNome(), p.getPreco(), p.getDepartamento(), quantidade);
                Carrinho.addCarrinho(produto, produto.getQuantidade());
                p.setQuantidade(p.getQuantidade() - quantidade);

                System.out.println("ADICIONADO AO CARRINHO!");
                Thread.sleep(1000);
            }
        }
    }
}