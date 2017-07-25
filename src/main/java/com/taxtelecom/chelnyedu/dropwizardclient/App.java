package com.taxtelecom.chelnyedu.dropwizardclient;

import com.taxtelecom.chelnyedu.dropwizardclient.guiform.PhoneBookController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by user on 18.07.17.
 */
public class App  extends Application{
    private Stage primaryStage;
    private Pane rootLayout;
    PhoneBookController pk = new PhoneBookController();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PhoneBook");

        initRootLayout();
    }
    public void initRootLayout() {
        try {

            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/PhoneBookForm.fxml"));
            rootLayout = loader.load();
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

    public static void main(String[] args) {
        //System.out.println("куку");
        launch(args);

    }


}
