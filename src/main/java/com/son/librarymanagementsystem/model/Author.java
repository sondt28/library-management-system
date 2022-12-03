package com.son.librarymanagementsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
							CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	@JoinTable(name = "book_author", 
			   joinColumns = @JoinColumn(name = "author_id"),
			   inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<Book> books = new HashSet<>();
}
