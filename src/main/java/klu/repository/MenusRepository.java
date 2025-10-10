package klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.model.Menus;

@Repository
public interface MenusRepository extends JpaRepository<Menus, Long> {
    List<Menus> findByRole(String role);
}
