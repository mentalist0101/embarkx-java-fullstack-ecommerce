package org.paolino.sb2026.repositories;

import org.paolino.sb2026.model.AppRole;
import org.paolino.sb2026.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}
