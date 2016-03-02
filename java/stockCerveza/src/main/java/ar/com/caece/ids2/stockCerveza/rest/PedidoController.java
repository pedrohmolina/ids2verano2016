package ar.com.caece.ids2.stockCerveza.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.caece.ids2.stockCerveza.exception.CervezaInexistenteException;
import ar.com.caece.ids2.stockCerveza.exception.SinStockCervezaException;
import ar.com.caece.ids2.stockCerveza.exception.ValidacionException;
import ar.com.caece.ids2.stockCerveza.facade.PedidoFacade;
import ar.com.caece.ids2.stockCerveza.model.Pedido;

@RestController
public class PedidoController {

	@SuppressWarnings("finally")
	@RequestMapping(value="/realizarPedido", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> realizarPedido(@RequestParam(value="pedido") String pedido) {
	
		PedidoFacade pedidoFacade = null; 
		Gson gson = null;
		Map<String, Object> resultado = null;
		
		try {
			pedidoFacade = new PedidoFacade();
			gson = new Gson();
			resultado = new HashMap<String, Object>();
			
			Pedido pedidoObj = gson.fromJson(pedido, Pedido.class);
			pedidoFacade.realizarPedido(pedidoObj);

			resultado.put("statusCode", 0);
			resultado.put("message", "ok");
		}catch(SinStockCervezaException | CervezaInexistenteException se ) {
			resultado.put("statusCode", 2);
			resultado.put("message", se.getMessage());
		}catch(ValidacionException ve ) {
			resultado.put("statusCode", 4);
			resultado.put("message", ve.getMessage());
		} catch (Exception e) {
			resultado.put("statusCode", 999);
			resultado.put("message", e.getMessage());
			e.printStackTrace();
		}finally{
			return resultado;
		}
			
	}
}
