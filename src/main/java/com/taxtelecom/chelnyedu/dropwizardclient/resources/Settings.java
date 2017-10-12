package com.taxtelecom.chelnyedu.dropwizardclient.resources;

import org.eclipse.jface.preference.PreferenceStore;

import java.io.IOException;

/**
 * Created by user on 12.10.17.
 */
public class Settings {
    public static final String CLIENT_TYPE = "clientType";

    private static PreferenceStore store;

    public static void initial() {
        store = new PreferenceStore("src/main/resources/app.properties");
        try {
            store.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PreferenceStore getStore () {
        return store;
    }

    public static String getClient(){
        return store.getString(CLIENT_TYPE);
    }

    public static void setClient(String value) {
        store.setValue(CLIENT_TYPE, value);
        try {
            store.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
