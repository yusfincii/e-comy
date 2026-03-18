package com.ecom.user.favourites.persistence.entity;

import com.ecom.user.users.persistence.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "FAVOURITES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favourites {

    @EmbeddedId
    private FavouritesId id;

    @ManyToOne
    @MapsId("userId")
    private Users user;

    // open then product service impl
    @ManyToOne
    @MapsId("productId")
    private Products product;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
