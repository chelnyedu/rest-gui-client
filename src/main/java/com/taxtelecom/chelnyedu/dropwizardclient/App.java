package com.taxtelecom.chelnyedu.dropwizardclient;

import com.taxtelecom.chelnyedu.dropwizardclient.factory.Factory;
import com.taxtelecom.chelnyedu.dropwizardclient.factory.InterfaceClient;
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
    static Factory factory = new Factory();
    public static InterfaceClient interfaceClient;
    private Stage primaryStage;
    private Pane rootLayout;

    public static void main(String[] args) throws IOException {
        args = new String[1];
        //args[0] = "retrofit";
        args[0] = "jersey";
        interfaceClient = factory.getClienttype(args[0]);
        launch(args);

    }

    /*private void initData() {
        PhoneBookController pk = new PhoneBookController();
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                pk.tryShowData();
            }
        });
    }*/

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;

        initRootLayout();
        //initData();
    }
    public Stage getPrimaryStage() {
        return primaryStage;
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
            //PhoneBookController pk = new PhoneBookController();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            /*primaryStage.setOnShown(EventHandler<WindowEvent>);
            /*primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    pk.initData();
                }
            });*/


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
