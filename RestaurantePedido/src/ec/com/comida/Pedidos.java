package ec.com.comida;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;





import ec.com.comida.bean.Categoria;
import ec.com.comida.bean.MenuItem;
import ec.com.comida.bean.Producto;
import ec.com.comida.composite.CompositeCategoria;
import ec.com.comida.composite.CompositeProducto;
import ec.com.comida.comunication.PedidoCliente;
import ec.com.comida.service.ApplicationService;

public class Pedidos {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Pedidos window = new Pedidos();
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

		GridLayout shellGridLayout = new GridLayout();
		shellGridLayout.numColumns = 4;
		shellGridLayout.makeColumnsEqualWidth = true;

		// GridData logoGridData = new GridData(SWT.NONE);
		// logoGridData.horizontalSpan = 2;
		//
		// GridData tableGridData = new GridData(SWT.NONE);
		// tableGridData.horizontalSpan = 2;

		shell = new Shell();
		shell.setSize(1024, 768);
		shell.setText("Restaurante");
		shell.setLayout(shellGridLayout);

		GridData logoGridData = new GridData();
		logoGridData.horizontalAlignment = GridData.CENTER;
		logoGridData.verticalAlignment = GridData.CENTER;

		Composite logoComposite = new Composite(shell, SWT.NONE);
		logoComposite.setLayoutData(logoGridData);
		logoComposite.setLayout(new GridLayout());

		Label lblLogo = new Label(logoComposite, SWT.NONE);
		lblLogo.setImage(SWTResourceManager.getImage(Pedidos.class,
				"/ec/com/comida/img/logoEmpresa.jpg"));

		GridData tablaGridData = new GridData();
		tablaGridData.horizontalAlignment = GridData.FILL;
		tablaGridData.verticalAlignment = GridData.BEGINNING;
		tablaGridData.horizontalSpan = 2;
		

		GridLayout tablaGridLayout = new GridLayout();
//		tablaGridLayout.makeColumnsEqualWidth = true;
		
		Composite tablaComposite = new Composite(shell, SWT.NONE);
		tablaComposite.setLayoutData(tablaGridData);
		tablaComposite.setLayout(tablaGridLayout);

