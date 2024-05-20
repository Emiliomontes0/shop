package com.Lunexconline.Ecommerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.awt.Image;
@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Product {
    private long id;
    private String name;
    private int cost;
    private int count;
    private Image image;

    Product(String name, int cost, int count, Image image){
        this.name = name;
        this.cost = cost;
        this.count = count;
        this.image = image;
    }
}
