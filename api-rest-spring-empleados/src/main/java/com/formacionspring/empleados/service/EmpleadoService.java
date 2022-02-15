package com.formacionspring.empleados.service;

import java.util.List;

import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Empleado;

public interface EmpleadoService {
	//GUARDAR EN ARRAYLIST EL EMPLEADO
	public List<Empleado> findAll();
	
	//BUSCAR ID EMPLEADO
	public Empleado findById(Long id);
	
	//BORRAR EMPLEADO
	public void delete (Long id);
	
	//GUARDAR EMPLEADO PARA ACTUALIZAR
	public Empleado save(Empleado empleado);
	
	//BUSCAR DEPARTAMENTO 
	public List<Departamento> findAllDepartamentos();
}
