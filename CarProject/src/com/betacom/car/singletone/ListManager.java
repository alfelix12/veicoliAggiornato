//package com.betacom.car.singletone;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.betacom.car.models.Veicoli;
//
//public class ListManager {
//	private static ListManager instance = null;
//	
//	private Integer id = 0;
//	Map<String, String[]> controlli = new HashMap<String, String[]>();
//	Map<String, String> lTarge = new HashMap<String, String>();
//	private List<Veicoli> listV = new ArrayList<Veicoli>();
//	
//	
//	private ListManager() {
//	}
//	
//
//	public static ListManager getInstance() {
//		if (instance == null) {
//			instance = new ListManager();
//		}
//		return instance;
//	}
//	
//	public  void loadConstant() {
//		List<String> cons = new ArrayList<String>();
//		cons.add("alim=benzina,diesel,electica,ibrida,manuale");
//		cons.add("cat=strada,fuoristrada,suv,mtb,cross");
//		cons.add("colore=bianco,nero,verde,giallo,marrone,rosso");
//		cons.add("marca=Fiat,Renault,BMW,Telsla,Bianchi,Yamaha,Mercedes,Tecnizer");
//		cons.add("sospenzione=senza,mono,");
//		
//		for (String it:cons) {
//			String [] el = it.split("=");
//			String [] elP = el[1].split(",");
//			controlli.put(el[0], elP);			
//		}
//	}
//	public boolean isValidValue(String key, String value) {
//		String[] values = controlli.get(key);
//		boolean ret = false;
//		for (String it:values) {
//			if (value.equalsIgnoreCase(it)) {
//				ret = true;
//				break;
//			}			
//		}
//		return ret;
//	}
//	
//	public boolean isTargaExist(String targa) {
//		if (lTarge.containsKey(targa))
//			return true;
//		
//		lTarge.put(targa.toUpperCase(), "");
//		return false;	
//		
//	}
//	
//	public Veicoli insertVeicolo(Veicoli v) {
//		v.setId(++id);
//		listV.add(v);
//		return v;
//		
//	}
//
//	public void remove(Integer id) {
//		for (Veicoli it:listV) {
//			if (it.getId() == id) {
//				listV.remove(it);
//				break;
//			}
//		}
//	}
//
//	public List<Veicoli> getListV() {
//		return listV;
//	}
//
//}


///PARTE 2

//package com.betacom.car.singletone;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.betacom.car.models.Veicoli;
//
//public class ListManager {
//
//    private static ListManager instance = null;
//
//    private Integer idCounter = 0;
//
//    private Map<String, String[]> controlli = new HashMap<>();
//    private Map<String, String> lTarghe = new HashMap<>();
//    private List<Veicoli> listV = new ArrayList<>();
//
//    private ListManager() {
//    }
//
//    public static synchronized ListManager getInstance() {
//        if (instance == null) {
//            instance = new ListManager();
//        }
//        return instance;
//    }
//
//    public void loadConstant(String pathFile) {
//
//        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
//
//            String line;
//
//            while ((line = br.readLine()) != null) {
//
//                if (line.isBlank() || line.startsWith("#")) {
//                    continue;
//                }
//
//                String[] parts = line.split("=");
//                if (parts.length != 2) continue;
//
//                String key = parts[0].trim();
//                String[] values = parts[1].split(",");
//
//                controlli.put(key, values);
//            }
//
//        } catch (IOException e) {
//            System.err.println("Errore caricamento controlli: " + e.getMessage());
//        }
//    }
//
//   
//    public boolean isValidValue(String key, String value) {
//
//        String[] values = controlli.get(key);
//        if (values == null) return false;
//
//        for (String it : values) {
//            if (value.equalsIgnoreCase(it)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean isTargaExist(String targa) {
//
//        if (targa == null) return false;
//
//        targa = targa.toUpperCase();
//
//        if (lTarghe.containsKey(targa)) {
//            return true;
//        }
//
//        lTarghe.put(targa, "");
//        return false;
//    }
//
//    
//    public Veicoli insertVeicolo(Veicoli v) {
//
//        v.setId(++idCounter);
//        listV.add(v);
//        return v;
//    }
//
//    public void remove(Integer id) {
//
//        listV.removeIf(v -> v.getId().equals(id));
//    }
//
//    public List<Veicoli> getListV() {
//        return listV;
//    }
//}


package com.betacom.car.singletone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.betacom.car.models.Veicoli;

public class ListManager {

    private static ListManager instance = null;

    private int idCounter = 0;
    private final Map<String, String[]> controlli = new HashMap<>();
    private final Map<String, String> lTarghe = new HashMap<>();
    private final List<Veicoli> listV = new ArrayList<>();

    private ListManager() {}

    public static synchronized ListManager getInstance() {
        if (instance == null) {
            instance = new ListManager();
        }
        return instance;
    }

    public void loadConstant(String pathFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=");
                if (parts.length != 2) continue;

                String key = parts[0].trim();
                String[] values = parts[1].split(",");
                controlli.put(key, values);
            }
        } catch (IOException e) {
            System.err.println("Errore caricamento controlli: " + e.getMessage());
        }
    }

    public boolean isValidValue(String key, String value) {
        if (key == null || value == null) return false;
        String[] values = controlli.get(key);
        if (values == null) return false;

        for (String it : values) {
            if (value.equalsIgnoreCase(it)) return true;
        }
        return false;
    }

    public boolean isTargaExist(String targa) {
        if (targa == null || targa.length() != 7) return true;
        targa = targa.toUpperCase();
        if (lTarghe.containsKey(targa)) return true;

        lTarghe.put(targa, "");
        return false;
    }

    public Veicoli insertVeicolo(Veicoli v) {
        if (v == null) return null;

        v.setId(++idCounter);
        listV.add(v);
        return v;
    }

    public void remove(Integer id) {
        listV.removeIf(v -> v.getId().equals(id));
    }

    public List<Veicoli> getListV() {
        return listV;
    }

    public void exportListToFile(String pathFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true))) {
            for (Veicoli v : listV) {
                bw.write(v.toString());
                bw.newLine();
                
                
            }
            System.out.println("Lista veicoli scritta su file: " + pathFile);
        } catch (IOException e) {
            System.err.println("Errore scrittura lista veicoli: " + e.getMessage());
        }
    }
}
