/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionbeta;

import intento.*;
import java.util.ArrayList;
/*
 * en esta clase no se guarda nada en MySQL,solo se mantiene una lista en memoria
 * para justar los productos antes de mandarlos a la compra
 */
public class CarritoTemporal {

// esta es la lista compartida del carrito al ser static se puede consultar desde otras ventanas
    public static ArrayList<ItemCarrito> productos = new ArrayList<>();

// aqui se agrega un producto al carrito, si ya estaba solo aumenta la cantidad
    public static void agregarProducto(ItemCarrito nuevo) {

        for (ItemCarrito item : productos) {
// se compara su id para no repetir el mismo producto en otra fila
            if (item.idProducto == nuevo.idProducto) {
                item.cantidad += nuevo.cantidad;
                return;
            }
        }

        productos.add(nuevo);
    }
// aqui recorre todos los productos y suma el subtotal de cada uno
    public static double calcularSubtotal() {

        double total = 0;

        for (ItemCarrito item : productos) {
            total += item.getSubtotal();
        }

        return total;
    }
// este metodo se usa cuando el pedido ya terminó o cuando se quiere empezar desde cero.
    public static void limpiarCarrito() {
        productos.clear();
    }
}
