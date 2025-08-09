
package com.watchlux.service;

import com.watchlux.dto.CheckoutRequest;
import com.watchlux.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    OrderDTO checkout(CheckoutRequest req);
    List<OrderDTO> listByEmail(String email);
    List<OrderDTO> listAll();
}
