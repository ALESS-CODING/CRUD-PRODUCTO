package edu.cibertec.lab06.controller;

import edu.cibertec.lab06.dto.ProductoDTO;
import edu.cibertec.lab06.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ModelAndView listadoProducto (){
        return new ModelAndView("lista","producto", productoService.getAll());
    }

    @GetMapping("/add")
    public ModelAndView registroFiorm (){
        return new ModelAndView("registrar","producto", new ProductoDTO());
    }

    @PostMapping("/grabar")
    public ModelAndView grabarProducto (@Valid @ModelAttribute(name = "producto") ProductoDTO productoDTO, BindingResult result){
        if(result.hasErrors()) return new ModelAndView("registrar","producto",productoDTO);
        productoService.save(productoDTO);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/eliminar")
    public String eliminarProducto (@ModelAttribute(name = "id") Long id){
        if(productoService.deleteById(id)) return "redirect:/";
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarForm (@ModelAttribute(name = "id") Long id, Model m){
        ProductoDTO producto = productoService.findById(id);
        if(producto == null) return new ModelAndView("redirect:/");
        return new ModelAndView("editar","producto", producto);
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizarProducto (@Valid @ModelAttribute(name = "producto") ProductoDTO productoDTO, BindingResult result){
        if(result.hasErrors()) return new ModelAndView("registrar","producto",productoDTO);
        productoService.update(productoDTO);
        return new ModelAndView("redirect:/");
    }
}
