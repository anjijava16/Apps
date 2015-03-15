package org.swt.tables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SwtGroup {
	Display display = new Display();
	Shell shell = new Shell(display);
	
	
	public SwtGroup() {
		shell.setText("Swt Group Example");
		// main group
		Group group = new Group(shell, SWT.NULL);
		group.setText("SWT.GRP0");
		group.setBounds(100, 100, 1000, 500);
		GridLayout gridLayout0 = new GridLayout();
		gridLayout0.numColumns = 2;
		group.setLayout(gridLayout0);

		group.setBackground(display.getSystemColor(SWT.COLOR_GREEN));

		// Group1 and table 1
		Group group1 = new Group(group, SWT.NULL);
		group1.setText("SWT.GRP1");
		group1.setBounds(100, 100, 300, 300);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		group1.setLayout(gridLayout);
		group1.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

		GridData gridData = new GridData(SWT.LEFT, SWT.TOP, true, true);

		final Text text = new Text(shell, SWT.BORDER);
		text.setBounds(25, 240, 220, 25);

		Table table = new Table(group1, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		table.setHeaderVisible(true);
		String[] titles = { "Col 1", "Col 2", "Col 3", "Col 4" };
		table.setLayoutData(gridData);
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText("Item " + loopIndex);
			item.setText(0, "Item " + loopIndex);
			item.setText(1, "Yes");
			item.setText(2, "No");
			item.setText(3, "table");
		}

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

		table.setBounds(25, 70, 220, 200);

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					text.setText("You checked " + event.item);
				} else {
					text.setText("You selected " + event.item);
				}
			}
		});

		// Group2 and table 2

		Group group2 = new Group(group, SWT.NULL);
		group2.setText("SWT.GRP2");
		group2.setBounds(500, 100, 300, 300);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 1;
		group2.setLayout(gridLayout2);
		group2.setBackground(display.getSystemColor(SWT.COLOR_RED));

		GridData gridData2 = new GridData(SWT.RIGHT, SWT.TOP, true, true);

		final Text text2 = new Text(shell, SWT.BORDER);
		text2.setBounds(250, 240, 220, 25);

		Table table2 = new Table(group2, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		table2.setHeaderVisible(true);
		String[] titles2 = { "Col 1", "Col 2", "Col 3", "Col 4" };
		// group1.setLayout(gridLayout);
		table2.setLayoutData(gridData2);
		for (int loopIndex = 0; loopIndex < titles2.length; loopIndex++) {
			TableColumn column = new TableColumn(table2, SWT.NULL);
			column.setText(titles2[loopIndex]);
		}

		for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
			TableItem item = new TableItem(table2, SWT.NULL);
			item.setText("Item " + loopIndex);
			item.setText(0, "Item " + loopIndex);
			item.setText(1, "Yes");
			item.setText(2, "No");
			item.setText(3, "A table item");
		}

		for (int loopIndex = 0; loopIndex < titles2.length; loopIndex++) {
			table2.getColumn(loopIndex).pack();
		}

		table2.setBounds(25, 70, 220, 200);

		table2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					text.setText("You checked " + event.item);
				} else {
					text.setText("You selected " + event.item);
				}
			}
		});

		shell.pack();
		shell.open();
		// textUser.forceFocus();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();
	}

	private void init() {

	}

	public static void main(String[] args) {
		new SwtGroup();
	}
}