package com.formacionspring.empleados.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Empleado;


@Repository
public interface EmpleadoDao extends CrudRepository<Empleado, Long> {
	@Query("from Departamento")
	public List<Departamento> findAllDepartamentos();
}
