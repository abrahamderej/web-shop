package clients;

import java.util.ArrayList;

public class ShoppingCartDTO {
	private String cartid;
	private double totalPrice;
	private ArrayList<CartLineDTO> cartlineList = new ArrayList<CartLineDTO>();


	public void print() {
		System.out.println("Content of the shoppingcart:");
		for (CartLineDTO cline : cartlineList) {
			System.out.println("Product Quantity: " + cline.getQuantity() + " "
					+ cline.getProduct().getProductNumber() + " "
					+ cline.getProduct().getDescription() + " "
					+ cline.getProduct().getPrice());
		}
		System.out.println();
		System.out.println();
	}
	
	

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public ArrayList<CartLineDTO> getCartlineList() {
		return cartlineList;
	}

	public void setCartlineList(ArrayList<CartLineDTO> cartlineList) {
		this.cartlineList = cartlineList;
	}
	
	public void addCartLine(CartLineDTO cartLine) {
		cartlineList.add(cartLine);
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public double getTotalPrice() {
		return totalPrice;
	}
	
	

}
