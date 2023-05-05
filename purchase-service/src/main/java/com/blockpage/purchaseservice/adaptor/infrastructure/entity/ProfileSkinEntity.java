package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "profile_skin")
public class ProfileSkinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String profileSkinName;

    @Column
    private String profileSkinDescription;

    @Column
    private String profileSkinBlockPrice;

    @Column
    private String profileSkinImage;

}
