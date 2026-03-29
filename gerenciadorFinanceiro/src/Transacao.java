import java.time.LocalDate;

public class Transacao {

    private final int id;
    private double valor;
    private int idCategoria;
    private LocalDate data;
    private String tipo; // "receita" ou "despesa"

    public Transacao(int id, double valor, int idCategoria, LocalDate data, String tipo) {
        this.id = id;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.data = data;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao{id=" + id + ", valor=" + valor +
                ", categoria=" + idCategoria + ", data=" + data + "}";
    }
}
