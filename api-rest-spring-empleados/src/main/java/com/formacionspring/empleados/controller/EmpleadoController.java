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

import com.formacionspring.empleados.entity.Empleado;
import com.formacionspring.empleados.service.EmpleadoService;



@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	
	
	//------------------------------------------------
	
	//VISUALIZAR TODOS LOS EMPLEADOS
	
	
	@GetMapping("/empleados")
	public List<Empleado> empleado() {
		return servicio.findAll();
		
	}
	
	//------------------------------------------------
	

	//VISUALIZAR EMPLEADOS POR ID CON ERRORES
	
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> empleadosShow(@PathVariable Long id){
		Empleado empleados = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleados = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (empleados == null) {
			response.put("Mensaje, ","El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleados	, HttpStatus.OK);
		
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------

	//INSERTAR EMPLEADOS
	
	
	@PostMapping("/empleados")
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado) {
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoNew = servicio.save(empleado);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El empleado ha sido creado correctamente :D");
		response.put("Empleado", empleadoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------


	// ELIMINAR EMPLEADOS
	
	@DeleteMapping("/empleados/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteEmpleadoMostrado(@PathVariable  Long id) {
		Empleado empleadoBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (empleadoBorrado == null) {
				response.put("Mensaje, ","El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El empleado ha sido borrado :D");
		response.put("cliente", empleadoBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	//ACTUALIZAR EMPLEADOS
	
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> updateEmpleadoe(@RequestBody Empleado empleado, @PathVariable Long id) {
		
		Empleado empleadoUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoUpdated.setNombre(empleado.getNombre());
			empleadoUpdated.setSalario(empleado.getSalario());
			empleadoUpdated.setTelefono(empleado.getTelefono());
			empleadoUpdated.setDepartamento(empleado.getDepartamento());
			
			servicio.save(empleadoUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El empleado ha sido actualizado :D");
		response.put("cliente", empleadoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
	
	


}
	
