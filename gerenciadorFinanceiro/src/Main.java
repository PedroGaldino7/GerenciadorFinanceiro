// javac -d bin src/*.java
// java -cp bin Main

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int lerIntSeguro(Scanner sc) {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                limparTela();
                System.out.println("Entrada invalida! Digite apenas numeros ou numeros inteiros.");
                System.out.println("Pressione Enter para voltar ao menu...");
                sc.nextLine();
                return -1;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GerenciadorCategoria gerenciadorCategoria = new GerenciadorCategoria();
        GerenciadorTransacao gerenciadorTransacao = new GerenciadorTransacao(gerenciadorCategoria);
        int op;

        do {
            limparTela();
            System.out.println("=== Gerenciador Financeiro ===");
            System.out.println("1. Gerenciar Categorias");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            op = lerIntSeguro(sc);

            switch (op) {
                case 1:
                    int opC;
                    do{
                        limparTela();
                        System.out.println("=== Gerenciar Categorias ===");
                        System.out.println("1. Adicionar Categoria");
                        System.out.println("2. Listar Categorias");
                        System.out.println("3. Excluir Categoria");
                        System.out.println("0. Voltar ao Menu Principal");
                        System.out.print("Escolha uma opcao: ");
                        opC = lerIntSeguro(sc);

                        switch (opC) {
                            case 1:
                                limparTela();
                                System.out.println("Digite o nome da categoria:");
                                String nome = sc.nextLine();
                                Categoria categoria = new Categoria(gerenciadorCategoria.getCategorias().size() + 1, nome);
                                gerenciadorCategoria.adicionarCategoria(categoria);
                                System.out.println("Categoria adicionada com sucesso!");
                                System.out.println("Pressione Enter para voltar ao menu...");
                                sc.nextLine();
                                break;

                            case 2:
                                limparTela();
                                gerenciadorCategoria.listarCategorias();
                                System.out.println("Pressione Enter para voltar ao menu...");
                                sc.nextLine();
                                break;

                            case 3:
                                limparTela();
                                gerenciadorCategoria.listarCategorias();

                                if (gerenciadorCategoria.getCategorias().isEmpty()) {
                                    System.out.println("Pressione enter para voltar ao menu...");
                                    sc.nextLine();
                                    break;
                                }

                                System.out.println("\nDigite o ID da categoria a ser excluida:");
                                int id = lerIntSeguro(sc);
                                gerenciadorCategoria.excluirCategoria(id);
                                sc.nextLine();
                                break;

                            case 0:
                                limparTela();
                                break;

                            default:
                                System.out.println("Opcao invalida! Pressione Enter para tentar novamente...");
                                sc.nextLine();
                        }
                    }while (opC != 0);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida! Pressione Enter para tentar novamente...");
                    sc.nextLine();
            }
        } while (op != 0);
        sc.close();
    }
}
