package ar.com.caece.ids2.barapp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sebastian Schepens on 18/2/2016.
 */
public class DetalleCerveza {
    @JsonProperty("CodigoCerveza")
    private Integer codigoCerveza;
    @JsonProperty("Cantidad")
    private Integer cantidad;

    public DetalleCerveza() {
    }

    public DetalleCerveza(Integer codigoCerveza, Integer cantidad) {
        this.codigoCerveza = codigoCerveza;
        this.cantidad = cantidad;
    }

    public Integer getCodigoCerveza() {
        return this.codigoCerveza;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePlato{" +
                "codigoPlato: " + codigoCerveza + ", " +
                "cantidad: " + cantidad +
                "}";
    }
}
