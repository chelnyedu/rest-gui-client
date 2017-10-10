package com.taxtelecom.chelnyedu.dropwizardclient.guiswtjframe;

import com.taxtelecom.chelnyedu.dropwizardclient.resources.Contact;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import java.io.IOException;

import static com.taxtelecom.chelnyedu.dropwizardclient.App.interfaceClient;


/**
 * Created by user on 03.10.17.
 */
public class PhonebookSwt extends ApplicationWindow {
    Text firstNameField;
    Text lastNameField;
    Text phoneField;
    Text mailField;
    Text commentField;
    private Button settingsButton;
    private final int h;
    private final int w;



    public PhonebookSwt(int w, int h) {
        super(null);
        this.h = h;
        this.w = w;
    }

    protected Control createContents(Composite parent) {
        //getShell().setMinimumSize(h,w);
        Composite composite = new Composite(parent, SWT.BORDER);

        getShell().setText("Phonebook");
        composite.setLayout(new GridLayout(2, false));

        //1st column
        TableViewer tableContactViewer = new TableViewer(composite, SWT.BORDER);
        GridData gridTableData = new GridData(GridData.FILL_BOTH);
        gridTableData.verticalSpan = 2;
        tableContactViewer.getControl().setLayoutData(gridTableData);
        final Table table = tableContactViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setSize(300,300);
        tableContactViewer.setContentProvider(new ArrayContentProvider());
        final TableViewerColumn firstNameColumn = new TableViewerColumn(tableContactViewer, SWT.NONE);
        firstNameColumn.getColumn().setText("First Name");
        firstNameColumn.getColumn().setWidth(150);
        firstNameColumn.setLabelProvider(new ColumnLabelProvider() {
                                             @Override
                                             public String getText(Object element) {
                                                 Contact contact = (Contact) element;
                                                 return contact.getFirstName();
                                             }
                                         });
        final TableViewerColumn lastNameColumn = new TableViewerColumn(tableContactViewer, SWT.NONE);
        lastNameColumn.getColumn().setText("Last Name");
        lastNameColumn.getColumn().setWidth(150);
        lastNameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Contact contact = (Contact) element;
                return contact.getLastName();
            }
        });

        //2nd column
        Group group = new Group(composite,SWT.SHADOW_ETCHED_IN );
        group.setText("Person's details");
        group.setLayout(new GridLayout(2, false));
        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL, GridData.VERTICAL_ALIGN_END, true, true);
        group.setLayoutData(gridData);
        new Label(group, SWT.NONE).setText("First Name");
        firstNameField = new Text(group, SWT.SINGLE | SWT.BORDER);
        firstNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        new Label(group, SWT.NONE).setText("Last Name");
        lastNameField = new Text(group, SWT.SINGLE | SWT.BORDER );
        lastNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        new Label(group, SWT.NONE).setText("Phone");
        phoneField = new Text(group, SWT.SINGLE | SWT.BORDER );
        phoneField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        new Label(group, SWT.NONE).setText("E-mail");
        mailField = new Text(group, SWT.SINGLE | SWT.BORDER );
        mailField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        new Label(group, SWT.NONE).setText("Comment");
        commentField = new Text(group, SWT.SINGLE | SWT.BORDER );
        commentField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        group.pack();

        Group buttonGroup = new Group(composite, SWT.SHADOW_NONE);
        buttonGroup.setLayout(new RowLayout());
        group.setLayoutData(new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_END, false, true));

        final Button addButton = new Button(buttonGroup, SWT.PUSH);
        addButton.setText("Add");
        final Button updateButton = new Button(buttonGroup, SWT.PUSH);
        updateButton.setText("Update");
        final Button deleteButton = new Button(buttonGroup, SWT.PUSH);
        deleteButton.setText("Delete");
        settingsButton = new Button(buttonGroup, SWT.PUSH);
        settingsButton.setText("Settings");

        tableContactViewer.addSelectionChangedListener(selectionChangedEvent -> {
        IStructuredSelection selection;
            selection = (IStructuredSelection) tableContactViewer.getSelection();
            Object firstElement = selection.getFirstElement();
        Contact contact = (Contact) firstElement;
        showContactDetails(contact);
        });


        //initData
        initData(tableContactViewer);
        //action add button
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (isValidContact()){
                    Contact newContact = new Contact(1, firstNameField.getText(), lastNameField.getText(),
                            phoneField.getText(), mailField.getText(), commentField.getText());
                    addContact(newContact);
                    initData(tableContactViewer);
                }
            }
        });


        deleteButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Contact contact = (Contact)((IStructuredSelection) tableContactViewer.getSelection()).getFirstElement();
                deleteContact(contact.getId());
                initData(tableContactViewer);
            }
        });

        updateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection;
                selection = (IStructuredSelection) tableContactViewer.getSelection();
                Contact c = (Contact) selection.getFirstElement();
                if (isValidContact()){
                    Contact contactForUpdate = new Contact(c.getId(),firstNameField.getText(), lastNameField.getText(),
                            phoneField.getText(), mailField.getText(), commentField.getText() );
                    updateContact(contactForUpdate);}
                initData(tableContactViewer);
            }
        });

        //todo add action for settings

        buttonGroup.pack();

        return composite;
    }

    /**
     * fill tableviwer
     */
    private void initData(TableViewer tableContactViewer) {
        try {
            tableContactViewer.setInput(interfaceClient.getListContact());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fill TextFields
     * @param contact
     */
    private void showContactDetails(Contact contact) {
        if (contact != null){
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneField.setText(contact.getPhone());
        String mail = contact.getMail();
        mailField.setText(mail != null ? mail : "");
        String comment = contact.getComment();
        commentField.setText(comment != null ? comment : "");
        }else {
            firstNameField.setText("");
            lastNameField.setText("");
            phoneField.setText("");
            mailField.setText("");
            commentField.setText("");
        }
        }

    /**
     * Validation of TextField
     */
    private boolean isValidContact(){
        if (firstNameField.getText().length() < 2 ||
                lastNameField.getText().length() <  2
                || phoneField.getText().matches("^[a-zA-Z]+$")
                || phoneField.getText().length()<2 || mailField.getText().length() <2){
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING);
            messageBox.setText("Oops!");
            messageBox.setMessage("This contact isn't valid");
            messageBox.open();


           // myAlert.validationAlert();
            return false;
        }else return true;
    }

    /**
     * Method of adding contact.
     */
    private void addContact(Contact newContact){
        try {
            interfaceClient.createContact(newContact);
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION);
            messageBox.setMessage("Contact was created!");
            messageBox.setText("Success");
            messageBox.open();
        }catch (IOException e){
            String s = "adding new contact!";
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING);
            messageBox.setMessage("Error in" + s + e.toString());
            messageBox.setText("Error");
            messageBox.open();

        }


    }

    /**
     * Method of deleting contact.
     */
    private void deleteContact(int index){
        try {
            interfaceClient.deleteContact(index);
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION);
            messageBox.setMessage("Contact was deleted!");
            messageBox.setText("Success");
            messageBox.open();

        } catch (IOException e) {
            String s = "deleting";
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING);
            messageBox.setMessage("Error in" + s + e.toString());
            messageBox.setText("Error");
            messageBox.open();
        }

    }

    /**
     * Method of updating contact.
     */
    private void updateContact(Contact contact){
        try {
            interfaceClient.updateContact(contact);
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION);
            messageBox.setMessage("Contact was updated!");
            messageBox.setText("Success");
            messageBox.open();
            //myAlert.successAlert("updated!");
        } catch (IOException e) {
            String s = "updating contact!";
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WARNING);
            messageBox.setMessage("Error in" + s + e.toString());
            messageBox.setText("Error");
            messageBox.open();
        }

    }



}
