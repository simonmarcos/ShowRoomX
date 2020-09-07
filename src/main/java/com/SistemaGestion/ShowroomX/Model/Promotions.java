package com.SistemaGestion.ShowroomX.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Promotions")
public class Promotions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromotions;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @JoinTable(name = "BandPromotions", joinColumns = @JoinColumn(name = "FK_PROMOTIONS", nullable = false), inverseJoinColumns = @JoinColumn(name = "FK_BRAND", nullable = false))

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Brand> brand;

    public Promotions() {
    }

    public Promotions(long idPromotions, String name, Set<Brand> brand) {
        this.idPromotions = idPromotions;
        this.name = name;
        this.brand = brand;
    }

    public long getIdPromotions() {
        return idPromotions;
    }

    public void setIdPromotions(long idPromotions) {
        this.idPromotions = idPromotions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Brand> getBrand() {
        return brand;
    }

    public void setBrand(Set<Brand> brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Promotions{" +
                "idPromotions=" + idPromotions +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                '}';
    }
}
