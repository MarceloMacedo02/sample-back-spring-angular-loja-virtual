package br.com.digital.demoproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.demoproduct.entitys.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
