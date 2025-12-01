package com.example.demo.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.repository.entity.Arquivo;

public interface ArquivoRepository 
    extends ListCrudRepository<Arquivo, Long> {

}
