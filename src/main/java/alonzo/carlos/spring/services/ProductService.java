package alonzo.carlos.spring.services;

import alonzo.carlos.spring.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();
    Product getProductById(int id);
    boolean deleteProductById(int id);
    Product saveUpdateProduct(Product product);
}
