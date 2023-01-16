package alonzo.carlos.spring.services;

import alonzo.carlos.spring.domain.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    private static final List<Product> products = generateProducts();
    @Override
    public List<Product> listAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return products.get(id);
    }

    @Override
    public boolean deleteProductById(int id) {
        boolean result = false;
        if (id < products.size() && id >= 0) {
            result = products.remove(id) != null;
        }
        return result;
    }

    @Override
    public Product saveUpdateProduct(@NotNull Product product) {
        if(!products.contains(product)) {
            int currentIndex = getIndex();
            products.add(currentIndex, product);
            product.setId(currentIndex);
        }
        else {
            products.set(products.indexOf(product), product);
        }
        return product;
    }

    private static List<Product> generateProducts(){
        var list = new ArrayList<Product>();
        int index = 0;
        var p1 = new Product(index, "Hovercraft", BigDecimal.valueOf(14.99), "hovercraft");
        list.add(index++, p1);
        var p2 = new Product(index, "Hammer", BigDecimal.valueOf(14.99), "Hammer.com");
        list.add(index++, p2);
        var p3 = new Product(index, "Corvette", BigDecimal.valueOf(70000.00), "Corvette");
        list.add(index++, p3);
        var p4 = new Product(index, "M3", BigDecimal.valueOf(55000.00), "M3");
        list.add(index++, p4);
        var p5 = new Product(index, "NSX", BigDecimal.valueOf(100000.00), "NSX");
        list.add(index, p5);
        return list;
    }

    private static int getIndex(){
        return products.size();
    }
}
