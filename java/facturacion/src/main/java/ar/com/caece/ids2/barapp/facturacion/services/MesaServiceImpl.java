package ar.com.caece.ids2.barapp.facturacion.services;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MesaServiceImpl implements MesaService {

    private static final Map<Integer, Mesa> mesas = new ConcurrentHashMap<>();
    private static Integer nextCode = 0;

    private static void checkMesaExists(Integer code) throws TableNotFoundException {
        if (!mesas.containsKey(code)) {
            throw new TableNotFoundException();
        }
    }

    private static Integer getNextCode() {
        return nextCode++;
    }

    @Override
    public Mesa createMesa(String name) throws DuplicateTableException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        Optional<Mesa> m = mesas.values().stream().filter(mesa1 -> Objects.equals(mesa1.getNombre(), name)).findAny();
        if (m.isPresent()) {
            throw new DuplicateTableException("Table with name already present");
        }
        Mesa mesa = new Mesa(name);
        mesa.setCodigoMesa(getNextCode());
        mesas.put(mesa.getCodigoMesa(), mesa);
        return mesa;
    }

    @Override
    public Mesa getMesa(Integer code) throws TableNotFoundException {
        checkMesaExists(code);
        return mesas.get(code);
    }

    @Override
    public List<Mesa> getMesas() {
        return new ArrayList<>(mesas.values());
    }

    @Override
    public void destroyMesa(Integer code) throws TableNotFoundException {
        checkMesaExists(code);
        mesas.remove(code);
    }
}
