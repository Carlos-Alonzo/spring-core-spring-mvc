package alonzo.carlos.spring.controller;

import alonzo.carlos.spring.domain.Product;
import alonzo.carlos.spring.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private ProductController controller;
    @Mock private ProductService service;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(1, "Hovercraft", BigDecimal.valueOf(14.99), "hovercraft");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        doReturn(generateProducts()).when(service).listAllProducts();
        doReturn(testProduct).when(service).getProductById(anyInt());
        doReturn(testProduct).when(service).saveUpdateProduct(any());
    }

    @Test
    void listProducts() throws Exception {
        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("products", hasSize(5)));
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    void saveUpdateProduct_New() throws Exception {
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("productform"));
    }

    @Test
    void saveUpdateProduct() throws Exception {
        mockMvc.perform(post("/product")
                .param("id", "1")
                .param("description", "Hovercraft")
                .param("price", "14.99")
                .param("imageUrl", "hovercraft"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
        //
        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(service).saveUpdateProduct(boundProduct.capture());
    }

    @Test
    void editProductById() throws Exception {
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productform"));
        verify(service).getProductById(1);
    }

    @Test
    void deleteProductById() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products/"));
        verify(service).deleteProductById(1);
    }

    private List<Product> generateProducts(){
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
}