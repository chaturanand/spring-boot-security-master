package com.sebasira.MediumTutorialSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebasira.MediumTutorialSecurity.model.Book;

/**
 * @author Chaturanand Yadav
 * @version 1.0.0
 * @since 2020-01-14
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
