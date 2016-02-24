package ar.com.caece.ids2.barapp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Cuenta {
    @JsonProperty("Platos")
    List<DetallePlato> platos = new ArrayList<>();
    @JsonProperty("Cervezas")
    List<DetalleCerveza> cervezas = new ArrayList<>();
    @JsonProperty("Total")
    Long total = 0L;

    public Cuenta(List<DetallePlato> platos, List<DetalleCerveza> cervezas, Long total) {
        this.platos = platos;
        this.cervezas = cervezas;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DetallePlato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<DetallePlato> platos) {
        this.platos = platos;
    }

    public List<DetalleCerveza> getCervezas() {
        return cervezas;
    }

    public void setCervezas(List<DetalleCerveza> cervezas) {
        this.cervezas = cervezas;
    }
}
