package com.SistemaGestion.ShowroomX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Brand", schema = "distributor_db")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idBrand")
public class Brand implements Serializable, Comparable<Brand> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBrand;

    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @NotNull
    @Column(name = "stock", nullable = false)
    private int stock;

    @NotNull
    @Column(name = "purchaseAmount", nullable = false)
    private double purchaseAmount;

    @NotNull
    @Column(name = "unitSaleAmount", nullable = false)
    private double unitSaleAmount;

    @Column(name = "promotionSaleAmount")
    private double promotionSaleAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_PROVIDER", nullable = false)
    @JsonManagedReference
    private Provider provider;

    @ManyToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Sales> sales;

    @ManyToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Promotions> promotions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Purchases> purchases;

    public Brand(long idBrand, @NotNull String name, @NotNull int stock, @NotNull double purchaseAmount, @NotNull double unitSaleAmount, double promotionSaleAmount, Provider provider, Set<Sales> sales, Set<Promotions> promotions, Set<Purchases> purchases) {
        this.idBrand = idBrand;
        this.name = name;
        this.stock = stock;
        this.purchaseAmount = purchaseAmount;
        this.unitSaleAmount = unitSaleAmount;
        this.promotionSaleAmount = promotionSaleAmount;
        this.provider = provider;
        this.sales = sales;
        this.promotions = promotions;
        this.purchases = purchases;
    }

    public Brand() {
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public double getUnitSaleAmount() {
        return unitSaleAmount;
    }

    public void setUnitSaleAmount(double unitSaleAmount) {
        this.unitSaleAmount = unitSaleAmount;
    }

    public double getPromotionSaleAmount() {
        return promotionSaleAmount;
    }

    public void setPromotionSaleAmount(double promotionSaleAmount) {
        this.promotionSaleAmount = promotionSaleAmount;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    public Set<Promotions> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotions> promotions) {
        this.promotions = promotions;
    }

    public Set<Purchases> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchases> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;
        Brand brand = (Brand) o;
        return idBrand == brand.idBrand &&
                Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBrand, name);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "idBrand=" + idBrand +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", purchaseAmount=" + purchaseAmount +
                ", unitSaleAmount=" + unitSaleAmount +
                ", promotionSaleAmount=" + promotionSaleAmount +
                ", provider=" + provider +
                ", sales=" + sales +
                ", promotions=" + promotions +
                ", purchases=" + purchases +
                '}';
    }

    @Override
    public int compareTo(Brand o) {
        return this.getName().compareTo(o.getName());
    }
}
