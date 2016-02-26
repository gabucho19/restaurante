package ec.com.comida.composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import ec.com.comida.bean.MenuItem;
import ec.com.comida.bean.Producto;

public class CompositeProducto extends Composite {

	private List<Producto> listaProducto;
	private Table table;

	public CompositeProducto(Composite parent, int style) {
		super(parent, style);
		// createComponents();
	}

	public void createComponents() {

		GridLayout gridLayoutProducto = new GridLayout();
		gridLayoutProducto.numColumns = 3;

		setLayout(gridLayoutProducto);

		for (Producto producto : listaProducto) {
			// new Button(this, SWT.NONE).setText(producto.getNombre());

			// new BtnProducto(this, SWT.NONE, table, producto);
			final Producto temp = producto;

			Button button = new Button(this, SWT.PUSH);
			button.setImage(SWTResourceManager.getImage(CompositeProducto.class,
					"/ec/com/comida/img/" + producto.getImagen()));
			// button.setLocation(20, 45);
			// button.pack();

			button.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event arg0) {
					TableItem[] data = getTable().getItems();
					MenuItem menuItem = null;
					int count = data.length;
					boolean existe = false;
					for (int i = 0; i < count; i++) {
						menuItem = (MenuItem) data[i].getData();
						if (menuItem.getProducto().getId() == temp.getId()) {
							existe = true;
							break;
						}
					}
					TableItem item1 = null;
					if (!existe) {
						item1 = new TableItem(getTable(), SWT.NONE);
						menuItem = new MenuItem(1, temp.getPrecio(), temp);
						item1.setData(menuItem);
//						item1.setText(0, String.valueOf(temp.getId()));
						item1.setText(0, String.valueOf(menuItem.getCantidad()));
						item1.setText(1, menuItem.getProducto().getNombre());
						item1.setText(2, String.valueOf(menuItem.getProducto()
								.getPrecio()));
						item1.setText(3, String.valueOf(menuItem.getTotal()));

					} else {
						// mensaje
					}
				}
			});
		}

	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
