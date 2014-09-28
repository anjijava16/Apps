package org.swt.paints;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class DrawingShapes {

    private Shell shell;

    public DrawingShapes(Display display) {

        shell = new Shell(display);

        shell.addPaintListener(new ArcExamplePaintListener());

        shell.setText("Basic shapes");
        shell.setSize(430, 300);
        shell.setLocation(300, 300);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    private class ArcExamplePaintListener implements PaintListener {

        public void paintControl(PaintEvent e) {

            drawShapes(e);
            e.gc.dispose();
        }
    }

    private void drawShapes(PaintEvent e) {

        e.gc.setAntialias(SWT.ON);

        e.gc.setBackground(new Color(e.display, 150, 150, 150));

        e.gc.fillRectangle(20, 20, 120, 80);
        e.gc.fillRectangle(180, 20, 80, 80);
        e.gc.fillOval(290, 20, 120, 70);

        e.gc.fillOval(20, 150, 80, 80);
        e.gc.fillRoundRectangle(150, 150, 100, 80, 25, 25);
        e.gc.fillArc(280, 150, 100, 100, 0, 115);
    }

    public static void main(String[] args) {
        Display display = new Display();
        new DrawingShapes(display);
        display.dispose();
    }
}
