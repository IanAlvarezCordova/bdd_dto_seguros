//File: /src/main/java/com/example/bdd_dto/service/SeguroService.java
package com.example.bdd_dto.service;

import com.example.bdd_dto.dto.SeguroDTO;
import com.example.bdd_dto.model.Automovil;
import com.example.bdd_dto.model.Propietario;
import com.example.bdd_dto.model.Seguro;
import com.example.bdd_dto.repository.AutomovilRepository;
import com.example.bdd_dto.repository.SeguroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguroService {
    private final SeguroRepository seguroRepository;
    private final AutomovilRepository automovilRepository;

    public SeguroService(SeguroRepository seguroRepository, AutomovilRepository automovilRepository) {
        this.seguroRepository = seguroRepository;
        this.automovilRepository = automovilRepository;
    }

    public List<SeguroDTO> obtenerTodos() {
        return seguroRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public SeguroDTO obtenerPorId(Long id) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguro no encontrado"));
        return convertirADTO(seguro);
    }

    public SeguroDTO crearSeguro(SeguroDTO seguroDTO) {
        // Validar que automovilId esté presente
        Long automovilId = seguroDTO.getAutomovilId();
        if (automovilId == null) {
            throw new IllegalArgumentException("El campo automovilId es requerido");
        }

        // Buscar el automóvil
        Automovil automovil = automovilRepository.findById(automovilId)
                .orElseThrow(() -> new RuntimeException("Automovil no encontrado"));
        Propietario propietario = automovil.getPropietario();

        // Cálculo del costoTotal (ignoramos el costoTotal del DTO, ya que se calcula aquí)
        double baseCost = 1000.0;
        double factorModelo = getFactorModelo(automovil.getModelo());
        double edadFactor = getEdadFactor(propietario.getEdad());
        double costoTotal = baseCost + (automovil.getValor() * factorModelo) +
                (edadFactor * 100) + (automovil.getAccidentes() * 50);

        // Crear y guardar el seguro
        Seguro seguro = new Seguro();
        seguro.setCostoTotal(costoTotal);
        seguro.setAutomovil(automovil);
        seguroRepository.save(seguro);
        automovil.setSeguro(seguro);
        automovilRepository.save(automovil);

        return convertirADTO(seguro);
    }

    public SeguroDTO actualizarSeguro(Long id, SeguroDTO seguroDTO) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguro no encontrado"));

        // Determinar el automóvil a usar
        Automovil automovil;
        if (seguroDTO.getAutomovilId() != null && !seguroDTO.getAutomovilId().equals(seguro.getAutomovil().getId())) {
            // Si se proporciona un nuevo automovilId, actualizar la relación
            automovil = automovilRepository.findById(seguroDTO.getAutomovilId())
                    .orElseThrow(() -> new RuntimeException("Automovil no encontrado"));
            // Limpiar la relación anterior
            Automovil automovilAnterior = seguro.getAutomovil();
            automovilAnterior.setSeguro(null);
            automovilRepository.save(automovilAnterior);
            // Asignar el nuevo automóvil
            seguro.setAutomovil(automovil);
            automovil.setSeguro(seguro);
            automovilRepository.save(automovil);
        } else {
            automovil = seguro.getAutomovil();
        }

        // Establecer costoTotal: usar el valor del DTO si se proporciona, recalcular si no
        double costoTotal;
        if (seguroDTO.getCostoTotal() != 0.0) {
            costoTotal = seguroDTO.getCostoTotal();
        } else {
            Propietario propietario = automovil.getPropietario();
            double baseCost = 1000.0;
            double factorModelo = getFactorModelo(automovil.getModelo());
            double edadFactor = getEdadFactor(propietario.getEdad());
            costoTotal = baseCost + (automovil.getValor() * factorModelo) +
                    (edadFactor * 100) + (automovil.getAccidentes() * 50);
        }

        seguro.setCostoTotal(costoTotal);
        seguroRepository.save(seguro);

        return convertirADTO(seguro);
    }

    public void eliminarSeguro(Long id) {
        Seguro seguro = seguroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seguro no encontrado"));
        Automovil automovil = seguro.getAutomovil();
        automovil.setSeguro(null);
        automovilRepository.save(automovil);
        seguroRepository.deleteById(id);
    }

    private SeguroDTO convertirADTO(Seguro seguro) {
        SeguroDTO dto = new SeguroDTO();
        dto.setId(seguro.getId());
        dto.setCostoTotal(seguro.getCostoTotal());
        dto.setAutomovilId(seguro.getAutomovil().getId());
        return dto;
    }

    // Métodos auxiliares (ajusta según tu lógica)
    private double getFactorModelo(String modelo) {
        switch (modelo) {
            case "Hyundai": return 0.1;
            case "Tesla": return 0.2;
            case "Toyota": return 0.3;
            case "Ford": return 0.25;
            case "Chevrolet": return 0.22;
            case "BMW": return 0.35;
            case "Mercedes": return 0.4;
            case "Kia": return 0.15;
            case "Nissan": return 0.18;
            case "Volkswagen": return 0.27;
            case "Audi": return 0.33;
            case "Honda": return 0.2;
            default: return 0.1; // Valor por defecto
        }
    }

    private double getEdadFactor(int edad) {
        if (edad >= 18 && edad < 23) return 1.5;
        else if (edad >= 23 && edad < 55) return 1.0;
        else return 1.2;
    }
}