package com.hassan.ecomerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hassan.ecomerce.model.Role;
import com.hassan.ecomerce.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
 Optional<Role> findById(Long id);

}
