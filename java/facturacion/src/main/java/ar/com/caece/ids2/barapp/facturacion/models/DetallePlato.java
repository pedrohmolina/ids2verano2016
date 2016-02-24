package ar.com.caece.ids2.barapp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sebastian Schepens on 18/2/2016.
 */
public class DetallePlato {
    @JsonProperty("CodigoPlato")
    private Integer codigoPlato;
    @JsonProperty("Cantidad")
    private Integer cantidad;
    @JsonProperty("Estado")
    private Pedido.State estado = Pedido.State.PENDIENTE;

    public DetallePlato() {
    }

    public DetallePlato(Integer codigoPlato, Integer cantidad) {
        this.codigoPlato = codigoPlato;
        this.cantidad = cantidad;
    }

    public Integer getCodigoPlato() {
        return this.codigoPlato;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePlato{" +
                "codigoPlato: " + codigoPlato + ", " +
                "cantidad: " + cantidad + ", " +
                "estado: " + estado +
                "}";
    }
}
