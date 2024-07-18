package application;

import model.entities.Carrinho;
import model.entities.Departamento;
import model.entities.Produto;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class UI {
    private static final Set<Produto> produtos = new HashSet<>();
    private static final String user = System.getProperty("user.name");
    private static final File path = new File(System.getProperty("user.dir") + "\\market_products.txt");

    public static void front() throws IOException, InterruptedException {
        int escolha;
        criarEstoque();

        do {
            clearScreen();
            mostrarMenu();
            escolha = Main.sc.nextInt();
            switch (escolha) {
                case 1 -> comprar();
                case 2 -> Carrinho.verCarrinho();
                case 3 -> {
                    Carrinho.gerarNotaFiscal(new File(System.getProperty("user.home") + "\\Downloads\\notaFiscal.txt"));
                    escolha = 4;
                    break;
                }
                case 4 -> {
                    System.out.println("DESEJA SAIR MESMO?\n[1] - NÃO\n[2] - SIM\n");
                    escolha = Main.sc.nextInt();
                    if (escolha == 2) {
                        escolha = 4;
                    }
                    break;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 4);


        System.out.println("OBRIGADO POR COMPRAR. VOLTE SEMPRE!");
    }

    public static void mostrarMenu() {
        System.out.println("|---------------------|");
        System.out.println("| BEM VINDO(A), " + user.toUpperCase());
        System.out.println("|---------------------|");
        System.out.println("|      MERCADINHO     |");
        System.out.println("|---------------------|");
        System.out.println("|  [1] - COMPRAR      |");
        System.out.println("|  [2] - CARRINHO     |");
        System.out.println("|  [3] - NOTA FISCAL  |");
        System.out.println("|  [4] - SAIR         |");
        System.out.println("|---------------------|");
        System.out.print("ESCOLHA: ");
    }

    public static void mostrarDepartamentos() {
        System.out.println("\n|---------------------|");
        System.out.println("|     DEPARTAMENTOS   |");
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
        System.out.print("ESCOLHA: ");
    }

    private static String getDepartamentoNome(int escolha) {
        return switch (escolha) {
            case 1 -> "PADARIA";
            case 2 -> "BEBIDAS";
            case 3 -> "CARNES";
            case 4 -> "HIGIENE";
            case 5 -> "FRUTAS";
            case 6 -> "FRIOS";
            default -> throw new IllegalArgumentException("Departamento inválido");
        };
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
                mostrarDepartamentos();
                escolha = Main.sc.nextInt();

                while (escolha < 1 || escolha > 8) {
                    System.out.print("VALOR INVÁLIDO. DIGITE NOVAMENTE: ");
                    escolha = Main.sc.nextInt();
                }
                if (escolha >= 1 && escolha <= 7) {
                    if(escolha == 7) {
                        clearScreen();
                        Carrinho.verCarrinho();
                    }
                    else {
                        String departamento = getDepartamentoNome(escolha);
                        clearScreen();
                        showProducts(departamento);
                    }
                } else if (escolha != 8) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } while (escolha != 8);


        }
        catch (Exception e) {
            System.out.println("ERRO AO LER O ARQUIVO: " + e.getMessage());
        }
    }

    public static void showProducts(String departamento) throws IOException, InterruptedException {
        Set<Produto> temp = new TreeSet<>(produtos);
        temp.removeIf(x -> !Objects.equals(x.getDepartamento().getNomeDepartamento(), departamento) || x.getQuantidade() == 0);
        Produto[] produtosTemp = new Produto[temp.size()];

        System.out.println("\nPRODUTOS DE " + departamento + ":");
        System.out.println("[0] - VOLTAR\n");
        int i = 0;
        for(Produto p : temp) {
            produtosTemp[i] = p;
            System.out.println("[" + (i+1) + "] - " + p);
            i++;
        }

        System.out.print("DIGITE O CÓDIGO DO PRODUTO: ");
        Main.sc.nextLine();
        int codeProduto = Main.sc.nextInt();

        while(!(codeProduto >= 0 && codeProduto <= produtosTemp.length)) {
            System.out.println("VALOR INVÁLIDO. DIGITE NOVAMENTE: ");
            codeProduto = Main.sc.nextInt();
        }

        if(codeProduto == 0) {
            return;
        }

        clearScreen();
        Produto produtoEscolhido = produtosTemp[codeProduto-1];
        System.out.println("\nPODUTO DE " + departamento + ":");
        System.out.println("[0] - VOLTAR\n");
        System.out.println("|| " + produtoEscolhido);


        System.out.print("DIGITE A QUANTIDADE DE " + produtoEscolhido.getNome() + ": ");
        int quantidade = Main.sc.nextInt();
        while(!(quantidade >= 0 && quantidade <= produtoEscolhido.getQuantidade())) {
            System.out.println("QUANTIDADE INVÁLIDA. DIGITE NOVAMENTE: ");
            quantidade = Main.sc.nextInt();
        }

        if(quantidade > 0) {
            Produto produto = new Produto(produtoEscolhido.getNome(), produtoEscolhido.getPreco(), produtoEscolhido.getDepartamento(), quantidade);
            Carrinho.addCarrinho(produto, quantidade);
            produtoEscolhido.setQuantidade(produtoEscolhido.getQuantidade() - quantidade);
            System.out.println("ADICIONADO AO CARRINHO!");
            Thread.sleep(1000);
        }
    }

    public static void criarEstoque() {
        File outputFile = procurarArquivo(new File(System.getProperty("user.home") + "\\OneDrive\\Documetos"), "\\estoqueProdutos.txt");
        if (outputFile == null) {
            outputFile = new File(System.getProperty("user.home") + "\\Downloads\\estoqueProdutos.txt");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            String line = br.readLine();
            while (line != null) {
                bw.write(line);
                bw.newLine();

                line = br.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static File procurarArquivo(File dir, String fileNameToFind) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        File found = procurarArquivo(file, fileNameToFind);
                        if (found != null) {
                            return found;
                        }
                    } else if (fileNameToFind.equals(file.getName())) {
                        return file;
                    }
                }
            }
        }
        return null;
    }
}