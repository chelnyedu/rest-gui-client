package com.taxtelecom.chelnyedu.dropwizardclient.guiswtjframe;


import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;



/**
 * Created by user on 03.10.17.
 */
public class PhonebookSwt extends ApplicationWindow {
    GridLayout layout = new GridLayout(2, false);
    Label firstNameLabel, lastNameLabel, phoneLabel, mailLabel, commentLabel;
    Text firstNameField, lastNameField, phoneField, mailField, commentField;
    Button addButton, updateButton, deleteButton, settingsButton;
    private final int h;
    private final int w;




    public PhonebookSwt(int w, int h) {
        super(null);
        this.h = h;
        this.w = w;
    }

    protected Control createContents(Composite parent) {
        getShell().setMinimumSize(h,w);
        Composite composite = new Composite(parent, SWT.BORDER);

        getShell().setText("Widget Window");
        composite.setLayout(layout);

        //1st row
        TableViewer tableContactViewer = new TableViewer(composite, SWT.BORDER);
        GridData gridTableData = new GridData(GridData.FILL_BOTH);
        gridTableData.horizontalSpan=1;
        gridTableData.verticalSpan = 2;
        final Table table = tableContactViewer.getTable();
        table.setLayoutData(gridTableData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setSize(300,300);
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(150);
        tblclmnNewColumn.setText("First Name");
        TableColumn tblclmnNewColumn2 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn2.setWidth(150);
        tblclmnNewColumn2.setText("last Name");
        table.pack();

        //2nd row
        Group group = new Group(composite,SWT.SHADOW_ETCHED_IN );
        group.setText("Person's details");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        group.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.GRAB_HORIZONTAL, GridData.VERTICAL_ALIGN_END, true, true);
        gridData.horizontalSpan = 1;
        group.setLayoutData(gridData);
        firstNameLabel=new Label(group, SWT.NONE);
        firstNameLabel.setText("First Name");
        firstNameField = new Text(group, SWT.SINGLE | SWT.BORDER);
        firstNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        lastNameLabel = new Label(group, SWT.NONE);
        lastNameLabel.setText("Last Name");
        lastNameField = new Text(group, SWT.SINGLE | SWT.BORDER );
        lastNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        phoneLabel = new Label(group, SWT.NONE);
        phoneLabel.setText("Phone");
        phoneField = new Text(group, SWT.SINGLE | SWT.BORDER );
        phoneField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        mailLabel = new Label(group, SWT.NONE);
        mailLabel.setText("E-mail");
        mailField = new Text(group, SWT.SINGLE | SWT.BORDER );
        mailField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        commentLabel = new Label(group, SWT.NONE);
        commentLabel.setText("Comment");
        commentField = new Text(group, SWT.SINGLE | SWT.BORDER );
        commentField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        group.pack();

        Group btngroup = new Group(composite, SWT.SHADOW_NONE);
        RowLayout row = new RowLayout();
        btngroup.setLayout(row);
        GridData gridbtnData = new GridData(GridData.FILL, GridData.VERTICAL_ALIGN_END, false, true);
        gridbtnData.horizontalSpan = 1;
        group.setLayoutData(gridbtnData);

        addButton = new Button(btngroup, SWT.PUSH);
        addButton.setText("Add");
        updateButton = new Button(btngroup, SWT.PUSH);
        updateButton.setText("Update");
        deleteButton = new Button(btngroup, SWT.PUSH);
        deleteButton.setText("Delete");
        settingsButton = new Button(btngroup, SWT.PUSH);
        settingsButton.setText("Settings");

        btngroup.pack();

        return composite;
    }

}
