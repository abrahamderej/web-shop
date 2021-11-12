package shoppingcart.service;


import shoppingcart.domain.Product;

public class ProductAdapter {
	public static Product getProduct(ProductDTO productDTO) {
		Product product = new Product(
				productDTO.getProductNumber(),
				productDTO.getName(),
				productDTO.getPrice(),
				productDTO.getDescription(),
				productDTO.getNumberInStock()
				);		
		return product;				
	}
	
	public static ProductDTO getProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO(
				product.getProductNumber(),
				product.getName(),
				product.getPrice(),
				product.getDescription(),
				product.getNumberInStock()
				);		
		return productDTO;				
	}
}
