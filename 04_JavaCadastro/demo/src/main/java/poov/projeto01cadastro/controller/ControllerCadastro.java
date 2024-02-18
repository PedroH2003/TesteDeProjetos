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

public class ControllerCadastro {

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField campoNome;

    @FXML
    private PasswordField campoSenha;

    ////////////////////////////////////////////////////

    LoginDAO loginDAO = new LoginDAO();

    ///////////////////////////////////////////////////

    @FXML
    void cadastrar(ActionEvent event) throws SQLException, IOException {
        String nome = campoNome.getText();
        String senha = campoSenha.getText();

        if(nome.length() != 0 && senha.length() != 0){
            if(loginDAO.existe_nome(nome)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Esse nome de usuário já existe!!\nEscolha outro.");
                alert.showAndWait(); 
            }
            else{
                loginDAO.cadastrar_login(nome, senha);
                switchScene("Login", "Página Login");
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("O nome/senha não podem ficar vazios!!");
            alert.showAndWait();             
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        switchScene("Login", "Página Login");
    }

    public void switchScene(String file, String windowName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+ file +".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonVoltar.getScene().getWindow(); // O QUE NÃO ESTÁ START DO App
        stage.setScene(scene);
        stage.setResizable(false);     
        stage.setTitle(windowName);
        stage.show();
    }

}
