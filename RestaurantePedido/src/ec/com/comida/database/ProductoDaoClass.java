package ec.com.comida.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.com.comida.bean.Categoria;
import ec.com.comida.bean.Producto;

public class ProductoDaoClass {
	public List<Producto> getProductsByCategory(Categoria categoria) {
		List<Producto> response = new ArrayList<Producto>();
		try {

			Connection connection = ConnectionClass.getConnection();
			Statement statement = connection.createStatement();
			ResultSet res = statement
					.executeQuery("SELECT * FROM \"RESTOSCH\".\"PRODUCTO\" WHERE \"CATEGORIA_ID\" = "
							+ categoria.getId());

			while (res.next()) {
				response.add(new Producto(res.getLong("ID"), res
						.getString("NOMBRE"), res.getBigDecimal("PRECIO"),
						categoria.getId(), res.getString("IMAGEN")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

}
