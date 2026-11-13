package com.uriel.grueact3t4.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uriel.grueact3t4.dto.ProductoDTO;
import com.uriel.grueact3t4.service.CatalogoService;

@Controller
public class CatalogoVistaController {

    private final CatalogoService servicio;

    public CatalogoVistaController(CatalogoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/productos";
    }

    // Mostrar los productos y filtrar por categoria
    @GetMapping("/productos")
    public String listar(@RequestParam(required = false) Long categoriaId, Model model) {
        List<ProductoDTO> lista = servicio.listar();
        if (categoriaId != null) {
            lista = lista.stream()
                    .filter(p -> categoriaId.equals(p.categoriaId()))
                    .toList();
        }
        model.addAttribute("productos", lista);
        model.addAttribute("categorias", servicio.listarCategorias());
        model.addAttribute("categoriaSeleccionada", categoriaId);
        return "catalogo";
    }

    // Registrar categoria para poder asignarla a un producto
    @PostMapping("/productos/categorias")
    public String crearCategoria(@RequestParam String nombre) {
        servicio.crearCategoria(nombre);
        return "redirect:/productos";
    }

    // Registrar producto con su categoria
    @PostMapping("/productos/guardar")
    public String guardar(@RequestParam String nombre,
                          @RequestParam BigDecimal precio,
                          @RequestParam Long categoriaId) {
        servicio.crear(new ProductoDTO(null, nombre, precio, categoriaId, null));
        return "redirect:/productos";
    }

    // Modificar producto existente
    @PostMapping("/productos/editar/{id}")
    public String editar(@PathVariable Long id,
                         @RequestParam String nombre,
                         @RequestParam BigDecimal precio,
                         @RequestParam Long categoriaId) {
        servicio.actualizar(id, new ProductoDTO(id, nombre, precio, categoriaId, null));
        return "redirect:/productos";
    }

    // Eliminar producto por ID
    @PostMapping("/productos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/productos";
    }
}
