package org.paolino.sb2026.services;

import org.paolino.sb2026.payload.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
