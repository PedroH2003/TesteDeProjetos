package poov.projeto01cadastro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import poov.projeto01cadastro.connection.ConnectionFactory;
import poov.projeto01cadastro.connection.ConnectionFactoryPostgreSQL;
import poov.projeto01cadastro.models.Login;

public class LoginDAO {

    ConnectionFactory c = new ConnectionFactoryPostgreSQL("localhost:5432/projeto_cadastro", "pedro", "123");
    Connection conexao = c.getConnection();

    public static Login login = new Login();

    private static final String FIND_NAME = "SELECT * FROM login WHERE nome = ?";
    private static final String FIND_SENHA = "SELECT * FROM login WHERE senha = ?";
    private static final String NEW_LOGIN = "INSERT INTO login (nome, senha) VALUES (?, ?)";

    public boolean existe_nome(String nome) throws SQLException{
        boolean ans = false;

        ResultSet rs = null;
        String comando = FIND_NAME;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, nome);
        rs = psmt.executeQuery();
        ans = rs.next();

        psmt.close();
        rs.close();

        return ans;
    }

    public boolean existe_senha(String senha) throws SQLException{
        boolean ans = false;

        ResultSet rs = null;
        String comando = FIND_SENHA;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, senha);
        rs = psmt.executeQuery();
        ans = rs.next();

        psmt.close();
        rs.close();

        return ans;
    }

    public void acessar_login(String nome) throws SQLException{
        ResultSet rs = null;
        String comando = FIND_NAME;

        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, nome);
        rs = psmt.executeQuery();
        rs.next();
        
        login.setCodigo(rs.getLong(1));
        login.setNome(rs.getString(2));
        login.setSenha(rs.getString(3));

        psmt.close();
        rs.close();
    }

    public void cadastrar_login(String nome, String senha) throws SQLException{
        String comando = NEW_LOGIN;
        PreparedStatement psmt = conexao.prepareStatement(comando);
        psmt.setString(1, nome);
        psmt.setString(2, senha);
        int num = psmt.executeUpdate();
        
        if(num > 0){
            System.err.println("CADASTRO FEITO COM SUCESSO!!");
        }
        psmt.close();
    }
    
}
