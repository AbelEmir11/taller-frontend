package com.example.demo.repository;

import com.example.demo.model.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, Long> {

}