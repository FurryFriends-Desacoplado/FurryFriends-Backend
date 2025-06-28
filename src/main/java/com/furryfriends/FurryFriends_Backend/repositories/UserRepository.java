package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNumeroDocumento(String numeroDocumento);
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET enrollment_status = CAST(:status AS enrollment_status_enum) WHERE id = :userId", nativeQuery = true)
    int updateEnrollmentStatus(Long userId, String status);
}
