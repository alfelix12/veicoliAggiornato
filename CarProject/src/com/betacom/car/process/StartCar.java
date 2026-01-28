//package com.betacom.car.process;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.betacom.car.services.implentazione.BiciImpl;
//import com.betacom.car.services.implentazione.ListImpl;
//import com.betacom.car.services.implentazione.MacchinaImpl;
//import com.betacom.car.services.implentazione.MotoImpl;
//import com.betacom.car.services.interfaces.ServicesInterfaces;
//
//
//public class StartCar {
//	public final static int OPERATION=0;
//	public final static int TIPO_VEICOLO=1;
//	public final static int PARAMETERS=2;
//	
//	public void execute(List<String> params) throws Exception{
//		Map<String,ServicesInterfaces> serv = new HashMap<String, ServicesInterfaces>();
//		serv.put("macchina", new MacchinaImpl());
//		serv.put("moto", new MotoImpl());
//		serv.put("bici", new BiciImpl());
//		serv.put("list", new ListImpl());
//		
//		
//		for (String para:params) {
//			String[] inp = para.split(";");
//			String operation = inp[OPERATION].trim();
//			try {
//				if ("list".equalsIgnoreCase(operation)) {
//					ServicesInterfaces ex = serv.get("list");
//					ex.controlExecute("list",para);
//				} else {
//					String tipo = inp[TIPO_VEICOLO].trim();										
//					if (serv.containsKey(tipo)) {					
//						ServicesInterfaces ex = serv.get(tipo);
//						ex.controlExecute(operation,inp[PARAMETERS]);
//						
//					} else 
//						System.out.println("Tipo veicolo non previsto:" + tipo);
//					
//				}
//				
//				
//			} catch (Exception e) {
//				System.out.println("error:" + e.getMessage());
//			}
//			
//			
//			
//		}
//	}
//}


package com.betacom.car.process;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.services.implentazione.BiciImpl;
import com.betacom.car.services.implentazione.ListImpl;
import com.betacom.car.services.implentazione.MacchinaImpl;
import com.betacom.car.services.implentazione.MotoImpl;
import com.betacom.car.services.interfaces.ServicesInterfaces;

public class StartCar {

    private PrintWriter writer; // writer per output su file
    public final static int OPERATION = 0;
    public final static int TIPO_VEICOLO = 1;
    public final static int PARAMETERS = 2;

    public StartCar(String fileOutput) {
        try {
            this.writer = new PrintWriter(new BufferedWriter(new FileWriter(fileOutput, true)));
        } catch (Exception e) {
            System.out.println("Errore inizializzazione writer: " + e.getMessage());
            this.writer = null;
        }
    }

    private void log(String msg) {
        System.out.println(msg);
        if (writer != null) {
            writer.println(msg);
            writer.flush();
        }
    }

    public void execute(List<String> params) throws Exception {
        Map<String, ServicesInterfaces> serv = new HashMap<>();
        serv.put("macchina", new MacchinaImpl());
        serv.put("moto", new MotoImpl());
        serv.put("bici", new BiciImpl());
        serv.put("list", new ListImpl());

        for (String para : params) {
            String[] inp = para.split(";");
            String operation = inp[OPERATION].trim();
            try {
                if ("list".equalsIgnoreCase(operation)) {
                    ServicesInterfaces ex = serv.get("list");
                    log("List process: " + para);
                    ex.controlExecute("list", para);
                } else {
                    String tipo = inp[TIPO_VEICOLO].trim();
                    if (serv.containsKey(tipo)) {
                        ServicesInterfaces ex = serv.get(tipo);
                        log("Execute " + tipo + " " + operation);
                        ex.controlExecute(operation, inp[PARAMETERS]);
                    } else {
                        log("Tipo veicolo non previsto: " + tipo);
                    }
                }
            } catch (Exception e) {
                log("error: " + e.getMessage());
            }
        }

        if (writer != null) {
            writer.close();
        }
    }
}

