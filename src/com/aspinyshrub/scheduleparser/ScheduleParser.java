/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspinyshrub.scheduleparser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 *
 * @author furze
 */
public class ScheduleParser {
    Text origionalFilePath;
    Text newFilePath;
    Text differences;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new ScheduleParser().createShell(display);
        
        shell.open();
        while (!shell.isDisposed()){
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
    
    public Shell createShell(Display display) {
        Shell shell = new Shell(display);
        shell.setSize(500, 500);
        shell.setText("Schedule Parser");
        
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        shell.setLayout(layout);
        
        new Label(shell, SWT.NONE).setText("Origional File:");
        
        origionalFilePath = new Text(shell, SWT.SINGLE | SWT.BORDER);
        origionalFilePath.setEnabled(false);
        GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        origionalFilePath.setLayoutData(gridData);
        
        final Button browseOrigionalFile = new Button(shell, SWT.PUSH);
        browseOrigionalFile.setText("Browse");
        
        browseOrigionalFile.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent event){
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Open");
                String[] filterExtension = { "*.csv" };
                fileDialog.setFilterExtensions(filterExtension);
                String selected = fileDialog.open();
                
                origionalFilePath.setText(selected);
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent event){
                
            }
        });
        
        new Label(shell, SWT.NONE).setText("New File:");
        
        newFilePath = new Text(shell, SWT.SINGLE | SWT.BORDER);
        newFilePath.setEnabled(false);
        newFilePath.setLayoutData(gridData);
        
        final Button browseNewFile = new Button(shell,SWT.PUSH);
        browseNewFile.setText("Browse");
        
        browseNewFile.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent event){
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Open");
                String[] filterExtension = { "*.csv" };
                fileDialog.setFilterExtensions(filterExtension);
                String selected = fileDialog.open();
                
                newFilePath.setText(selected);
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent event){
                
            }
        });
        
        final Button parse = new Button(shell, SWT.PUSH);
        GridData parseGridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
        parseGridData.horizontalSpan = 3;
        parse.setLayoutData(parseGridData);
        parse.setText("Parse");
        parse.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent event){
                if (origionalFilePath.getText().length() == 0){
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY );
                    errorBox.setText("Warning");
                    errorBox.setMessage("Origional file required!");
                    
                    errorBox.open();
                }
                else if (newFilePath.getText().length() == 0){
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.RETRY );
                    errorBox.setText("Warning");
                    errorBox.setMessage("New file required!");
                    
                    errorBox.open();
                }
                else {
                    Parser.Parse(origionalFilePath.getText(), newFilePath.getText());
                }
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent event){
                
            }
        });
        
        Label differencesLabel = new Label(shell, SWT.NONE);
        differencesLabel.setText("Differences");
        GridData differencesLabelGridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
        differencesLabelGridData.horizontalSpan = 3;
        differencesLabel.setLayoutData(differencesLabelGridData);
        
        differences = new Text(shell, SWT.MULTI | SWT.BORDER);
        differences.setEnabled(false);
        GridData differencesGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        differencesGridData.horizontalSpan = 3;
        differences.setLayoutData(differencesGridData);
        
        GridData exportGridData = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
        exportGridData.horizontalSpan = 3;
        final Button export = new Button(shell,SWT.PUSH);
        export.setLayoutData(exportGridData);
        export.setText("Export");
        
        export.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent event){
                //TODO
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent event){
                
            }
        });
        
        return shell;
    }
}
