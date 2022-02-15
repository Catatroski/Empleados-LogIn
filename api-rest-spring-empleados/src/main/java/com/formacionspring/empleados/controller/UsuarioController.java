package com.formacionspring.empleados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.empleados.entity.Usuario;
import com.formacionspring.empleados.service.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioService servicio;
	
	//------------------------------------------------
	
	//VISUALIZAR TODOS LOS USUARIOS
	
	
	@GetMapping("/usuarios")
	public List<Usuario> jefe() {
		return servicio.findAll();
		
	}
	
	//------------------------------------------------
	

	//VISUALIZAR USUARIOS POR ID CON ERRORES
	
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> jefesShow(@PathVariable Long id){
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (usuario == null) {
			response.put("Mensaje, ","El usuario con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario	, HttpStatus.OK);
		
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------

	
	//INICIO DE SESION
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestParam String usuarioEnviado, @RequestParam String password) {
		
		Map<String, Object> response = new HashMap<>();
		
		boolean log = false;
		boolean usuarioEncontrado = false;
		
		try {
			List<Usuario> usuariosList = servicio.findAll();
			for(Usuario usuario : usuariosList) {
				if(usuarioEnviado.equals(usuario.getUsuario())) {
					usuarioEncontrado = true;
					if(password.equals(usuario.getPassword())) {
						 log = true;
						 break;
					}
				}
				
			}
			
		} catch (Exception e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(log) {
			response.put("Mensaje", "Logeado! :D");
			response.put("Usuario", usuarioEnviado);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		} else if(usuarioEncontrado) {
			response.put("Mensaje", "Password incorrecta! :(");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("Mensaje", "Usuario incorrecto! :(");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	

	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------
}
	
	
