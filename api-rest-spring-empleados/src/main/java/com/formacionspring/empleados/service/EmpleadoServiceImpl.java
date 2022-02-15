package com.formacionspring.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.empleados.dao.EmpleadoDao;
import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDao dao;
	
	@Override 	//ARRAY TODOS LOS EMPLEADOS
	public List<Empleado> findAll() {
		return (List<Empleado>) dao.findAll();
	}

	
	@Override 	//EMPLEADOS BUSCADO POR ID
	public Empleado findById(Long id) {
		return  dao.findById(id).orElse(null);
	}

	@Override	//EMPLEADO BORRADO POR ID
	public void delete(Long id) {
		dao.deleteById(id);	
	}

	@Override	//METODO PARA GUARDAR EMPLEADOS
	public Empleado save(Empleado empleado) {
		return dao.save(empleado);
	}

	@Override	//ARRAY TODAS LOS DEPARTAMENTOS
	public List<Departamento> findAllDepartamentos() {
		return dao.findAllDepartamentos();
	}
	
}