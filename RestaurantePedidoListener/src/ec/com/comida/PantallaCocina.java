package ec.com.comida;

import java.io.IOException;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ec.com.comida.comunicate.ListenerPedido;

public class PantallaCocina {

	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PantallaCocina window = new PantallaCocina();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1024, 768);
		shell.setText("SWT Application");
		
		GridLayout shellGridLayout = new GridLayout();
		shellGridLayout.numColumns = 5;
		shell.setLayout(shellGridLayout);

		int port = 2040;
		try {
			Thread t = new ListenerPedido(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
