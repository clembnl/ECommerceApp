package com.ecommerce.api.controller;

import com.ecommerce.api.exception.AuthenticationFailException;
import com.ecommerce.api.exception.WishListItemNotExistException;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.model.User;
import com.ecommerce.api.model.WishList;
import com.ecommerce.api.repository.UserRepository;
import com.ecommerce.api.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<List<WishList>> getWishList(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<WishList> wishList = wishListService.readWishList(user.getId());
        return new ResponseEntity<List<WishList>>(wishList, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product,
                                                   Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        WishList wishList = new WishList(user, product);
        wishListService.createWishlist(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{wishlistItemId}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<ApiResponse> deleteWishListItem(@PathVariable("wishlistItemId") int itemID,
                                                          Authentication authentication)
            throws AuthenticationFailException, WishListItemNotExistException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        wishListService.deleteWishListItem(itemID, user.getId());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"),
                HttpStatus.OK);
    }
}
