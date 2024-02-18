package poov.projeto01cadastro.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryMySQL implements ConnectionFactory {
    
    private String dbURL;
    private String user;
    private String password;
    private static final String classeDriver = "com.mysql.cj.jdbc.Driver";
    private static final String caminho = "jdbc:mysql";
    
    /**
     * 
     * @param dbURL     localhost:5432/projeto_cadastro
     * @param user      pedro
     * @param password  123
     */
    public ConnectionFactoryMySQL(String dbURL, String user, String password) {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        String url = caminho + "://" + dbURL;
        Connection conexao = null;
        try {
            System.out.println("Conectando com o banco de dados.");
            Class.forName(classeDriver);
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com o banco de dados estabelecida.");
            return conexao;
        } catch (Exception e) {
            System.out.println("Erro obtendo uma conexão com o banco de dados.");
            e.printStackTrace();
            return null;
        }
    }

}