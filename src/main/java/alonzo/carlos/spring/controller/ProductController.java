package alonzo.carlos.spring.controller;

import alonzo.carlos.spring.domain.Product;
import alonzo.carlos.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ControllerAdvice
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }


    @RequestMapping("/products")
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @RequestMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @RequestMapping("/product/new")
    public String saveUpdateProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveUpdateProduct(@Valid @ModelAttribute("product") Product product){
        var savedUpdatedProduct = productService.saveUpdateProduct(product);
        return "redirect:/product/" + savedUpdatedProduct.getId();
    }

    @RequestMapping("/product/edit/{id}")
    public String editProductById(@PathVariable Integer id, Model model) {
        model.addAttribute("product",productService.getProductById(id));
        return "productform";
    }

    @RequestMapping("product/delete/{id}")
    public String deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
        return "redirect:/products/";
    }
}
