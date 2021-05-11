package pl.edu.pw.mini.zpoif.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.project.Asteroid.Asteroid;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main extends Application {
    public Main(){}

    public static void main(String[] args) {
        launch(args);
    }

    public static LocalDate end = LocalDate.now();
    public static LocalDate start = LocalDate.now().minusDays(7);
    public static ArrayList<Asteroid> ChosenAsteroids=new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Menu/Menu.fxml")));

        stage.setTitle("NASA Asteroid Application");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Node split = scene.lookup(".split-pane");
        split.lookup(".split-pane-divider").setMouseTransparent(true);
    }
}
