package shoppingcart.web;

import shoppingcart.domain.Product;

import java.util.Collection;

public class Products {
    private Collection<Product> products;

    public Products() {
    }

    public Products(Collection<Product> products) {
        this.products = products;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}
