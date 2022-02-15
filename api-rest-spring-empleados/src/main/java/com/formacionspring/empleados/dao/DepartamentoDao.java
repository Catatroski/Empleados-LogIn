package com.formacionspring.empleados.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.empleados.entity.Departamento;



@Repository
public interface DepartamentoDao extends CrudRepository<Departamento, Long> {
}