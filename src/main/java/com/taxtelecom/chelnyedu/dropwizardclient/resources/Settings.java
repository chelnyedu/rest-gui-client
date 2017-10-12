package com.taxtelecom.chelnyedu.dropwizardclient.resources;

import org.eclipse.jface.preference.PreferenceStore;

/**
 * Created by user on 12.10.17.
 */
public class Settings {
    private static PreferenceStore store;

    public static void initial() {
        store = new PreferenceStore("/resources/clientType.propeties");
    }

    public static PreferenceStore getStore () {
        return store;
    }

}
