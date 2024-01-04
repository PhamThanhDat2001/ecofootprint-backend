package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "information_second")
public class InfomationSecond {
    @Id
    @Column(name = "id")
    private int id;


    @Column(name = "title")
    private  String title;

    @Column(name = "description")
    private String description;



    @Column(name = "image_url")
    private  String image_url;
    @Column(name = "link_url")
    private  String link_url;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Users users;
}
