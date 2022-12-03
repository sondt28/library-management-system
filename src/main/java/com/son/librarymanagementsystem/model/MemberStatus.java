package com.son.librarymanagementsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member_status")
public class MemberStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "status_value")
	private String statusValue;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST,CascadeType.REFRESH},
				fetch = FetchType.LAZY,
				mappedBy = "status")
	private Set<Member> members;
}
