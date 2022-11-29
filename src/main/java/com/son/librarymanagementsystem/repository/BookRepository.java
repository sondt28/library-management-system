package com.son.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.son.librarymanagementsystem.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
