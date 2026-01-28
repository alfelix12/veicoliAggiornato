//package com.betacom.car;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.betacom.car.process.StartCar;
//import com.betacom.car.singletone.ListManager;
//
//public class MainCar {
//
//	public static void main(String[] args) {
//		
//		
//		List<String> params = new ArrayList<String>();
//		
//		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2025,modello=500,porte=4,targa=el234gx,cc=1200");
//		params.add("add;macchina;ruote=4,alim=benzina,cat=strada,colore=bianco,marca=fiat,anno=2026,modello=panda,porte=4,targa=fl234gx,cc=1300");
//		params.add("delete;macchina;id=2");
//		params.add("add;moto;ruote=2,alim=benzina,cat=strada,colore=nero,marca=Yamaha,anno=2025,modello=r1,targa=EL22239,cc=900");
//		params.add("add;bici;ruote=2,alim=manuale,cat=strada,colore=nero,marca=Bianchi,anno=2025,modello=Grizl 5,marce=10,sospenzione=senza,piegevole=no");
//		params.add("list;type=all");
//		params.add("list;type=filter,tipoVeicolo=macchina");
//		
//		try {
//			ListManager.getInstance().loadConstant();
//			new StartCar().execute(params);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}
//
//}

//package com.betacom.car;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.betacom.car.process.StartCar;
//import com.betacom.car.singletone.ListManager;
//
//public class MainCar {
//
//    public static void main(String[] args) {
//
//        List<String> params = new ArrayList<>();
//
//        // path file
//        String fileVeicoli = "C:\\Users\\ACER\\Desktop\\veicoli.txt";
//        String fileConstant = "C:\\Users\\ACER\\Desktop\\constant.txt";
//
//        String fileOutput = "C:\\Users\\ACER\\Desktop\\outputCar.txt";
//
//       
//        ListManager lm = ListManager.getInstance();
//        lm.loadConstant(fileConstant);
//
//   
//        try (BufferedReader br = new BufferedReader(new FileReader(fileVeicoli))) {
//
//            String line;
//
//            while ((line = br.readLine()) != null) {
//
//                if (line.isBlank()) {
//                    continue;
//                }
//
//                params.add(line);
//            }
//
//        } catch (Exception e) {
//            System.out.println("Errore lettura comandi: " + e.getMessage());
//            return;
//        }
//
//       
//        try {
//            new StartCar().execute(params);
//        } catch (Exception e) {
//            System.out.println("Errore esecuzione: " + e.getMessage());
//        }
//    }
//}

package com.betacom.car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.betacom.car.process.StartCar;
import com.betacom.car.singletone.ListManager;

public class MainCar {

    public static void main(String[] args) {

        List<String> params = new ArrayList<>();

        // path file
        String fileVeicoli = "C:\\Users\\ACER\\Desktop\\veicoli.txt";
        String fileConstant = "C:\\Users\\ACER\\Desktop\\constant.txt";
        String fileOutput = "C:\\Users\\ACER\\Desktop\\outputCar.txt";

        // carico costanti
        ListManager lm = ListManager.getInstance();
        lm.loadConstant(fileConstant);

        // leggo comandi
        try (BufferedReader br = new BufferedReader(new FileReader(fileVeicoli))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    params.add(line.trim());
                }
            }
        } catch (Exception e) {
            System.out.println("Errore lettura comandi: " + e.getMessage());
            return;
        }

        // eseguo comandi
        try {
            StartCar startCar = new StartCar(fileOutput); // passo file output
            startCar.execute(params);

            // alla fine scrivo la lista dei veicoli su file
            lm.exportListToFile(fileOutput);

        } catch (Exception e) {
            System.out.println("Errore esecuzione: " + e.getMessage());
        }
    }
}

