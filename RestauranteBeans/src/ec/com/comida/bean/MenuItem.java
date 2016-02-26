package ec.com.comida.bean;

import java.math.BigDecimal;

public class MenuItem {

	private int cantidad;
	private BigDecimal total;
	private Producto producto;

	public MenuItem(int cantidad, BigDecimal total, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
