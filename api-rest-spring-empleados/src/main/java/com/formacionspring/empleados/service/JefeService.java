package com.formacionspring.empleados.service;

import java.util.List;

import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Jefe;

public interface JefeService {
	//GUARDAR EN ARRAYLIST EL JEFE
	public List<Jefe> findAll();
	
	//BUSCAR ID
	public Jefe findById(Long id);
	
	//BORRAR JEFE
	public void delete (Long id);
	
	//GUARDAR JEFE PARA ACTUALIZAR
	public Jefe save(Jefe jefe);
	
	//BUSCAR DEPARTAMENTO 
	public List<Departamento> findAllDepartamentos();
}
