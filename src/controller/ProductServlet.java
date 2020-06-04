package controller;

import model.Product;
import service.ProductServiceIpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/myproduct")
public class ProductServlet extends HttpServlet {
   private ProductServiceIpl productServiceIpl= new ProductServiceIpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            default:
                break;
        }


    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String id= request.getParameter("id");
        Product product = productServiceIpl.findById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        }else {
            productServiceIpl.removeProduct(id);
            try {
                response.sendRedirect("./myproduct");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String newName= request.getParameter("newName");
        Double newPrice= Double.parseDouble(request.getParameter("newPrice"));
        String newDescription= request.getParameter("newDes");
        String newMaker= request.getParameter("newMaker");
        String id= request.getParameter("id");
        Product product = productServiceIpl.findById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        } else {
            dispatcher= request.getRequestDispatcher("view/editproduct.jsp");
            request.setAttribute("message","Update product success!!!");
            product.setName(newName);
            product.setPrice(newPrice);
            product.setDescription(newDescription);
            product.setMaker(newMaker);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("productName");
        Double price= Double.parseDouble(request.getParameter("productPrice"));
        String description= request.getParameter("productDes");
        String maker= request.getParameter("productMaker");
        productServiceIpl.saveProduct(new Product(name,price,description,maker));
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/createproduct.jsp");
        request.setAttribute("message","Create new product success!!!");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatForm(request, response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request,response);
                break;
            case "view":
                viewProductForm(request, response);
                break;
//            case "search":
//                resultSearchForm(request, response);
//                break;
            default:
                listCustomers(request, response);
                break;
        }

    }
//
//    private void resultSearchForm(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("search");
//        Product product= productServiceIpl.searchByName(name);
//        RequestDispatcher dispatcher;
//        if(product==null){
//            dispatcher=request.getRequestDispatcher("404-error.jsp");
//        }else {
//            dispatcher=request.getRequestDispatcher("view/searchproduct.jsp");
//            request.setAttribute("product", product);
//        }
//        try {
//            dispatcher.forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void viewProductForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Product product=productServiceIpl.findById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("view/product.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("id");
        Product product=productServiceIpl.findById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("view/deleteproduct.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String id= request.getParameter("id");
        Product product = productServiceIpl.findById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("view/editproduct.jsp");
            request.setAttribute("product", product);

            }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/createproduct.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void listCustomers(HttpServletRequest request, HttpServletResponse response){
        List<Product> productList= productServiceIpl.findAll();
        request.setAttribute("products",productList);
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/listproducts.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
