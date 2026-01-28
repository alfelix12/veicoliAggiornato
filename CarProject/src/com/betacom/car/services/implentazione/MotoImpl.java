package com.betacom.car.services.implentazione;

import java.util.Map;

import com.betacom.car.models.Moto;
import com.betacom.car.services.interfaces.ServicesInterfaces;
import com.betacom.car.services.interfaces.VeicoliAbstract;
import com.betacom.car.singletone.ListManager;

public class MotoImpl extends VeicoliAbstract implements ServicesInterfaces{

	@Override
	public void controlExecute(String ope, String params) throws Exception {
		System.out.println("Execute MOTO");
		Map<String, String> p = decodeParamers(params);
		Moto moto = new Moto();
		moto.setTipoVeicolo("moto");
		
		moto = (Moto) controlExecute(moto, p);
		
		if (ListManager.getInstance().isTargaExist(p.get("targa").toUpperCase()))
			throw new Exception("Targa già inserita");
		moto.setTarga(p.get("targa").toUpperCase());
		
		try {
			moto.setCc(Integer.parseInt(p.get("cc")));			
		} catch (Exception e) {
			throw new Exception("cilindrato invalido");
		}
		
		moto = (Moto) ListManager.getInstance().insertVeicolo(moto);
		System.out.println("Moto inserita");

	}

}
