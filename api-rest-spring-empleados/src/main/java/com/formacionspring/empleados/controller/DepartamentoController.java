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

import com.formacionspring.empleados.entity.Departamento;
import com.formacionspring.empleados.service.DepartamentoService;


@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;
	
	
	
	//------------------------------------------------
	
	//VISUALIZAR TODOS LOS DEPARTAMENTOS
	
	
	@GetMapping("/departamentos")
	public List<Departamento> departamento() {
		return servicio.findAll();
		
	}
	
	//------------------------------------------------
	

	//VISUALIZAR EMPLEADOS POR ID CON ERRORES
	
	
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> departamentosShow(@PathVariable Long id){
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			departamento = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (departamento == null) {
			response.put("Mensaje, ","El departamento con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento	, HttpStatus.OK);
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	//INSERTAR DEPARTAMENTOS
	
	
		@PostMapping("/departamentos")
		public ResponseEntity<?> saveJefe(@RequestBody Departamento departamento) {
			Departamento departamentoNew = null;
			Map<String, Object> response = new HashMap<>();
			
			try {
				departamentoNew = servicio.save(departamento);
			} catch (DataAccessException e) {
				response.put("Mensaje", "Error al realizar insert a la base de datos :(");
				response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("Mensaje","El departamento ha sido creado correctamente :D");
			response.put("Empleado", departamentoNew);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}
		
		//----------------------------------------------------------------------------------------------------------------------------
		
		
		// ELIMINAR DEPARTAMENTOS
		
		@DeleteMapping("/departamentos/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> deleteDepartamentoMostrado(@PathVariable  Long id) {
			Departamento departamentoBorrado= servicio.findById(id);
			Map<String, Object> response = new HashMap<>();
			
			try {	
				
				if (departamentoBorrado == null) {
					response.put("Mensaje, ","El departamento con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
				
				servicio.delete(id);
				
			}
				catch (DataAccessException e) {
				response.put("Mensaje", "Error al borrar en la base de datos :(");
				response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			
			response.put("Mensaje","El departamento ha sido borrado :D");
			response.put("cliente", departamentoBorrado);
			 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		
		//----------------------------------------------------------------------------------------------------------------------------
		
		//ACTUALIZAR DEPARTAMENTOS
		
		
		@PutMapping("/departamentos/{id}")
		public ResponseEntity<?> updateEmpleadoe(@RequestBody Departamento departamento, @PathVariable Long id) {
			
			Departamento departamentoUpdated = servicio.findById(id);
			Map<String, Object> response = new HashMap<>();
			
			try {
				departamentoUpdated.setNombre(departamento.getNombre());
				departamentoUpdated.setUbicacion(departamento.getUbicacion());
				
				servicio.save(departamentoUpdated);
				
			} catch (DataAccessException e) {
				response.put("Mensaje", "Error al actualizar en la base de datos :(");
				response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("Mensaje","El departamento ha sido actualizado :D");
			response.put("cliente", departamentoUpdated);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		}
		
		//-----------------------------------------------------------------------------------------------------------------------------
}
	
	
