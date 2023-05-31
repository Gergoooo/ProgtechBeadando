package com.gergo.alkalmazas.model;

import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.model.enums.PublisherConverter;

import javax.persistence.*;

@Entity
@Table(name="kamerahaz")
public class KameraHaz {

    public KameraHaz() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "publisher")
    @Convert(converter= PublisherConverter.class)
    private Publisher publisher;

    @Column(name="price")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}