package poov.projeto01cadastro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import poov.projeto01cadastro.connection.ConnectionFactory;
import poov.projeto01cadastro.connection.ConnectionFactoryPostgreSQL;
import poov.projeto01cadastro.models.Filme;

public class FilmeDAO {

    ConnectionFactory c = new ConnectionFactoryPostgreSQL("localhost:5432/projeto_cadastro", "pedro", "123");
    Connection conexao = c.getConnection();
    
    private static final String FIND_FILMES = "SELECT * FROM filme WHERE codigo_login = ?";
    private static final String INSERIR_FILMES = "INSERT INTO filme(nome, ano, duracao, nota, codigo_login) VAlUES (?, ?, ?, ?, ?)";
    private static final String EXCLUIR_FILMES = "DELETE FROM filme WHERE nome = ? AND codigo_login = ?";

    public ArrayList<Filme> pesquisaFilme() throws SQLException{
        ArrayList<Filme> filmes = new ArrayList<>();
        ResultSet rs = null;
        String comando = FIND_FILMES;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setLong(1, LoginDAO.login.getCodigo());
        rs = psmt.executeQuery();

        while(rs.next()){
            Filme aux = new Filme();
            aux.setCodigo(rs.getLong(1));
            aux.setNome(rs.getString(2));
            aux.setAno(rs.getString(3));
            aux.setDuracao(rs.getString(4));
            aux.setNota(rs.getString(5));

            filmes.add(aux);
        }

        return filmes;
    }

    public void inserir_filme(Filme f) throws SQLException{
        String comando = INSERIR_FILMES;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, f.getNome());
        psmt.setString(2, f.getAno());
        psmt.setString(3, f.getDuracao());
        psmt.setString(4, f.getNota());
        psmt.setLong(5, LoginDAO.login.getCodigo());
        psmt.executeUpdate();
    }

    public void excluir_filme(Filme f) throws SQLException{
        String comando = EXCLUIR_FILMES;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, f.getNome());
        psmt.setLong(2, LoginDAO.login.getCodigo());
        psmt.executeUpdate();
    }

}
