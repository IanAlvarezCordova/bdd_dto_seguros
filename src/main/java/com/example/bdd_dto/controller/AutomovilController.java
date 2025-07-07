//File: /src/main/java/com/example/bdd_dto/controller/AutomovilController.java
package com.example.bdd_dto.controller;

import com.example.bdd_dto.dto.AutomovilDTO;
import com.example.bdd_dto.service.AutomovilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automoviles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutomovilController {
    private final AutomovilService automovilService;

    public AutomovilController(AutomovilService automovilService) {
        this.automovilService = automovilService;
    }

    @GetMapping
    public ResponseEntity<List<AutomovilDTO>> obtenerTodos() {
        List<AutomovilDTO> automoviles = automovilService.obtenerTodos();
        return ResponseEntity.ok(automoviles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomovilDTO> obtenerPorId(@PathVariable Long id) {
        AutomovilDTO automovilDTO = automovilService.obtenerPorId(id);
        return ResponseEntity.ok(automovilDTO);
    }

    @PostMapping
    public ResponseEntity<AutomovilDTO> crearAutomovil(@RequestBody AutomovilDTO automovilDTO) {
        AutomovilDTO creado = automovilService.crearAutomovil(automovilDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutomovilDTO> actualizarAutomovil(@PathVariable Long id, @RequestBody AutomovilDTO automovilDTO) {
        AutomovilDTO actualizado = automovilService.actualizarAutomovil(id, automovilDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutomovil(@PathVariable Long id) {
        automovilService.eliminarAutomovil(id);
        return ResponseEntity.ok("Automovil eliminado correctamente");
    }
}