package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import klu.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query("SELECT COUNT(u) FROM Users u WHERE u.email = ?1")
    int validateEmail(String email);

    @Query("SELECT COUNT(u) FROM Users u WHERE u.email = ?1 AND u.password = ?2")
    int validateCredentials(String email, String password);
}
