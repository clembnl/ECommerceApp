package com.ecommerce.api.controller;

import com.ecommerce.api.exception.AuthenticationFailException;
import com.ecommerce.api.exception.WishListItemNotExistException;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.model.User;
import com.ecommerce.api.model.WishList;
import com.ecommerce.api.service.AuthenticationService;
import com.ecommerce.api.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{token}")
    public ResponseEntity<List<WishList>> getWishList(@PathVariable("token") String token) {
        int user_id = authenticationService.getUser(token).getId();
        List<WishList> wishList = wishListService.readWishList(user_id);
        return new ResponseEntity<List<WishList>>(wishList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = new WishList(user, product);
        wishListService.createWishlist(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{wishlistItemId}")
    public ResponseEntity<ApiResponse> deleteWishListItem(@PathVariable("wishlistItemId") int itemID,
                                                      @RequestParam("token") String token)
            throws AuthenticationFailException, WishListItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        wishListService.deleteWishListItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"),
                HttpStatus.OK);
    }
}
