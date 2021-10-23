package br.com.digital.demoproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.demoproduct.entitys.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
