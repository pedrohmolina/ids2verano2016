package ar.com.caece.ids2.stockCerveza.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.caece.ids2.stockCerveza.facade.PedidoFacade;
import ar.com.caece.ids2.stockCerveza.model.Pedido;

@RestController
public class PedidoController {

	@RequestMapping(value="/realizarPedido", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> realizarPedido(@RequestParam(value="pedido") String pedido){
		System.out.println("pepe");
		PedidoFacade pedidoFacade = new PedidoFacade();
		Gson gson = new Gson();
		
		Map<String, Object> resultado = null;
		
		try {
			resultado = new HashMap<String, Object>();
			
			Pedido pedidoObj = gson.fromJson(pedido, Pedido.class);
			pedidoFacade.realizarPedido(pedidoObj);
			System.out.println("ssdfsddsfsdf");

			resultado.put("statusCode", 0);
			resultado.put("mensaje", "ok");

		} catch (RuntimeException re) {
			// TODO Auto-generated catch block
			resultado.put("statusCode", 999);
			resultado.put("mensaje", re.getMessage());
			re.printStackTrace();
		} catch (Exception e) {
			resultado.put("statusCode", 999);
			resultado.put("mensaje", e.getMessage());
			e.printStackTrace();
		}finally{
			
			return resultado;
		}
			
		
		
		
	}
}
