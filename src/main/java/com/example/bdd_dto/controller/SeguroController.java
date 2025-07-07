//File: /src/main/java/com/example/bdd_dto/controller/SeguroController.java
package com.example.bdd_dto.controller;

import com.example.bdd_dto.dto.SeguroDTO;
import com.example.bdd_dto.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SeguroController {
    @Autowired
    private final SeguroService seguroService;

    public SeguroController(SeguroService seguroService) {
        this.seguroService = seguroService;
    }

    @GetMapping
    public ResponseEntity<List<SeguroDTO>> obtenerTodos() {
        List<SeguroDTO> seguros = seguroService.obtenerTodos();
        return ResponseEntity.ok(seguros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> obtenerPorId(@PathVariable Long id) {
        SeguroDTO seguroDTO = seguroService.obtenerPorId(id);
        return ResponseEntity.ok(seguroDTO);
    }

    @PostMapping
    public ResponseEntity<SeguroDTO> crearSeguro(@RequestBody SeguroDTO seguroDTO) {
        SeguroDTO creado = seguroService.crearSeguro(seguroDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroDTO> actualizarSeguro(@PathVariable Long id, @RequestBody SeguroDTO seguroDTO) {
        SeguroDTO actualizado = seguroService.actualizarSeguro(id, seguroDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSeguro(@PathVariable Long id) {
        seguroService.eliminarSeguro(id);
        return ResponseEntity.ok("Seguro eliminado correctamente");
    }
}