package br.com.neolog.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neolog.pojo.Product;

public interface ProductRepository
    extends
        JpaRepository<Product,Integer>
{

    Product findByCode(
        String code );

    Iterable<Product> findByCodeIn(
        Collection<String> codes );

    @Override
    List<Product> findAll();

}