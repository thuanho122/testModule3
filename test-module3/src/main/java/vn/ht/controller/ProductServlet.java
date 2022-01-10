package vn.ht.controller;


import vn.ht.DAO.ProductDAO;
import vn.ht.model.Category;
import vn.ht.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "newproduct":
                try {
                    insertProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    updateProduct(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                try {
                    deleteUser(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                listProduct(req, resp);
                break;
        }
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String name = req.getParameter("nameProduct");
        double price = Double.parseDouble(req.getParameter("priceProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantityProduct"));
        String color = req.getParameter("colorProduct");
        int categoryId = Integer.parseInt(req.getParameter("selectcategory"));
        Product newProduct = new Product(name, price, quantity, color, categoryId);
        productDAO.insertProduct(newProduct);
        listProduct(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("nameProduct");
        double price = Double.parseDouble(req.getParameter("priceProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantityProduct"));
        String color = req.getParameter("colorProduct");
        int categoryId = Integer.parseInt(req.getParameter("selectcategory"));
        int id = Integer.parseInt(req.getParameter("id"));
        Product newProduct = new Product(id, name, price, quantity, color, categoryId);
        System.out.println(newProduct);
        productDAO.updateProduct(newProduct);
        listProduct(req, resp);
    }


    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        productDAO.deleteProduct(id);
        listProduct(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id);
        List<Category> categoryList = productDAO.selectAllCategory();
        System.out.println(categoryList);
        req.setAttribute("listcategory", categoryList);
        req.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit-product.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = productDAO.selectAllCategory();
        System.out.println(categoryList);
        req.setAttribute("listcategory", categoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/add-product.jsp");
        dispatcher.forward(req, resp);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = productDAO.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        System.out.println(listProduct.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list-product.jsp");
        dispatcher.forward(request, response);
    }
}
