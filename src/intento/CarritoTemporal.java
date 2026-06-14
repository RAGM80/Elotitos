/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package intento;

import java.util.ArrayList;

public class CarritoTemporal {

    public static ArrayList<ItemCarrito> productos = new ArrayList<>();

    public static void agregarProducto(ItemCarrito nuevo) {

        for (ItemCarrito item : productos) {

            if (item.idProducto == nuevo.idProducto) {
                item.cantidad += nuevo.cantidad;
                return;
            }
        }

        productos.add(nuevo);
    }

    public static double calcularSubtotal() {

        double total = 0;

        for (ItemCarrito item : productos) {
            total += item.getSubtotal();
        }

        return total;
    }

    public static void limpiarCarrito() {
        productos.clear();
    }
}
