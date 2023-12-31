/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.Abarrotes.Controladores;

import java.util.List;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.uv.Abarrotes.DTOs.DTONotaVenta;
import org.uv.Abarrotes.DTOs.DTOVenta;
import org.uv.Abarrotes.modelos.NotaVenta;
import org.uv.Abarrotes.servicio.NotaVentaService;
/**
 *
 * @author yacruz
 */
@Controller
@RequestMapping("api/notasventas")
public class NotaVentaController {
    @Autowired
    private NotaVentaService notaventaService;
 
    @PostMapping
    public ResponseEntity<org.uv.Abarrotes.DTOs.DTONotaVenta> crearNotaVentaConEntidades(@RequestBody NotaVenta nuevoNotaVenta) {
        org.uv.Abarrotes.DTOs.DTONotaVenta notaventaCreado = notaventaService.crearNotaVenta(nuevoNotaVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaventaCreado);
    }

    //postmapping para crear una nota de venta
    @PostMapping("/crear")
    public ResponseEntity<DTOVenta> crearNotaVenta(@RequestBody NotaVenta nuevoNotaVenta) {
        DTOVenta notaVenta = new DTOVenta(notaventaService.crearNota(nuevoNotaVenta));
        return ResponseEntity.ok(notaVenta);
    }
    @GetMapping
    public ResponseEntity<List<DTONotaVenta>> obtenerNotasVentas(){
        List<DTONotaVenta> notasventas = notaventaService.obtenerNotasVentas();
        return ResponseEntity.ok(notasventas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DTONotaVenta> obtenerNotasVentasPorId(@PathVariable Long id){
        DTONotaVenta notasventas = notaventaService.obtenerNotaVentaPorId(id);
        return ResponseEntity.ok(notasventas);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DTONotaVenta> actualizarNotaVenta(@PathVariable Long id, @RequestBody NotaVenta notaventaActualizado) {
        DTONotaVenta notaventa = notaventaService.actualizarNotaVenta(id, notaventaActualizado);
        return ResponseEntity.ok(notaventa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotaVenta(@PathVariable Long id) {
        notaventaService.eliminarNotaVenta(id);
        return ResponseEntity.noContent().build();
    }
}
