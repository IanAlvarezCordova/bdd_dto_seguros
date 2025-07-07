//File: /src/main/java/com/example/bdd_dto/controller/PropietarioController.java
package com.example.bdd_dto.controller;

import com.example.bdd_dto.dto.PropietarioDTO;
import com.example.bdd_dto.service.PropietarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropietarioController {
    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService){
        this.propietarioService=propietarioService;
    }

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> obtenerTodos(){
        List<PropietarioDTO> propietarios = propietarioService.obtenerPropietarios();
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioDTO> obtenerPorId(@PathVariable Long id){
        PropietarioDTO propietarioDTO = propietarioService.obtenerPropietarioPorId(id);
        if(propietarioDTO != null){
            return ResponseEntity.ok(propietarioDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PropietarioDTO> crearPropietario(@RequestBody PropietarioDTO propietarioDTO){
        PropietarioDTO creado = propietarioService.crearPropietario(propietarioDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioDTO> actualizarPropietario(
            @PathVariable Long id,
            @RequestBody PropietarioDTO propietarioDTO) {
        PropietarioDTO actualizado = propietarioService.actualizarPropietario(id, propietarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPropietario(@PathVariable Long id) {
        propietarioService.eliminarPropietario(id);
        return ResponseEntity.ok("Propietario eliminado correctamente");
    }
}