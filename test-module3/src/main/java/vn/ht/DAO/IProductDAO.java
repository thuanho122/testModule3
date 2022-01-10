package vn.ht.DAO;

import vn.ht.model.Category;
import vn.ht.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    List<Category> selectAllCategory();

    List<Product> selectAllProduct();

    Product selectProduct(int id);

    void insertProduct(Product product) throws SQLException;

    boolean deleteProduct(int id) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;
}
