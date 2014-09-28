package org.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;



public class SwtLabelCheckbox {

    private Shell shell;
    private Button cb;
    String lyrics =
    		"And I know that he knows I'm unfaithful\n"+
    		"And it kills him inside\n"+
    		"To know that I am happy with some other guy\n";

    public SwtLabelCheckbox(Display display) {

        shell = new Shell(display);

        shell.setText("Label & Check button");

        initUI();

        shell.setSize(250, 200);
        shell.setLocation(300, 300);

        shell.open();
        
        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
    }


    public void initUI() {

        cb = new Button(shell, SWT.CHECK);
        cb.setText("Show title");
        cb.setSelection(true);
        cb.setLocation(50, 50);
        cb.pack();


        cb.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (cb.getSelection()) {
                    shell.setText("Check button");
                } else {
                    shell.setText("");
                }
            }
        });
        Label label = new Label(shell, SWT.LEFT);
        label.setText(lyrics);
        
        Point p = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        label.setBounds(5, 5, p.x+5, p.y+5);
    }


    public static void main(String[] args) {
        Display display = new Display();
        new SwtLabelCheckbox(display);
        display.dispose();
    }
}

