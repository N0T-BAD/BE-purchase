package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "profile_skin")
public class ProfileSkinEntity extends BaseEntity {

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
