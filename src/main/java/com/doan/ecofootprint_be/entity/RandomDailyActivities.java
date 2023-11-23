package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "random_daily_activities")
public class RandomDailyActivities {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "activity_time")
    private Date activityTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