		table = new Table(tablaComposite, SWT.BORDER  | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		
//		TableColumn tc0 = new TableColumn(table, SWT.CENTER);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		TableColumn tc4 = new TableColumn(table, SWT.CENTER);
//		tc0.setText("Id");
		tc1.setText("Cantidad");
		tc2.setText("Plato");
		tc3.setText("Precio");
		tc4.setText("Total");

//		tc0.setWidth(100);
		tc1.setWidth(70);
		tc2.setWidth(230);
		tc3.setWidth(100);
		tc4.setWidth(100);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.setSize(512, 512);

		GridData botonesGridData = new GridData();
		botonesGridData.horizontalAlignment = GridData.CENTER;
		botonesGridData.verticalAlignment = GridData.CENTER;

		Composite botonesComposite = new Composite(shell, SWT.NONE);
		botonesComposite.setLayoutData(botonesGridData);
		GridLayout botonesGridLayout = new GridLayout();
		botonesGridLayout.numColumns = 1;
		botonesComposite.setLayout(botonesGridLayout);
		
		
		Button btnEliminar = new Button(botonesComposite, SWT.NONE);
		btnEliminar.setImage(SWTResourceManager.getImage(Pedidos.class, "/ec/com/comida/img/iconoEliminar.JPG"));
		btnEliminar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				int posicion = table.getSelectionIndex();
				if (posicion >= 0) {
					table.remove(posicion);
				}
			}
		});
		
		
		Button btnMenos = new Button(botonesComposite, SWT.NONE);
		btnMenos.setImage(SWTResourceManager.getImage(Pedidos.class, "/ec/com/comida/img/iconoMenos.JPG"));
		btnMenos.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				int posicion = table.getSelectionIndex();
				if (posicion >= 0) {
					MenuItem menuItem = (MenuItem) table.getItem(posicion)
							.getData();

					int cantidad = menuItem.getCantidad();
					int nuevaCantidad = cantidad - 1;
					MathContext mc = new MathContext(4);
					BigDecimal total = menuItem.getProducto().getPrecio()
							.multiply(new BigDecimal(nuevaCantidad), mc);
					menuItem.setCantidad(nuevaCantidad);
					menuItem.setTotal(total);

					TableItem item1 = table.getItem(posicion);
					item1.setData(menuItem);
//					item1.setText(0,
//							String.valueOf(menuItem.getProducto().getId()));
					item1.setText(0, String.valueOf(menuItem.getCantidad()));
					item1.setText(1, menuItem.getProducto().getNombre());
					item1.setText(2,
							String.valueOf(menuItem.getProducto().getPrecio()));
					item1.setText(3, String.valueOf(menuItem.getTotal()));
				}

			}
		});
		
		
		
		Button btnMas = new Button(botonesComposite, SWT.NONE);
		btnMas.setImage(SWTResourceManager.getImage(Pedidos.class,
				"/ec/com/comida/img/iconoMas.JPG"));

		btnMas.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				int posicion = table.getSelectionIndex();
				if (posicion >= 0) {
					MenuItem menuItem = (MenuItem) table.getItem(posicion)
							.getData();

					int cantidad = menuItem.getCantidad();
					int nuevaCantidad = cantidad + 1;
					MathContext mc = new MathContext(4);
					BigDecimal total = menuItem.getProducto().getPrecio()
							.multiply(new BigDecimal(nuevaCantidad), mc);
					menuItem.setCantidad(nuevaCantidad);
					menuItem.setTotal(total);

					TableItem item1 = table.getItem(posicion);
					item1.setData(menuItem);
//					item1.setText(0,
//							String.valueOf(menuItem.getProducto().getId()));
					item1.setText(0, String.valueOf(menuItem.getCantidad()));
					item1.setText(1, menuItem.getProducto().getNombre());
					item1.setText(2,
							String.valueOf(menuItem.getProducto().getPrecio()));
					item1.setText(3, String.valueOf(menuItem.getTotal()));
				}

			}
		});
		
		
		Button btnGenerarPedido = new Button(botonesComposite, SWT.NONE);
		btnGenerarPedido.setImage(SWTResourceManager.getImage(Pedidos.class,
				"/ec/com/comida/img/procesar.JPG"));

		btnGenerarPedido.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				TableItem tableItemArray[] = table.getItems();
				List<MenuItem> menuItemList = new ArrayList<MenuItem>();
				if(tableItemArray.length > 0) {
					int size = tableItemArray.length;
					for (int i=0; i<size; i++) {
						menuItemList.add((MenuItem) tableItemArray[i].getData());
					}					
				}
				PedidoCliente cliente = PedidoCliente.getInstance();
				try {
					cliente.sendJSONMessage(menuItemList);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				
				

			}
		});
		
		
		
		
		
		
		
		GridData botonesCategoriaGridData = new GridData();
		botonesCategoriaGridData.horizontalAlignment = GridData.CENTER;
		botonesCategoriaGridData.verticalAlignment = GridData.CENTER;
		botonesCategoriaGridData.horizontalSpan = 2;
		
		Composite botonesCategoriaComposite = new Composite(shell, SWT.NONE);
		botonesCategoriaComposite.setLayoutData(botonesCategoriaGridData);
		
		GridLayout botonesCategoriaGridLayout = new GridLayout();
		botonesCategoriaGridLayout.numColumns = 2;
		botonesCategoriaComposite.setLayout(botonesCategoriaGridLayout);
		
		
		
		GridData botonesproductoGridData = new GridData();
		botonesproductoGridData.horizontalAlignment = GridData.FILL;
		botonesproductoGridData.verticalAlignment = GridData.FILL;
		botonesproductoGridData.horizontalSpan = 2;
		
		CompositeProducto compositeProducto = new CompositeProducto(shell, SWT.NONE);
		compositeProducto.setLayoutData(botonesproductoGridData);
		
		GridLayout gridLayoutProducto = new GridLayout();
		gridLayoutProducto.numColumns = 5;
		compositeProducto.setLayout(gridLayoutProducto);
		
		

		
		ApplicationService service = new ApplicationService();
		List<Categoria> listCategories = service.getAllCategories();
		CompositeCategoria btn = null;
		for (Categoria categoriaTemp : listCategories) {
			List<Producto> listProduct = service.getProductsByCategory(categoriaTemp);
			categoriaTemp.setListaProductos(listProduct);
			
			btn = new CompositeCategoria(botonesCategoriaComposite, SWT.NONE);
			btn.setText(categoriaTemp.getNombre());
			btn.setCompositeProducto(compositeProducto);
			btn.setCategoria(categoriaTemp);
			btn.setTable(table);
			btn.createComponents();
		}
		


	}
}
