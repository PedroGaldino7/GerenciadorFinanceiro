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
    private GerenciadorCategoria gerenciadorCategoria;

    public GerenciadorTransacao(GerenciadorCategoria gerenciadorCategoria) {
        this.transacoes = new ArrayList<>();
        this.gerenciadorCategoria = gerenciadorCategoria;
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
            System.out.println("Erro ao salvar transacao: " + e.getMessage());
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
            System.out.println("Erro ao carregar transacoes: " + e.getMessage());
        }
    }

    public void atualizarTransacaoEmArquivo() {
        try (FileWriter writer = new FileWriter("transacoes.txt")) {
            for (Transacao transacao : transacoes) {
                writer.write(
                    transacao.getId() + ";" +
                    transacao.getValor() + ";" +
                    transacao.getIdCategoria() + ";" +
                    transacao.getData() + ";" +
                    transacao.getTipo() + "\n"
                );
            }
        } 
        
        catch (IOException e) {
            System.out.println("Erro ao salvar transacao: " + e.getMessage());
        }
    }

    public void apagarArquivoTransacoes(){
        File file = new File("transacoes.txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo de transacoes apagado com sucesso.");
            } else {

            }
        }
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
        salvarTransacaoEmArquivo(transacao);
    }

    public boolean removerTransacao(int id){
        boolean removido = transacoes.removeIf(
            t -> t.getId() == id
        );

        if(removido){
            if (transacoes.isEmpty()) {
                apagarArquivoTransacoes();
            }else{
                atualizarTransacaoEmArquivo();
            }
        }
        return removido;
    }

    public void listarTransacoes(){

        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transacao cadastrada.");
            return;
        }

        for(Transacao transacao : transacoes){
            System.out.println(
                "Id: "+ transacao.getId() +
                ", Valor: " + transacao.getValor() +
                ", Categoria: " + buscarNomeDaCategoriaPorId(transacao.getIdCategoria()) +
                ", Data: " + transacao.getData() +
                ", Tipo: " + transacao.getTipo()
            );
        }
    }

    public String buscarNomeDaCategoriaPorId(int idCat){
        Categoria categoria = gerenciadorCategoria.buscarCategoriaPorId(idCat);

        if (categoria != null) {
            return categoria.getNome();
        } else {
            return "Categoria não encontrada";
        }
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
