package com.Lunexconline.Ecommerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;

    private double price;

    private int count;

    @ElementCollection
    private List<String> imagePaths;

    public Product(String name, double price, int count){
        this.name = name;
        this.price = price;
        this.count = count;
    }
    public List<String> getImagePaths(){
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths){
        this.imagePaths=imagePaths;
    }

    public String setName (String name){
        return this.name = name;
    }

    public String getName(){return name;}

    public double getPrice(){return price;}

    public double setPrice(double price){return this.price = price;}

}
