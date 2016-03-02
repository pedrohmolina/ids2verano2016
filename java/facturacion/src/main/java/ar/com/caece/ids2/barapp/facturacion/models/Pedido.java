package ar.com.caece.ids2.barapp.facturacion.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Pedido {
    @JsonProperty("Codigo")
    private Integer codigo;
    @JsonProperty("CodigoMesa")
    private Integer codigoMesa;
    @JsonProperty("Estado")
    private State estado = State.PENDIENTE;
    @JsonProperty("Platos")
    private List<DetallePlato> platos = new ArrayList<>();
    @JsonProperty("Cervezas")
    private List<DetalleCerveza> cervezas = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(int codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public Pedido withPlato(DetallePlato detallePlato) {
        this.platos.add(detallePlato);
        return this;
    }

    public Pedido withPlatos(List<DetallePlato> detallePlatos) {
        this.platos.addAll(detallePlatos);
        return this;
    }

    public Pedido withBebida(DetalleCerveza detalleCerveza) {
        this.cervezas.add(detalleCerveza);
        return this;
    }

    public Pedido withBebidas(List<DetalleCerveza> detalleCervezas) {
        this.cervezas.addAll(detalleCervezas);
        return this;
    }

    public List<DetallePlato> getPlatos() {
        return platos;
    }

    public List<DetalleCerveza> getCervezas() {
        return cervezas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getCodigoMesa() {
        return codigoMesa;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo: " + codigo + ", " +
                "codigoMesa: " + codigoMesa + ", " +
                "platos: " + platos + ", " +
                "cervezas: " + cervezas + ", " +
                "estado: " + estado +
                "}";
    }

    public enum State {
        PENDIENTE,
        EN_PREPARACION,
        TERMINADO,
        CANCELADO;

        private static List<State> list = Arrays.asList(State.values());

        @JsonCreator
        public static State forValue(Integer value) {
            return list.get(value);
        }

        @JsonValue
        public Integer toValue() {
            return list.indexOf(this);
        }
    }
}
