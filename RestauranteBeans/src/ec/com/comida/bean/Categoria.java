package ec.com.comida.bean;

import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable {

	/**
	 * serial.
	 */
	private static final long serialVersionUID = 6953366425252362681L;

	private long id;

	private String nombre;

	private List<Producto> listaProductos;

	public Categoria(long id) {
		super();
		this.id = id;
	}

	public Categoria(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(long id, String nombre, List<Producto> listaProductos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaProductos = listaProductos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
