package exercise.repository;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

public class ProductsRepository extends BaseRepository {

    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getPrice());
            stmt.executeUpdate();
            ResultSet key = stmt.getGeneratedKeys();

            if (key.next()) {
                Long id = key.getLong(1);
                product.setId(id);
            } else {
                throw new SQLException("NOOOOOOOOOOOOOOOOOOOOO");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet pointer = stmt.executeQuery();

            if (pointer.next()) {
                String title = pointer.getString("title");
                int price = pointer.getInt("price");

                Product product = new Product(title, price);
                product.setId(id);

                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    public static List<Product> getEntities() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet pointer = stmt.executeQuery(sql);

            while (pointer.next()) {
                Long id = pointer.getLong("id");
                String title = pointer.getString("title");
                int price = pointer.getInt("price");

                Product product = new Product(title, price);
                product.setId(id);

                products.add(product);
            }
        }

        return products;
    }
}
