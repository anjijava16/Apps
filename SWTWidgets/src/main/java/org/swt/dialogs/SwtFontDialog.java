package org.swt.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SwtFontDialog {

    private Shell shell;

    public SwtFontDialog(Display display) {

        shell = new Shell(display);

        shell.setText("FontDialog");

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
                FontDialog dialog = new FontDialog(shell);
                FontData fdata = dialog.open();

                if (fdata != null) {

                    Font font = new Font(shell.getDisplay(), fdata);
                
                    label.setFont(font);
                    label.pack();
                    font.dispose();
                }                
            }
        });
    }

    public static void main(String[] args) {
        Display display = new Display();
        new SwtFontDialog(display);
        display.dispose();
    }
}
