package org.swt.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Calculator {
	private Shell shell;

	public Calculator(Display display) {
		shell = new Shell(display);
		shell.setText("Calculator");
		initUI();
		shell.pack();
		shell.setLocation(300, 300);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void initUI() {
		GridLayout gl = new GridLayout(4, true);
		gl.horizontalSpacing = 4;
		gl.verticalSpacing = 4;
		gl.marginBottom = 5;
		gl.marginTop = 5;
		shell.setLayout(gl);
		String[] buttons = { "Cls", "Bck", "", "Close", "7", "8", "9", "/",
				"4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
		Text display = new Text(shell, SWT.SINGLE);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		gridData.horizontalAlignment = GridData.FILL;
		display.setLayoutData(gridData);
		for (int i = 0; i < buttons.length; i++) {
			if (i == 2) {
				Label lbl = new Label(shell, SWT.CENTER);
				GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
				lbl.setLayoutData(gd);
			} else {
				Button btn = new Button(shell, SWT.PUSH);
				btn.setText(buttons[i]);
				GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
				gd.widthHint = 50;
				gd.heightHint = 30;
				btn.setLayoutData(gd);
			}
		}
	}

	public static void main(String[] args) {
		Display display = new Display();
		new Calculator(display);
		display.dispose();
	}
}
