package com.example.authenticationservice.repository.user;

import com.example.authenticationservice.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = """
            SELECT u.id, u.username, u.password, u.role
            FROM authentication.users u
            WHERE u.username = :username
            """)
    Optional<User> findByUsername(@Param(value = "username") String username);

    boolean existsByUsername(String username);
}
