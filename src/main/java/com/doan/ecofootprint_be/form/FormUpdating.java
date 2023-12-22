package com.doan.ecofootprint_be.form;

import com.doan.ecofootprint_be.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormUpdating {





	private String fullname;
	private String gender;
	private String address;
	private Date birthday;
	private String phone;



}
