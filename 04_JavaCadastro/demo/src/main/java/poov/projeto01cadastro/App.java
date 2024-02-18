package poov.projeto01cadastro;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
// import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Página Login");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icone_pipoca.png")));
        stage.show();

        // Alert alert = new Alert(Alert.AlertType.WARNING);
        // alert.setContentText("TBM FUNCIONAAAA!!!!!!!!!!!!!!!");
        // alert.showAndWait(); 
    }

    public static void main(String[] args) {
        launch();
    }
}