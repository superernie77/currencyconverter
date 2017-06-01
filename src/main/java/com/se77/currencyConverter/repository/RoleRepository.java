package com.se77.currencyConverter.repository;

import com.se77.currencyConverter.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
    public Role findByRole(String role);
}