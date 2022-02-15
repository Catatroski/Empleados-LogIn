package com.formacionspring.empleados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.empleados.dao.UsuarioDao;
import com.formacionspring.empleados.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao dao;
	
	@Override 	//ARRAY TODOS LOS USUARIOS
	public List<Usuario> findAll() {
		return (List<Usuario>) dao.findAll();
	}
	
	@Override 	//EMPLEADOS BUSCADO POR ID
	public Usuario findById(Long id) {
		return  dao.findById(id).orElse(null);
	}
	
	@Override	//METODO PARA GUARDAR EMPLEADOS
	public Usuario save(Usuario usuario) {
		return dao.save(usuario);
	}
}
