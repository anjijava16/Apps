package org.swt.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;



public class SwtColorDialog {

    private Shell shell;

    public SwtColorDialog(Display display) {

        shell = new Shell(display);

        shell.setText("ColorDialog");

        initUI();

        shell.setSize(350, 250);
        shell.setLocation(300, 300);

        shell.open();

        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
    }


    public void initUI() {


        final Label label = new Label(shell, SWT.NONE);
        label.setText("ZetCode Java SWT tutorial");

        label.setLocation(50, 50);
        label.pack();


        shell.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(MouseEvent event) {
                ColorDialog dialog = new ColorDialog(shell);
                RGB rgb = dialog.open();
                if (rgb != null) {
                    Color col = new Color(shell.getDisplay(), rgb);
                    label.setForeground(col);
                    col.dispose();
                }
            }

        });
    }


    public static void main(String[] args) {
        Display display = new Display();
        new SwtColorDialog(display);
        display.dispose();
    }
}
