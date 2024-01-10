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
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
//    @Column(name = "stt")
//    private  int stt;
    @Column(name = "username")
    private  String username;
    @Column(name = "total_points")
    private BigDecimal total_points;
    @Column(name = "title")
    private  String title;
    @Column(name = "user_id")
    private Long userid;

//    public Ranking(Long userId, BigDecimal zero) {
//    }
//public Ranking(Long userId, String username, BigDecimal totalPoints, String title) {
//    // Initialize fields
//
//}

    public Ranking(Long userid,String username, BigDecimal total_points, String title) {
        this.userid = userid;
        this.username = username;
        this.total_points = total_points;
        this.title = title;

    }
}
