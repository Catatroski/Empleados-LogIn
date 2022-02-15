package com.formacionspring.empleados.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.empleados.entity.Usuario;


@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	
}
