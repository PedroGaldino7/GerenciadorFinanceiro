import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorCategoria {

    private List<Categoria> categorias;

    public GerenciadorCategoria() {
        this.categorias = new ArrayList<>();
        carregarCategoriasDeArquivo();
    }

    public void salvarCategoriaEmArquivo(Categoria categoria) {
        try (FileWriter writer = new FileWriter("categorias.txt", true)) {
            writer.write(
                categoria.getId() + ";" +
                categoria.getNome() + "\n"
            );
        } 
        
        catch (IOException e) {
            System.out.println("Erro ao salvar categoria: " + e.getMessage());
        }
    }

    public void carregarCategoriasDeArquivo() {
        File file = new File("categorias.txt");

        if (!file.exists() || file.length() == 0) {
            return;
        }

        categorias.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    String nome = parts[1];
                    Categoria categoria = new Categoria(id, nome);
                    categorias.add(categoria);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar categorias: " + e.getMessage());
        }
    }

    public void atualizarCategorias() {
        try (FileWriter writer = new FileWriter("categorias.txt")) {
            for (Categoria categoria : categorias) {
                writer.write(
                    categoria.getId() + ";" +
                    categoria.getNome() + "\n"
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar categorias: " + e.getMessage());
        }
    }

    public void adicionarCategoria(Categoria categoria) {
        categorias.add(categoria);
        salvarCategoriaEmArquivo(categoria);
    }

    public boolean excluirCategoria(int id) {
        boolean removido = categorias.removeIf(
            categoria -> categoria.getId() == id
        );

        if (removido) {
            if(categorias.isEmpty()) {
                apagarArquivoCategorias();
            } else {
                atualizarCategorias();
            }
        }

        System.out.println(removido ? "Categoria excluida com sucesso!" : "Categoria nao encontrada, aperte enter para continuar.");
        return removido;
    }

    public void listarCategorias() {

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;

        } 
    
        for (Categoria categoria : categorias) {
            System.out.println("Nome: " + categoria.getNome());
        }
    }

    public void listarCategoriasComIds() {

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;

        } 
    
        for (Categoria categoria : categorias) {
            System.out.println("Id: " + categoria.getId() + ", Nome: " + categoria.getNome());
        }
    }

    public Categoria buscarCategoriaPorId(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    public void apagarArquivoCategorias() {
        File file = new File("categorias.txt");

        if (file.exists()) {
            if (file.delete()) {
                
            } else {
                System.out.println("Erro ao apagar arquivo de categorias.");
            }
        }
    }


    public List<Categoria> getCategorias() {
        return categorias;
    }

}
