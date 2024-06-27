package com.ecommerce.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.request.CreateCouponReqDto;
import com.ecommerce.api.dto.request.UpdateCouponReqDto;
import com.ecommerce.api.model.Coupon;
import com.ecommerce.api.model.User;
import com.ecommerce.api.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
    final CouponRepository couponRepository;
    final ModelMapper modelMapper;

    private Optional<Coupon> getCouponByCode(String code){
        List<Coupon> existingCoupons=couponRepository.findByCode(code);
        if(existingCoupons.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(existingCoupons.get(0));
    }

    public Coupon createCoupon(CreateCouponReqDto createCouponReqDto) throws Exception{
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Coupon> existingCoupon=getCouponByCode(createCouponReqDto.getCode());
        if(existingCoupon.isPresent()){
            throw new Exception("Coupon already exist");
        }
        Coupon coupon=modelMapper.map(createCouponReqDto, Coupon.class);
        coupon.setUser(user);
        return couponRepository.insert(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

    public Coupon getCoupon(String code) throws Exception{
        Optional<Coupon> existingCoupon=getCouponByCode(code);
        if(existingCoupon.isEmpty()){
            throw new Exception("Coupon does not exist");
        }
        return existingCoupon.get();
    }

    public Coupon updateCoupon(UpdateCouponReqDto updateCouponReqDto) throws Exception{
        Optional<Coupon> existingCoupon=getCouponByCode(updateCouponReqDto.getCode());
        if(existingCoupon.isEmpty()){
            throw new Exception("coupon does now exist");
        }
        modelMapper.map(updateCouponReqDto, existingCoupon.get());
        return couponRepository.save(existingCoupon.get());
    }

    public String deleteCoupon(String code) throws Exception{
        Optional<Coupon> existingCoupon=getCouponByCode(code);
        if(existingCoupon.isEmpty()){
            throw new Exception("coupon not present");
        }
        couponRepository.delete(existingCoupon.get());
        return "coupon deleted";
    }
}
