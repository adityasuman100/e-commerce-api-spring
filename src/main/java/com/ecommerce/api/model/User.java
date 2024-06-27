package com.ecommerce.api.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    String _id;

    String fullname;

    @Indexed(unique = true)
    String email;

    String password;

    @DocumentReference
    List<Order> orders;

    boolean isAdmin;
    boolean hasShippingAddress;

    ShippingAddress shippingAddress;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.isAdmin) {
            return List.of(new SimpleGrantedAuthority("ADMIN"));
        }
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
