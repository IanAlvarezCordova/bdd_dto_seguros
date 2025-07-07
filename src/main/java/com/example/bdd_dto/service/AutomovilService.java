//File: /src/main/java/com/example/bdd_dto/service/AutomovilService.java
package com.example.bdd_dto.service;

import com.example.bdd_dto.dto.AutomovilDTO;
import com.example.bdd_dto.model.Automovil;
import com.example.bdd_dto.model.Propietario;
import com.example.bdd_dto.repository.AutomovilRepository;
import com.example.bdd_dto.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutomovilService {
    private final AutomovilRepository automovilRepository;
    private final PropietarioRepository propietarioRepository;

    public AutomovilService(AutomovilRepository automovilRepository, PropietarioRepository propietarioRepository) {
        this.automovilRepository = automovilRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public List<AutomovilDTO> obtenerTodos() {
        return automovilRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AutomovilDTO obtenerPorId(Long id) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automovil no encontrado"));
        return convertirADTO(automovil);
    }

    public AutomovilDTO crearAutomovil(AutomovilDTO automovilDTO) {
        Propietario propietario = propietarioRepository.findById(automovilDTO.getPropietarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        Automovil automovil = new Automovil();
        automovil.setModelo(automovilDTO.getModelo());
        automovil.setValor(automovilDTO.getValor());
        automovil.setAccidentes(automovilDTO.getAccidentes());
        automovil.setPropietario(propietario);
        automovilRepository.save(automovil);
        return convertirADTO(automovil);
    }

    public AutomovilDTO actualizarAutomovil(Long id, AutomovilDTO automovilDTO) {
        Automovil automovil = automovilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automovil no encontrado"));
        automovil.setModelo(automovilDTO.getModelo());
        automovil.setValor(automovilDTO.getValor());
        automovil.setAccidentes(automovilDTO.getAccidentes());
        if (automovilDTO.getPropietarioId() != null) {
            Propietario propietario = propietarioRepository.findById(automovilDTO.getPropietarioId())
                    .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
            automovil.setPropietario(propietario);
        }
        automovilRepository.save(automovil);
        return convertirADTO(automovil);
    }

    public void eliminarAutomovil(Long id) {
        automovilRepository.deleteById(id);
    }

    private AutomovilDTO convertirADTO(Automovil automovil) {
        AutomovilDTO dto = new AutomovilDTO();
        dto.setId(automovil.getId());
        dto.setModelo(automovil.getModelo());
        dto.setValor(automovil.getValor());
        dto.setAccidentes(automovil.getAccidentes());
        dto.setPropietarioId(automovil.getPropietario().getId());
        dto.setPropietarioNombre(automovil.getPropietario().getNombre() + " " + automovil.getPropietario().getApellido());
        if (automovil.getSeguro() != null) {
            dto.setCostoSeguro(automovil.getSeguro().getCostoTotal());
        }
        return dto;
    }
}