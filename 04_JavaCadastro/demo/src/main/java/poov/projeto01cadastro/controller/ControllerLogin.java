package poov.projeto01cadastro.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poov.projeto01cadastro.dao.LoginDAO;

public class ControllerLogin {

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField campoNome;

    @FXML
    private PasswordField campoSenha;

    //////////////////////////////////////////////////

    LoginDAO loginDAO = new LoginDAO();

    //////////////////////////////////////////////////

    @FXML
    void cadastrar(ActionEvent event) throws IOException {
        switchScene("CadastrarLogin", "Página cadastro");
    }

    public void switchScene(String file, String windowName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+ file +".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonCadastrar.getScene().getWindow(); // O QUE NÃO ESTÁ START DO App
        stage.setScene(scene);
        stage.setResizable(false);     
        stage.setTitle(windowName);
        stage.show();
    }

    public void switchScene02(String file, String windowName) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+ file +".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonLogin.getScene().getWindow(); // O QUE NÃO ESTÁ START DO App
        stage.setScene(scene);
        stage.setResizable(false);     
        stage.setTitle(windowName);
        stage.show();
    }
    

    @FXML
    void logar(ActionEvent event) throws SQLException, IOException {
        String nome = campoNome.getText();
        String senha = campoSenha.getText();

        if(nome.length() != 0 && senha.length() != 0){
            if(loginDAO.existe_nome(nome) && loginDAO.existe_senha(senha)){
                loginDAO.acessar_login(nome);
                System.out.println(LoginDAO.login.toString() + "Login feito com Sucesso!!!!");
                
                switchScene02("TelaPrincipal", "Página principal");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("O nome/senha não existe!!");
                alert.showAndWait(); 
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("O nome/senha não pode ficar vazio!!");
            alert.showAndWait(); 
        }
        // 1) Pegar o nome e a senha
        // 2) Verificar um registro na tabela Login com esse nome e senha
        // 3) Se existir, ir para a proxima janela mandando o id do registro de login para o controller da proxima janela
        // 4) Se não existir avisar que não encontrou nenhum Login com aquele nome ou senha

    }


}
