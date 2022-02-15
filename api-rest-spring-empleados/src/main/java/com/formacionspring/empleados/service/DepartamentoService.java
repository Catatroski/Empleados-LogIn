package com.formacionspring.empleados.service;

import java.util.List;

import com.formacionspring.empleados.entity.Departamento;

public interface DepartamentoService {
	//GUARDAR EN ARRAYLIST EL DEPARTAMENTOS
	public List<Departamento> findAll();
	
	//BUSCAR ID
	public Departamento findById(Long id);
	
	//BORAR ARTICULO
	public void delete (Long id);
	
	//GUARDAR ARTICULO PARA ACTUALIZAR
	public Departamento save(Departamento departamento);
	
}