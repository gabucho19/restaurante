package ec.com.comida.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

import ec.com.comida.bean.Categoria;

public class CompositeCategoria extends Composite {

	private String text;
	private CompositeProducto compositeProducto;
	private Categoria categoria;
	private Table table;

	public CompositeCategoria(Composite parent, int style) {
		super(parent, style);
		
	}

	public void createComponents() {
		setLayout(new FillLayout());

		Button button = new Button(this, SWT.PUSH);
		button.setText(getText());

		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				for (Control control : getCompositeProducto().getChildren()) {
					control.dispose();
				}
				getCompositeProducto().setListaProducto(
						categoria.getListaProductos());
				getCompositeProducto().setTable(table);
				getCompositeProducto().createComponents();
				getCompositeProducto().layout(true, true);
			}
		});
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CompositeProducto getCompositeProducto() {
		return compositeProducto;
	}

	public void setCompositeProducto(CompositeProducto compositeProducto) {
		this.compositeProducto = compositeProducto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
