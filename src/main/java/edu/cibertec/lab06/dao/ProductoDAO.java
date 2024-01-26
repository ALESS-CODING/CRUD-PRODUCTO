package edu.cibertec.lab06.dao;

import edu.cibertec.lab06.dto.ProductoDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoDAO {

    private List<ProductoDTO> producto;

    public ProductoDAO() {
        producto = new ArrayList<>();
        producto.add(new ProductoDTO(generadorCodigo(), "polo", 40.9, 5));
        producto.add(new ProductoDTO(generadorCodigo(), "casaca", 35.9, 5));

    }

    //listar producto
    public List<ProductoDTO> getAll (){
        return producto;
    }

    //agregar producto
    public void agregarProducto ( ProductoDTO p){
        producto.add(p);
    }

    //eliminar producto
    public boolean eliminarProducto (long id){
        ProductoDTO productoDTO = producto.stream().filter( pro -> pro.getId() == id).findFirst().orElse( null);
        if(productoDTO == null) return false;
        producto.remove(productoDTO);
        return true;
    }

    //actualizar producto
    public void actualizarProducto (ProductoDTO productoDTO){
        int i = 0;
        for (ProductoDTO pro : producto){
            if(pro.getId() == productoDTO.getId()){
                producto.set(i,productoDTO);
            }
            i++;
        }
    }
    //generador de ID
    public long generadorCodigo (){
        if(producto.isEmpty()) return  1;
        ProductoDTO productoDTO = producto.get(producto.size() - 1);
        return productoDTO.getId() + 1;
    }
}
