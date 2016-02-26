package ec.com.comida.service;

import java.util.List;

import ec.com.comida.bean.Categoria;
import ec.com.comida.bean.Producto;
import ec.com.comida.database.CategoriaDaoClass;
import ec.com.comida.database.ProductoDaoClass;

public class ApplicationService {
	private CategoriaDaoClass categoriaDaoClass = new CategoriaDaoClass();

	private ProductoDaoClass productoDaoClass = new ProductoDaoClass();

	public Categoria getCategoryById(Categoria categoria) {
		return categoriaDaoClass.getCategoryById(categoria);
	}

	public List<Categoria> getAllCategories() {
		return categoriaDaoClass.getAllCategories();
	}

	public List<Producto> getProductsByCategory(Categoria categoria) {
		return productoDaoClass.getProductsByCategory(categoria);
	}

}
