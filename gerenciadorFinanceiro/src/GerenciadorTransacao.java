import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GerenciadorTransacao {
    
    private List<Transacao> transacoes;

    public GerenciadorTransacao() {
        this.transacoes = new ArrayList<>();
        carregarTransacoesDeArquivo();
    }

    public void salvarTransacaoEmArquivo(Transacao transacao) {
        try (FileWriter writer = new FileWriter("transacoes.txt", true)) {
            writer.write(
                transacao.getId() + ";" +
                transacao.getValor() + ";" +
                transacao.getIdCategoria() + ";" +
                transacao.getData() + ";" +
                transacao.getTipo() + "\n"
            );
        } 
        
        catch (IOException e) {
            System.out.println("Erro ao salvar transação: " + e.getMessage());
        }
    }

    public void carregarTransacoesDeArquivo() {
        File file = new File("transacoes.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                int valor = Integer.parseInt(parts[1]);
                int idCategoria = Integer.parseInt(parts[2]);
                LocalDate data = LocalDate.parse(parts[3]);
                String tipo = parts[4];
                Transacao transacao = new Transacao(id, valor, idCategoria, data, tipo);
                transacoes.add(transacao);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar transações: " + e.getMessage());
        }
    }

    public void atualizarTransacaoEmArquivo(Transacao transacao) {
        try (FileWriter writer = new FileWriter("transacoes.txt")) {
            writer.write(
                transacao.getId() + ";" +
                transacao.getValor() + ";" +
                transacao.getData() + ";" +
                transacao.getIdCategoria() + ";" +
                transacao.getTipo() + "\n"
            );
        } 
        
        catch (IOException e) {
            System.out.println("Erro ao salvar transação: " + e.getMessage());
        }
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        salvarTransacaoEmArquivo(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
