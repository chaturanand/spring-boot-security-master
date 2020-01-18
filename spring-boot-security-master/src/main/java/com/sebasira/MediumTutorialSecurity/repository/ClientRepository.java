package com.sebasira.MediumTutorialSecurity.repository;

import com.sebasira.MediumTutorialSecurity.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Chaturanand Yadav
 * @version 1.0.0
 * @since 2020-01-14
 */
@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(long id);
}
