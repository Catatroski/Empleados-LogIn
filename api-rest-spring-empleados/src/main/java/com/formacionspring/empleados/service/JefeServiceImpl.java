package com.formacionspring.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.empleados.dao.JefeDao;
import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.entity.Jefe;


@Service
public class JefeServiceImpl implements JefeService {

	@Autowired
	private JefeDao dao;
	
	@Override 	//ARRAY TODOS LOS JEFES
	public List<Jefe> findAll() {
		return (List<Jefe>) dao.findAll();
	}

	
	@Override 	//JEFES BUSCADO POR ID
	public Jefe findById(Long id) {
		return  dao.findById(id).orElse(null);
	}

	@Override	//JEFE BORRADO POR ID
	public void delete(Long id) {
		dao.deleteById(id);	
	}

	@Override	//METODO PARA GUARDAR JEFES
	public Jefe save(Jefe jefe) {
		return dao.save(jefe);
	}

	@Override	//ARRAY TODAS LOS DEPARTAMENTOS
	public List<Departamento> findAllDepartamentos() {
		return dao.findAllDepartamentos();
	}
}
