package com.app.sso.repository;

import com.app.sso.bean.User;
import org.opensaml.saml2.core.NameID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
