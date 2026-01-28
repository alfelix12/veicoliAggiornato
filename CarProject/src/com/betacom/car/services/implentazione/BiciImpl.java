package com.betacom.car.services.implentazione;

import java.util.Map;

import com.betacom.car.models.Bici;
import com.betacom.car.services.interfaces.ServicesInterfaces;
import com.betacom.car.services.interfaces.VeicoliAbstract;
import com.betacom.car.singletone.ListManager;

public class BiciImpl extends VeicoliAbstract  implements ServicesInterfaces{

	@Override
	public void controlExecute(String ope,String params) throws Exception {
		System.out.println("Execute bici");
		Map<String, String> p = decodeParamers(params);
		Bici bici = new Bici();
		bici.setTipoVeicolo("bici");
		
		bici = (Bici) controlExecute(bici, p);

		try {
			bici.setNumeroMarce(Integer.parseInt(p.get("marce")));			
		} catch (Exception e) {
			throw new Exception("numero marce invalido");
		}

		if (!ListManager.getInstance().isValidValue("sospensione", p.get("sospensione")))
			throw new Exception("Tipo sospensione invalida");
		bici.setTipoSospenzione(p.get("sospensione"));
		
		bici.setPiegevole((p.get("piegevole").trim().equalsIgnoreCase("si"))? true : false);


		bici = (Bici) ListManager.getInstance().insertVeicolo(bici);
		
		System.out.println(".... Bici inserita");
		
	}

}
