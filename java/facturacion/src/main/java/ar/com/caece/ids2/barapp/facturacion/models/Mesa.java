package ar.com.caece.ids2.barapp.facturacion.models;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Mesa {
    @JsonProperty("Nombre")
    private String nombre;
    @JsonProperty("CodigoMesa")
    private Integer codigoMesa;
    @JsonProperty("Estado")
    private State estado = State.LIBRE;
    @JsonProperty("Pedidos")
    private List<Pedido> pedidos;

    public Mesa(String nombre) {
        this.nombre = nombre;
        this.pedidos = Collections.synchronizedList(new ArrayList<>());
    }

    public Integer getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(Integer codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    public boolean isOpen() {
        return estado == State.OCUPADA;
    }

    @JsonIgnore
    public boolean isClosed() {
        return !isOpen();
    }

    public void open() throws TableAlreadyOccupiedException {
        if (estado == State.OCUPADA) {
            throw new TableAlreadyOccupiedException();
        }
        estado = State.OCUPADA;
    }

    public void close() throws TableNotOccupiedException {
        if (estado == State.LIBRE) {
            throw new TableNotOccupiedException("Table is not occupied");
        }
        estado = State.LIBRE;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Integer pedido) {
        Optional<Pedido> p = pedidos.stream().filter(ped -> ped.getCodigo() == pedido).findFirst();
        if (p.isPresent()) {
            pedidos.remove(p.get());
        }
    }

    public void removePedido(Pedido p) {
        pedidos.remove(p);
    }

    public State getEstado() {
        return estado;
    }

    public enum State {
        LIBRE,
        OCUPADA;

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
