package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "result_eco_footprint")
public class ResultEcoFootprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private  String username;
    @Column(name = "result")
    private BigDecimal result;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @PrePersist
    protected void onCreate() {
        // Set the default value to the current date when a new entity is persisted
        date = Calendar.getInstance().getTime();
    }

    @Column(name = "user_id")
    private String user_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users users;
}
