package edu.cibertec.lab06.service;

import edu.cibertec.lab06.dao.ProductoDAO;
import edu.cibertec.lab06.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductoService {

    private ProductoDAO data;

    @Autowired
    public ProductoService ( ProductoDAO data){
        this.data = data;
    }

    //listado producto
    public List<ProductoDTO> getAll (){
        return data.getAll();
    }

    //agregar nuevo producto
    public void save (ProductoDTO producto){
        producto.setId(data.generadorCodigo());
        data.agregarProducto(producto);
    }

    //eliminar un producto
    public boolean deleteById(@PathVariable(name = "id") long id){
        return data.eliminarProducto(id);
    }

    //obtener un producto
    public ProductoDTO findById(long id){
        return data.getAll()
                .stream()
                .filter(pro -> pro.getId() == id )
                .findFirst()
                .orElse(null);

    }

    //actualizar producto
    public void update(ProductoDTO producto){
        data.actualizarProducto(producto);
    }
}
