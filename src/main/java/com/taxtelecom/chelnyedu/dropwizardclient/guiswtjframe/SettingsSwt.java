package com.taxtelecom.chelnyedu.dropwizardclient.guiswtjframe;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Settings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.io.IOException;
import java.util.prefs.Preferences;

import static org.eclipse.jface.preference.JFacePreferences.getPreferenceStore;

/**
 * Created by user on 11.10.17.
 */
public class SettingsSwt extends ApplicationWindow {
    PreferenceStore store;
    Preferences preferences = Preferences.userNodeForPackage(com.taxtelecom.chelnyedu.dropwizardclient.App.class);

    Button retrofitButton;
    Button jerseyButton;
    Button okButton;
    Button cancelButton;

    public SettingsSwt(Shell shell) {
        super(shell);
    }

    @Override
    protected Control createContents(Composite parent){
        parent.setSize(175,150);
        getShell().setText("Settings");
        Composite composite = new Composite(parent, SWT.NONE);

        composite.setLayout(new RowLayout(SWT.HORIZONTAL));

        Settings.setClient("jersey");

        //System.err.println("Тип клиента: " + store.getString("clientType"));
        new Label(composite, SWT.FILL).setText("Выберите клиент для \nподключения к серверу");



        retrofitButton = new Button(composite, SWT.RADIO);
        retrofitButton.setText("retrofit");

        jerseyButton = new Button(composite, SWT.RADIO);
        jerseyButton.setText("jersey");

        okButton = new Button(composite, SWT.PUSH);
        okButton.setText("OK");


        cancelButton = new Button(composite, SWT.PUSH);
        cancelButton.setText("Cancel");

        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                getShell().dispose();
            }
        });

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(retrofitButton.getSelection()){
                Settings.setClient("retrofit");
            } else { Settings.setClient("jersey"); }
        }});

    return composite;
    }

}
