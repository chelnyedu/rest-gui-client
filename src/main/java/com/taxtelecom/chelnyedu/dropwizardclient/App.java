package com.taxtelecom.chelnyedu.dropwizardclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main class of application
 */
public class App  extends Application{
    private Stage primaryStage;
    private Pane rootLayout;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        initRootLayout();
    }

    /**
     * Initialize scene
     */
    public void initRootLayout() {
        try {

            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/PhoneBookForm.fxml"));
            loader.setResources(ResourceBundle.getBundle("Locale", new Locale("ru")));
            rootLayout = loader.load();
            primaryStage.setTitle(loader.getResources().getString("PhoneBook"));
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws IOException {
        launch(args);

    }


}
