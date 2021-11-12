package shoppingcart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;

@Document
public class ShoppingCart {
    @Id
    private int cartId;

    ArrayList<CartLine> cartLineList = new ArrayList<CartLine>();

    public void addToCart(Product product, int quantity) {
        for (CartLine cline : cartLineList) {
            if(cline.getProduct().getProductNumber()== product.getProductNumber()) {
                cline.setQuantity(cline.getQuantity()+quantity);
                return;
            }
        }
        CartLine cline = new CartLine();
        cline.setProduct(product);
        cline.setQuantity(quantity);
        cartLineList.add(cline);
    }

    public void changeQuantity(Product product, int quantity) {
        for (CartLine cline : cartLineList) {
            if(cline.getProduct().getProductNumber()== product.getProductNumber()) {
                cline.setQuantity(quantity);
                return;
            }
        }
    }


    public void print() {
        System.out.println("Content of the shoppingcart:");
        for (CartLine cline : cartLineList) {
            System.out.println(cline.getQuantity() + " "
                    + cline.getProduct().getProductNumber() + " "
                    + cline.getProduct().getDescription() + " "
                    + cline.getProduct().getPrice());
        }
        System.out.println("Total price ="+getTotalPrice());
    }

    public double getTotalPrice(){
        double totalPrice = 0.0;
        for (CartLine cline : cartLineList) {
            totalPrice=totalPrice+(cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }

    public void removeFromCart(Product product){
        Iterator<CartLine> iter = cartLineList.iterator();
        while (iter.hasNext()){
            CartLine cline = iter.next();
            if (cline.getProduct().getProductNumber() == product.getProductNumber()){
                iter.remove();
//                if (cline.getQuantity()>1){
//                    cline.setQuantity(cline.getQuantity()-1);
//                }
//                else{
//                    iter.remove();
//                }
            }
        }
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public ArrayList<CartLine> getCartLineList() {
        return cartLineList;
    }

    public void setCartLineList(ArrayList<CartLine> cartLineList) {
        this.cartLineList = cartLineList;
    }

}
