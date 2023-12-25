package com.doan.ecofootprint_be.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormLog {
    private int id;

    private Date logTime;

    private  String footprintType;

    private  String logDescription;

}
