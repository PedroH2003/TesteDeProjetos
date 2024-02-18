package poov.projeto01cadastro.models;

public class Login {
    private Long codigo;
    private String nome;
    private String senha;

    
    public Login() {
    }

    public Login(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Login(Long codigo, String nome, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
    }


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


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Login [id=" + codigo + ", nome=" + nome + ", senha=" + senha + "]";
    }

}
