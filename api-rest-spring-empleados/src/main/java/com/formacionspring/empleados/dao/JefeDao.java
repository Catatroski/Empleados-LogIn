package com.formacionspring.empleados.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Jefe;


@Repository
public interface JefeDao extends CrudRepository<Jefe, Long> {
	@Query("from Departamento")
	public List<Departamento> findAllDepartamentos();
}
