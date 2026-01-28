package com.betacom.car.services.implentazione;

import java.util.List;
import java.util.Map;

import com.betacom.car.models.Veicoli;
import com.betacom.car.services.interfaces.ServicesInterfaces;
import com.betacom.car.services.interfaces.VeicoliAbstract;
import com.betacom.car.singletone.ListManager;

public class ListImpl extends VeicoliAbstract implements ServicesInterfaces{

	@Override
	public void controlExecute(String ope,String params) throws Exception {
		System.out.println("List process :"+ params);
		String[] pp = params.split(";");
		
		Map<String, String> p = decodeParamers(pp[1]);
		
		List<Veicoli>   lV = ListManager.getInstance().getListV();
		for (Veicoli v:lV) {
			if (p.get("tipoVeicolo") != null) {
				if (v.getTipoVeicolo().equalsIgnoreCase(p.get("tipoVeicolo"))) System.out.println(v);
				if (v.getCategoria().equalsIgnoreCase(p.get("cat"))) System.out.println(v);

			} else {
				System.out.println(v);
				
			}
				
		}
	}

}
