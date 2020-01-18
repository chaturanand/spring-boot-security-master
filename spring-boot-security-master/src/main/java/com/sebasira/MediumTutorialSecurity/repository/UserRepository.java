package com.sebasira.MediumTutorialSecurity.repository;

import com.sebasira.MediumTutorialSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chaturanand Yadav
 * @version 1.0.0
 * @since 2020-01-14
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
