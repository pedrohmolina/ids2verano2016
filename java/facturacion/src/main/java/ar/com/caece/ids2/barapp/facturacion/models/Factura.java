package ar.com.caece.ids2.barapp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Factura {
    @JsonProperty("Platos")
    Map<Integer, DetallePlato> platos = new HashMap<>();
    @JsonProperty("Cervezas")
    Map<Integer, DetalleCerveza> cervezas = new HashMap<>();
    @JsonProperty("Propina")
    Long tip = 0L;
    @JsonProperty("Total")
    Long total = 0L;
    @JsonProperty("MedioDePago")
    MedioDePago medioDePago;

    public Factura(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTip() {
        return tip;
    }

    public void setTip(Long tip) {
        this.tip = tip;
    }

    public void addPlato(DetallePlato detallePlato) {
        DetallePlato d = this.platos.get(detallePlato.getCodigoPlato());
        if (d != null) {
            d.setCantidad(d.getCantidad() + detallePlato.getCantidad());
        } else {
            this.platos.put(detallePlato.getCodigoPlato(), detallePlato);
        }
    }

    public void addBebida(DetalleCerveza detalleCerveza) {
        DetalleCerveza d = this.cervezas.get(detalleCerveza.getCodigoCerveza());
        if (d != null) {
            d.setCantidad(d.getCantidad() + detalleCerveza.getCantidad());
        } else {
            this.cervezas.put(detalleCerveza.getCodigoCerveza(), detalleCerveza);
        }
    }
}
