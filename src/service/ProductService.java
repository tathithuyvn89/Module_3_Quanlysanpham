package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void saveProduct (Product product);
    Product findById(String id);
    void removeProduct(String id);
    Product searchByName(String productName);
}
