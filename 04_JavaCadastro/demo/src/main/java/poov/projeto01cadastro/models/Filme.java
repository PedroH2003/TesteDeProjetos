package poov.projeto01cadastro.models;

public class Filme {
    private Long codigo;
    private String nome;
    private String ano;
    private String duracao;
    private String nota;
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    @Override
    public String toString() {
        return "Filme [codigo=" + codigo + ", nome=" + nome + ", ano=" + ano + ", duracao=" + duracao + ", nota=" + nota
                + "]";
    }
}
