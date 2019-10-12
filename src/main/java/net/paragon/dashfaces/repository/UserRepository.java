package net.paragon.dashfaces.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.paragon.dashfaces.domain.entity.UserProfile;

public interface UserRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUsername(String username);
}
