package alonzo.carlos.spring.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter @EqualsAndHashCode
//@Accessors(fluent = true)
public class Product {
    @NotEmpty
    private Integer id;
    @NotEmpty
    private String desc;
    @NotEmpty
    private BigDecimal price;
    @NotEmpty
    private String imageUrl;

    public Product(){}
    public Product(Integer id, String desc, BigDecimal price, String imageUrl) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
