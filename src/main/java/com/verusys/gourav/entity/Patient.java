package com.verusys.gourav.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pat_tab")
public class Patient {
	@Id
	@GeneratedValue
	@Column(name = "pat_id")
	private Long id;
	
	@Column(name = "pat_first_name_col")
	private String firstName;
	
	@Column(name = "pat_last_name_col")
	private String lastName;
	
	@Column(name = "pat_gender_col")
	private String gender;
	
	@Column(name = "pat_phone_col")
	private String phone;
	
	@Column(name = "pat_email_col")
	private String email;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name = "pat_dob_col")
	private Date dateOfBirth;
	
	@Column(name = "pat_ms_col")
	private String maritalStatus;
	
	@Column(name = "pat_padd_col")
	private String presentAddr;
	
	@Column(name = "pat_cadd_col")
	private String commAddr;
	
	@ElementCollection
	@CollectionTable(name = "pat_medi_hist_tab",
								joinColumns = @JoinColumn(name="pat_medi_hist_id_fk_col"))
	@Column(name = "pat_medi_hist_col")
	private Set<String> pastMedicalHistory;
	
	@Column(name = "pat_other_col")
	private String ifOther;
	
	@Column(name = "pat_other_details_col")
	private String otherDatails;
}
