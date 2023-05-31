package com.gergo.alkalmazas.model;

import com.gergo.alkalmazas.model.enums.Publisher;
import com.gergo.alkalmazas.model.enums.PublisherConverter;

import javax.persistence.*;

@Entity
@Table(name="optika")
public class Optika {

    public Optika() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "compability")
    @Convert(converter= PublisherConverter.class)
    private Publisher compability;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Publisher getCompability() {
        return compability;
    }

    public void setCompability(Publisher compability) {
        this.compability = compability;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
