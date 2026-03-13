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
        gerenciadorCategoria.carregarCategoriasDeArquivo();
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
