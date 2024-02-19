package com.ecommerce.api.service;

import com.ecommerce.api.exception.WishListItemNotExistException;
import com.ecommerce.api.model.WishList;
import com.ecommerce.api.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }

    public void deleteWishListItem(int id, int userId) throws WishListItemNotExistException {
        if (!wishListRepository.existsById(id))
            throw new WishListItemNotExistException("Wishlist item id is invalid : " + id);
        wishListRepository.deleteById(id);
    }

}
