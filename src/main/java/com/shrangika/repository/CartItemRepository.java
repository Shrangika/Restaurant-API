package com.shrangika.repository;

import com.shrangika.model.Cart;
import com.shrangika.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
