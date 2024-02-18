package poov.projeto01cadastro.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poov.projeto01cadastro.dao.FilmeDAO;
import poov.projeto01cadastro.models.Filme;
import poov.projeto01cadastro.selenium.FilmeIMDB;

public class Controller_TelaPrincipal implements Initializable{

    @FXML
    private TableColumn<Filme, String> ano;

    @FXML
    private Button buttonFilme;

    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField campoFilme;

    @FXML
    private TableColumn<Filme, String> duracao;

    @FXML
    private TableColumn<Filme, String> nome;

    @FXML
    private TableColumn<Filme, String> nota;

    @FXML
    private TableView<Filme> tabelaFilme;

    ObservableList<Filme> listaFilme = FXCollections.observableArrayList();
    FilmeDAO filmeDAO = new FilmeDAO();
    ArrayList<Filme> filmes;
    Filme registro_filme;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            filmes = filmeDAO.pesquisaFilme();

            for(Filme u: filmes){
                listaFilme.add(u);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        nome.setCellValueFactory(new PropertyValueFactory<Filme, String>("nome"));
        ano.setCellValueFactory(new PropertyValueFactory<Filme, String>("ano"));
        duracao.setCellValueFactory(new PropertyValueFactory<Filme, String>("duracao"));
        nota.setCellValueFactory(new PropertyValueFactory<Filme, String>("nota"));

        tabelaFilme.setItems(listaFilme);
    }

    public void atualizar_filme(){
        listaFilme.clear();

        for(Filme u: filmes){
            listaFilme.add(u);
        }

        nome.setCellValueFactory(new PropertyValueFactory<Filme, String>("nome"));
        ano.setCellValueFactory(new PropertyValueFactory<Filme, String>("ano"));
        duracao.setCellValueFactory(new PropertyValueFactory<Filme, String>("duracao"));
        nota.setCellValueFactory(new PropertyValueFactory<Filme, String>("nota"));        

        tabelaFilme.setItems(listaFilme);
    }   

    @FXML
    void inserir_filme(ActionEvent event) throws SQLException {
        String nome_f = campoFilme.getText();

        
        if(nome_f.length() != 0){
            FilmeIMDB aux = new FilmeIMDB(nome_f);
            filmes.add(aux.f);
            atualizar_filme(); // atualizando a tabela de filmes da janela
            filmeDAO.inserir_filme(aux.f); // inserindo filme no banco de dados
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Você não digitou um nome para o filme!!");
            alert.showAndWait(); 
        }
        
    }

    @FXML
    void voltar_login(ActionEvent event) throws IOException {
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

    @FXML
    void filmeClicked(MouseEvent event) throws SQLException {

        if(event.getClickCount() == 2){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");    
            alert.setHeaderText("Excluir Filme");
            alert.setContentText("Tem certeza que deseja excluir o filme?");
                        
            ButtonType buttonTypeOK = new ButtonType("OK");
            ButtonType buttonTypeCancel = new ButtonType("Cancelar");
                        
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
                        
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOK) {
                // Aqui você pode adicionar a lógica para excluir o filme
                int i = tabelaFilme.getSelectionModel().getSelectedIndex();
                registro_filme = (Filme)tabelaFilme.getItems().get(i);
                filmeDAO.excluir_filme(registro_filme);
                filmes.clear();
                filmes = filmeDAO.pesquisaFilme();
                atualizar_filme();
                System.out.println("Filme excluído");

            } else {
                // Usuário clicou em Cancelar ou fechou o diálogo
                System.out.println("Exclusão cancelada");
            }
        }      
    }
}
