package org.swt.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class SwtFillLayout {

    private Shell shell;
    private Image castle;

    public SwtFillLayout(Display display) {

        shell = new Shell(display);
        shell.setLayout(new FillLayout());

        shell.setText("FillLayout");

        Device dev = shell.getDisplay();

        try {
            castle = new Image(dev, "micromax.png");
        } catch(Exception e) {
            System.out.println("Cannot load image");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        initUI();
        
        Rectangle rect = castle.getBounds();
        shell.setSize(rect.width, rect.height);

        shell.setLocation(300, 300);

        shell.open();

        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
    }

    public void initUI() {

        Label label = new Label(shell, SWT.IMAGE_PNG);
        label.setImage(castle);
        label.pack();
    }

    @Override
    public void finalize() {
        System.out.println("disposing");
        castle.dispose();
    }


    public static void main(String[] args) {
        Display display = new Display();
        SwtFillLayout app = new SwtFillLayout(display);
        app.finalize();
        display.dispose();
    }
}
