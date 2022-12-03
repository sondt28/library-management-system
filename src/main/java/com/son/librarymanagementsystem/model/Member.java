package com.son.librarymanagementsystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "joined_date")
	private Date joinedDate;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST,CascadeType.REFRESH},
				fetch = FetchType.LAZY,
				mappedBy = "member")
	private List<Loan> loans;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "active_status_id")
	private MemberStatus status;
}
