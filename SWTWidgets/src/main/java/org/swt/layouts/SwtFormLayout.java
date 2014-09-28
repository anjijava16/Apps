package org.swt.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class SwtFormLayout {
	private Shell shell;

	public SwtFormLayout(Display display) {
		shell = new Shell(display);
		shell.setText("Buttons");
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
		FormLayout layout = new FormLayout();
		shell.setLayout(layout);
		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("OK");
		Button cancelButton = new Button(shell, SWT.PUSH);
		cancelButton.setText("Cancel");
		FormData cancelData = new FormData(80, 30);
		cancelData.right = new FormAttachment(98);
		cancelData.bottom = new FormAttachment(95);
		cancelButton.setLayoutData(cancelData);
		FormData okData = new FormData(80, 30);
		okData.right = new FormAttachment(cancelButton, -5, SWT.LEFT);
		okData.bottom = new FormAttachment(cancelButton, 0, SWT.BOTTOM);
		okButton.setLayoutData(okData);
	}

	public static void main(String[] args) {
		Display display = new Display();
		new SwtFormLayout(display);
		display.dispose();
	}
}