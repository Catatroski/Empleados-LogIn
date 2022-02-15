package com.formacionspring.empleados.service;

import java.util.List;

import com.formacionspring.empleados.entity.Usuario;


public interface UsuarioService {
	
	//GUARDAR EN ARRAYLIST EL USUARIO
	public List<Usuario> findAll();
	
	//BUSCAR ID USUARIO
	public Usuario findById(Long id);
	
	//GUARDAR USUARIO PARA ACTUALIZAR
	public Usuario save(Usuario usuario);
}
