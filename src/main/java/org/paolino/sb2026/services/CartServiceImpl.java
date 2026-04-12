package org.paolino.sb2026.services;

import org.modelmapper.ModelMapper;
import org.paolino.sb2026.exceptions.APIException;
import org.paolino.sb2026.exceptions.ResourceNotFoundException;
import org.paolino.sb2026.model.Cart;
import org.paolino.sb2026.model.CartItem;
import org.paolino.sb2026.model.Product;
import org.paolino.sb2026.payload.CartDTO;
import org.paolino.sb2026.payload.ProductDTO;
import org.paolino.sb2026.repositories.CartItemRepository;
import org.paolino.sb2026.repositories.CartRepository;
import org.paolino.sb2026.repositories.ProductRepository;
import org.paolino.sb2026.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {
        Cart cart  = createCart();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(productId, cart.getCartId());
        if (cartItem != null) {
            throw new APIException("Product " + product.getProductName() + " already exists in the cart");
        }
        if (product.getQuantity() == 0) {
            throw new APIException(product.getProductName() + " is not available");
        }
        if (product.getQuantity() < quantity) {
            throw new APIException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());
        cartItemRepository.save(newCartItem);
        // product.setQuantity(product.getQuantity() - quantity);
        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));
        cartRepository.save(cart);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        List<CartItem> cartItems = cart.getCartItems();
        Stream<ProductDTO> productStream = cartItems.stream().map(item -> {
            ProductDTO map = modelMapper.map(item.getProduct(), ProductDTO.class);
            map.setQuantity(item.getQuantity());
            return map;
        });
        cartDTO.setProducts(productStream.toList());
        return cartDTO;
    }

    private Cart createCart() {
        Cart userCart  = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if (userCart != null) {
            return userCart;
        }
        Cart cart = new Cart();
        cart.setUser(authUtil.loggedInUser());
        cart.setTotalPrice(0.00);
        return cartRepository.save(cart);
    }
}
