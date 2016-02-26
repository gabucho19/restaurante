package ec.com.comida.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Producto implements Serializable {

	/**
	 * serial.
	 */
	private static final long serialVersionUID = 6256259812906400578L;

	private long id;

	private String nombre;

	private BigDecimal precio;

	private long categoriaId;

	private String imagen;

	public Producto(long id, String nombre, BigDecimal precio,
			long categoriaId, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaId = categoriaId;
		this.imagen = imagen;
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
