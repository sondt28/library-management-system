package com.son.librarymanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.son.librarymanagementsystem.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
