package org.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;


public class SwtSlider {

    private Shell shell;

    private Image mute;
    private Image min;
    private Image med;
    private Image max;


    public SwtSlider(Display display) {

        shell = new Shell(display);
        Device dev = shell.getDisplay();

        try {
            mute = new Image(dev, "micromax.png");
            min = new Image(dev, "micromax1.png");
            med = new Image(dev, "micromax2.png");
            max = new Image(dev, "micromax3.png");
        } catch(Exception e) {
            System.out.println("Cannot load images");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        shell.setText("Slider");

        initUI();

        shell.setSize(350, 200);
        shell.setLocation(300, 300);

        shell.open();

        while (!shell.isDisposed()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
    }


    public void initUI() {

        final Label label = new Label(shell, SWT.IMAGE_PNG);
        label.setImage(mute);
        label.pack();
        label.setLocation(240, 30);
              

        final Slider slider = new Slider(shell, SWT.HORIZONTAL);
        slider.setMaximum(100);
        slider.setBounds(30, 30, 180, 30);

        slider.addListener (SWT.Selection, new Listener () {
            public void handleEvent (Event e) {

                int value = slider.getSelection();

                if (value == 0) {
                    label.setImage(mute);
                    label.pack();
                } else if (value > 0 && value <= 30) {
                    label.setImage(min);
                } else if (value > 30 && value < 80) {
                    label.setImage(med);
                } else {
                    label.setImage(max);
                }
            }
        });
    }

    @Override
    public void finalize() {
        System.out.println("disposing");
        mute.dispose();
        med.dispose();
        min.dispose();
        max.dispose();
    }

    public static void main(String[] args) {
        Display display = new Display();
        SwtSlider app = new SwtSlider(display);
        app.finalize();
        display.dispose();
    }
}