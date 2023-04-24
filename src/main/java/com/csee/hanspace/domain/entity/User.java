package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authName;

    private boolean approve;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserSite> userSiteList = new ArrayList<>();

    @OneToOne(mappedBy="user")
    private SavedUserInfo savedUserInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();

}
