package vn.ht.DAO;

import vn.ht.model.Category;
import vn.ht.model.Product;
import vn.ht.repository.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (name, price, quantity, color, categoryid) VALUES (?, ?,?,?,?);";
    private static final String SELECT_ALL_PRODUCT = "select p.id, p.name, p.price, p.quantity, p.color,p.categoryId, c.name as categoryName from product p inner join category c  ON p.categoryId = c.id;";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM category;";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price= ?, quantity =?, color= ?, categoryId= ? where id = ?;";
    private static final String SELECT_PRODUCT_BY_ID = "select id,name,price,quantity,color,categoryid from product where id = ?;";

    public ProductDAO() {
        MysqlConnection.init("quanlysanpham");

    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categoryList.add(new Category(id, name));
            }
        } catch (SQLException ignored) {
            System.out.println(ignored);
        }
        return categoryList;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");
                products.add(new Product(id, name, price, quantity, color, categoryId, categoryName));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return products;
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                // String description = rs.getString("description");
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");
                product = new Product(id, name, price, quantity, color, categoryId, categoryName);
            }
        } catch (SQLException ignored) {
        }
        return product;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setInt(5, product.getCategoryId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setInt(5, product.getCategoryId());
            statement.setInt(6, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
