package org.paolino.sb2026.repositories;

import org.paolino.sb2026.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
