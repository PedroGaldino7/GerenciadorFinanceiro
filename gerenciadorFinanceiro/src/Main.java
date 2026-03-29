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

    public static double lerDoubleSeguro(Scanner sc) {
        while (true) {
            try {
                double valor = sc.nextDouble();
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
            System.out.println("2. Gerenciar Transacoes");
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
                                gerenciadorCategoria.listarCategoriasComIds();

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

                case 2:
                    limparTela();
                    int opT;
                    System.out.println("=== Gerenciar Transacoes ===");
                    System.out.println("1. Adicionar Transacao");
                    System.out.println("2. Listar Transacoes");
                    System.out.println("0. Voltar ao Menu Principal");
                    System.out.print("Escolha uma opcao: ");
                    opT = lerIntSeguro(sc);

                    switch (opT) {
                        case 1:
                            limparTela();
                            System.out.println("=== Adicionar Transacao ===");
                            System.out.println("Escolha o tipo da transacao:");
                            System.out.println("1. Receita");
                            System.out.println("2. Despesa");
                            int tipo = lerIntSeguro(sc);
                            String tipoEscolhido = "";

                                if (tipo == 1) {
                                    tipoEscolhido = "Receita";
                                } else if (tipo == 2) {
                                    tipoEscolhido = "Despesa";
                                }else {
                                    System.out.println("Tipo invalido! Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                }
                            
                            limparTela();
                            System.out.println("Escolha a categoria:");
                            gerenciadorCategoria.listarCategoriasComIds();

                                if (gerenciadorCategoria.getCategorias().isEmpty()) {
                                    System.out.println("Pressione enter para voltar ao menu...");
                                    sc.nextLine();
                                    break;
                                }

                            int idCategoria = lerIntSeguro(sc);
                            Categoria categoriaEscolhida = gerenciadorCategoria.buscarCategoriaPorId(idCategoria);

                                if (categoriaEscolhida == null) {
                                    System.out.println("Categoria nao encontrada! Pressione Enter para continuar...");
                                    sc.nextLine();
                                    break;
                                }

                            limparTela();
                            System.out.println("digite o valor da transacao:");
                            double valor = lerDoubleSeguro(sc);

                            if (valor < 0) {
                                System.out.println("Valor invalido! Pressione Enter para tentar novamente...");
                                sc.nextLine();
                                break;
                            }

                            Transacao transacao = new Transacao(
                                gerenciadorTransacao.getTransacoes().size() + 1,
                                valor,
                                idCategoria,
                                java.time.LocalDate.now(),
                                tipoEscolhido
                            );
                            gerenciadorTransacao.adicionarTransacao(transacao);
                            System.out.println("Transacao adicionada com sucesso!");
                            System.out.println("Pressione Enter para voltar ao menu...");
                            sc.nextLine();
                            break;

                        case 2:
                            limparTela();
                            gerenciadorTransacao.listarTransacoes();
                            System.out.println("Pressione Enter para voltar ao menu...");
                            sc.nextLine();
                            break;
                        }

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida! Pressione Enter para tentar novamente...");
                    sc.nextLine();
            }
        } while (op != 0);
        limparTela();
        sc.close();
    }
}
