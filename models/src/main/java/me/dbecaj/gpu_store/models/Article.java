package me.dbecaj.gpu_store.models;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
public class Article {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String imageURL;

    private Double price;

    private Integer rating;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
