package com.formacionspring.empleados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.empleados.entity.Jefe;
import com.formacionspring.empleados.service.JefeService;


@RestController
@RequestMapping("/api")
public class JefeController {
	
	@Autowired
	private JefeService servicio;
	
	//------------------------------------------------
	
	//VISUALIZAR TODOS LOS JEFES
	
	
	@GetMapping("/jefes")
	public List<Jefe> jefe() {
		return servicio.findAll();
		
	}
	
	//------------------------------------------------
	

	//VISUALIZAR EMPLEADOS POR ID CON ERRORES
	
	
	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> jefesShow(@PathVariable Long id){
		Jefe jefes = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefes = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (jefes == null) {
			response.put("Mensaje, ","El jefe con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Jefe>(jefes	, HttpStatus.OK);
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	//INSERTAR JEFES
	
	
	@PostMapping("/jefes")
	public ResponseEntity<?> saveJefe(@RequestBody Jefe jefe) {
		Jefe jefeNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefeNew = servicio.save(jefe);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El jefe ha sido creado correctamente :D");
		response.put("Empleado", jefeNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	// ELIMINAR JEFES
	
	@DeleteMapping("/jefes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteJefeMostrado(@PathVariable  Long id) {
		Jefe jefeBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (jefeBorrado == null) {
				response.put("Mensaje, ","El jefe con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El jefe ha sido borrado :D");
		response.put("cliente", jefeBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	//ACTUALIZAR JEFES
	
	
	@PutMapping("/jefe/{id}")
	public ResponseEntity<?> updateEmpleadoe(@RequestBody Jefe jefe, @PathVariable Long id) {
		
		Jefe jefeUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefeUpdated.setNombre(jefe.getNombre());
			jefeUpdated.setSalario(jefe.getSalario());
			jefeUpdated.setTelefono(jefe.getTelefono());
			jefeUpdated.setDepartamento(jefe.getDepartamento());
			
			servicio.save(jefeUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El jefe ha sido actualizado :D");
		response.put("cliente", jefeUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
}
	
	
	
