package org.paolino.sb2026.services;

import org.paolino.sb2026.models.Product;
import org.paolino.sb2026.payloads.ProductDTO;
import org.paolino.sb2026.payloads.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
    ProductResponse getAllProducts();
    ProductResponse searchByCategory(Long categoryId);
    ProductResponse searchProductByKeyword(String keyword);
    ProductDTO updateProduct(Long productId, Product product);
    ProductDTO deleteProduct(Long productId);
}
