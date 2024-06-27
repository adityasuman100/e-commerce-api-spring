package com.ecommerce.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.request.CreateOrderReqDto;
import com.ecommerce.api.model.Order;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final ModelMapper modelMapper;
    
    public Order createOrder(CreateOrderReqDto createOrderReqDto)throws Exception{
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.isHasShippingAddress()){
            throw new Exception("user does not have shipping address");
        }
        if(createOrderReqDto.getOrderItems().isEmpty()){
            throw new Exception("No Order Items");
        }
        Order order=modelMapper.map(createOrderReqDto, Order.class);
        order.setUser(user);
        return orderRepository.insert(order);
    }
}
