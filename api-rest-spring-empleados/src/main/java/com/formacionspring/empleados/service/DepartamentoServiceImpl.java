package com.formacionspring.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.empleados.dao.DepartamentoDao;
import com.formacionspring.empleados.entity.Departamento;


@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDao dao;
	
	@Override 	//ARRAY TODOS LOS DEPARTAMENTOS
	public List<Departamento> findAll() {
		return (List<Departamento>) dao.findAll();
	}

	
	@Override 	//DEPARTAMENTO BUSCADO POR ID
	public Departamento findById(Long id) {
		return  dao.findById(id).orElse(null);
	}

	@Override	//DEPARTAMENTOS BORRADO POR ID
	public void delete(Long id) {
		dao.deleteById(id);	
	}

	@Override	//METODO PARA GUARDAR DEPARTAMENTOS
	public Departamento save(Departamento jefe) {
		return dao.save(jefe);
	}

}
