package ec.com.comida.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.com.comida.bean.Categoria;

public class CategoriaDaoClass {
	public Categoria getCategoryById(Categoria categoria) {
		Categoria response = null;
		try {

			Connection connection = ConnectionClass.getConnection();
			Statement statement = connection.createStatement();
			ResultSet res = statement
					.executeQuery("SELECT * FROM CATEGORY WHERE ID = "
							+ categoria.getId());

			while (res.next()) {
				response = new Categoria(res.getLong("ID"),
						res.getString("NOMBRE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

	public List<Categoria> getAllCategories() {
		List<Categoria> response = new ArrayList<Categoria>();
		try {
			Connection connection = ConnectionClass.getConnection();
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery("select * from \"RESTOSCH\".\"CATEGORIA\"");

			while (res.next()) {
				response.add(new Categoria(res.getLong("ID"), res
						.getString("NOMBRE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
