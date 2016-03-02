package ar.com.caece.ids2.barapp.facturacion.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MenuServiceImpl implements MenuService {
    Random random = new Random();
    Map<Integer, Long> preciosPlatos = new HashMap<>();
    Map<Integer, Long> preciosBebidas = new HashMap<>();

    @Override
    public Long obtenerValorPlato(Integer code) {
        if (!preciosPlatos.containsKey(code)) {
            preciosPlatos.put(code, (long) random.nextInt(100000));
        }
        return preciosPlatos.get(code);
    }

    @Override
    public Long obtenerValorBebida(Integer code) {
        if (!preciosBebidas.containsKey(code)) {
            preciosBebidas.put(code, (long) random.nextInt(100000));
        }
        return preciosBebidas.get(code);
    }
}
