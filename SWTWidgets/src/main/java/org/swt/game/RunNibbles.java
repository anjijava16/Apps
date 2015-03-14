package org.swt.game;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class RunNibbles {

    private Shell shell;

    public RunNibbles(Display display) {

        shell = new Shell(display);

        shell.setText("Nibbles");

        initUI();

        shell.setSize(305, 330);
        shell.setLocation(300, 300);
        shell.open();

        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
    }


    public void initUI() {

        FillLayout layout = new FillLayout();
        shell.setLayout(layout);

        new NibblesGame(shell);
    }


    public static void main(String[] args) {
        Display display = new Display();
        new RunNibbles(display);
        display.dispose();
    }
}
