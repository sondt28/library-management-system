package com.son.librarymanagementsystem.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "loan_date")
	private LocalDateTime loanDate;
	
	@Column(name = "return_date")
	private Date returnDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "book_id")
	private Book book;
	
	@JoinColumn(name = "member_id")
	private Member member;	
}
