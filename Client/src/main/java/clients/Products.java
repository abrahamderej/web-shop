package clients;

import java.util.Collection;

public class Products {
    private Collection<ProductDTO> products;

    public Products() {
    }

    public Products(Collection<ProductDTO> products) {
        this.products = products;
    }

    public Collection<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}
