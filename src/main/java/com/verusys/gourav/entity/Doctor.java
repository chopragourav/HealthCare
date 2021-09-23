package com.verusys.gourav.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor_tab")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id_col")
	private Long id;
	
	@Column(name = "doc_fn_col")
	private String firstName;
	
	@Column(name = "doc_ln_col")
	private String lastName;
	
	@Column(name = "doc_email_col")
	private String email;
	
	@Column(name = "doc_addrs_col")
	private String address;
	
	@Column(name = "doc_mobile_col")
	private Long mobile;
	
	@Column(name = "doc_gender_col")
	private String gender;
	
	@Column(name = "doc_note_col")
	private String note;
	
	@Column(name = "doc_image_col")
	private String photos;

	@Transient
	private String photosImagePath;

	public String getPhotosImagePath() {
		if (photos == null || id == null) return null;
		return "/user-photos/" + id + "/" + photos;
		}
}

