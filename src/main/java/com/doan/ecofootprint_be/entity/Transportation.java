package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transportation")
public class Transportation {
    @Id
    @Column(name = "id")
    private int id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "transport_mode")
    private  String transportMode;

    @Column(name = "distance")
    private BigDecimal distance;

    @Column(name = "unit")
    private  String unit;

    @Column(name = "description")
    private  String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
