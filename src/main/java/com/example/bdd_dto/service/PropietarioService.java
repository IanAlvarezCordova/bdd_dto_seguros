//File: /src/main/java/com/example/bdd_dto/service/PropietarioService.java
package com.example.bdd_dto.service;

import com.example.bdd_dto.dto.PropietarioDTO;
import com.example.bdd_dto.model.Automovil;
import com.example.bdd_dto.model.Propietario;
import com.example.bdd_dto.repository.AutomovilRepository;
import com.example.bdd_dto.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private AutomovilRepository automovilRepository;

    public PropietarioDTO crearPropietario(PropietarioDTO propietarioDTO) {
        Propietario propietario = new Propietario();
        String[] nombreApellido = propietarioDTO.getNombreCompleto().split(" ");
        propietario.setNombre(nombreApellido[0]);
        propietario.setApellido(nombreApellido.length > 1 ? nombreApellido[1] : "");
        propietario.setEdad(propietarioDTO.getEdad());
        List<Long> automovilIds = propietarioDTO.getAutomovilIds() != null ? propietarioDTO.getAutomovilIds() : new ArrayList<>();
        List<Automovil> automoviles = new ArrayList<>();
        if (!automovilIds.isEmpty()) {
            automoviles = automovilRepository.findAllById(automovilIds);
            for (Automovil automovil : automoviles) {
                automovil.setPropietario(propietario);
            }
        }
        propietario.setAutomoviles(automoviles);
        propietarioRepository.save(propietario);
        return convertirADTO(propietario);
    }

    public PropietarioDTO obtenerPropietarioPorId(Long id) {
        Propietario propietario = propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        return convertirADTO(propietario);
    }

    public PropietarioDTO actualizarPropietario(Long id, PropietarioDTO propietarioDTO) {
        Propietario propietario = propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        String[] nombreApellido = propietarioDTO.getNombreCompleto().split(" ");
        propietario.setNombre(nombreApellido[0]);
        propietario.setApellido(nombreApellido.length > 1 ? nombreApellido[1] : "");
        propietario.setEdad(propietarioDTO.getEdad());
        List<Long> automovilIds = propietarioDTO.getAutomovilIds() != null ? propietarioDTO.getAutomovilIds() : new ArrayList<>();
        List<Automovil> automoviles = new ArrayList<>();
        if (!automovilIds.isEmpty()) {
            automoviles = automovilRepository.findAllById(automovilIds);
            for (Automovil automovil : automoviles) {
                automovil.setPropietario(propietario);
            }
        }
        propietario.setAutomoviles(automoviles);
        propietarioRepository.save(propietario);
        return convertirADTO(propietario);
    }

    public void eliminarPropietario(Long id) {
        Propietario propietario = propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        propietarioRepository.delete(propietario);
    }

    public List<PropietarioDTO> obtenerPropietarios() {
        return propietarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private PropietarioDTO convertirADTO(Propietario propietario) {
        PropietarioDTO dto = new PropietarioDTO();
        dto.setId(propietario.getId());
        dto.setNombreCompleto(propietario.getNombre() + " " + propietario.getApellido());
        dto.setEdad(propietario.getEdad());
        dto.setAutomovilIds(
                propietario.getAutomoviles().stream()
                        .map(Automovil::getId)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}