package com.Lunexconline.Ecommerce.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class Product {
    @Id
    private long id;
    private String name;
    private int cost;
    private int count;
    @ElementCollection
    private List<String> imagePaths;
    Product(String name, int cost, int count){
        this.name = name;
        this.cost = cost;
        this.count = count;
    }
    public List<String> getImagePaths(){
        return imagePaths;
    }
    public void setImagePaths(List<String> imagePaths){
        this.imagePaths=imagePaths;
    }
}
