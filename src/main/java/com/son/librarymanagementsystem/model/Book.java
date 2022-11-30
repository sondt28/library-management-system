package com.son.librarymanagementsystem.model;

import java.util.Date;
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
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "publication_date")
	private Date publicationDate;
	
	@Column(name = "copies_owned")
	private int copiesOwned;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
				CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
						CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	@JoinTable(name = "book_author", 
				joinColumns = @JoinColumn(name = "book_id"),
				inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors;
	
	public void addAuthor(Author author) {
		if (authors == null) 
			authors = new HashSet<>();
		
		authors.add(author);
		author.getBooks().add(this);
	}
}
