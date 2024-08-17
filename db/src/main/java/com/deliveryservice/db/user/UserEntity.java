package com.deliveryservice.db.user;


import com.deliveryservice.db.BaseEntity;
import com.deliveryservice.db.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(name = "registered at")
    private LocalDateTime registeredAt;

    @Column(name = "unregistered at")
    private LocalDateTime unregisteredAt;

    @Column(name = "last login at")
    private LocalDateTime lastLoginAt;
}
