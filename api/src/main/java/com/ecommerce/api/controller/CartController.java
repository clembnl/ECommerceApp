package com.ecommerce.api.controller;

import com.ecommerce.api.dto.cart.AddToCartDto;
import com.ecommerce.api.dto.cart.CartDto;
import com.ecommerce.api.exception.AuthenticationFailException;
import com.ecommerce.api.exception.CartItemNotExistException;
import com.ecommerce.api.exception.ProductNotExistException;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.CartService;
import com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 Authentication authentication)
            throws AuthenticationFailException, ProductNotExistException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Product product = productService.getProductById(addToCartDto.getProductId());
        System.out.println("product to add"+  product.getName());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"),
                HttpStatus.CREATED);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<CartDto> getCartItems(Authentication authentication)
            throws AuthenticationFailException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody AddToCartDto cartDto,
                                                      Authentication authentication)
            throws AuthenticationFailException,ProductNotExistException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,
                                                      Authentication authentication)
            throws AuthenticationFailException, CartItemNotExistException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        cartService.deleteCartItem(itemID, user.getId());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"),
                HttpStatus.OK);
    }
}
