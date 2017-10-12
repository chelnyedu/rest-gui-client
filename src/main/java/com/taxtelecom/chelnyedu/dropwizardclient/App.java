package com.taxtelecom.chelnyedu.dropwizardclient;

import com.taxtelecom.chelnyedu.dropwizardclient.factory.Factory;
import com.taxtelecom.chelnyedu.dropwizardclient.factory.InterfaceClient;
import com.taxtelecom.chelnyedu.dropwizardclient.guiswtjframe.PhonebookSwt;
import com.taxtelecom.chelnyedu.dropwizardclient.guiswtjframe.SettingsSwt;
import com.taxtelecom.chelnyedu.dropwizardclient.resources.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Main class of application
 */
public class App{
    static Factory factory = new Factory();
    public static InterfaceClient interfaceClient;
    //private Stage primaryStage;
    //private Pane rootLayout;

    public static void main(String[] args) throws IOException {
        Settings.initial();


        //args = new String[1];
        //args[0] = "retrofit";
        //args[0] = "jersey";
        //String type = s.getSettings();
        //String type = Settings.getClient();
        interfaceClient = factory.getClienttype(Settings.getClient());


        PhonebookSwt ph = new PhonebookSwt(600, 300);
        ph.setBlockOnOpen(true);
        ph.open();
        Display.getCurrent().dispose();
        //launch(args);

    }

/*
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;

        initRootLayout();
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Initialize scene
     */
    /*public void initRootLayout() {
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
            })


        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
