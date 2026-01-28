package com.betacom.car.models;

import java.time.LocalDate;

public class Veicoli {
	private Integer id;   // id univoco del record
	private String tipoVeicolo;  // Macchina, Moto, Bici
	private Integer numeroRuote; // 1 -> 4  dipende del tipo veicolo
	private String tipoAlimentazione; // bensine,diesel, electrica, hybrid, manuale 
	private String categoria;  // strada, fuoristrada, suv, città, sportiva
	private String colore;     // bianco, rosso, nero.......
	private String marca;     // fiat, renault, bmw,yamaha, bianchi.....
	private Integer annoProduzione;
	private String modello;   // fiat 500, kuga, ....
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoVeicolo() {
		return tipoVeicolo;
	}
	public void setTipoVeicolo(String tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}
	public Integer getNumeroRuote() {
		return numeroRuote;
	}
	public void setNumeroRuote(Integer numeroRuote) {
		this.numeroRuote = numeroRuote;
	}
	public String getTipoAlimentazione() {
		return tipoAlimentazione;
	}
	public void setTipoAlimentazione(String tipoAlimentazione) {
		this.tipoAlimentazione = tipoAlimentazione;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getAnnoProduzione() {
		return annoProduzione;
	}
	public void setAnnoProduzione(Integer annoProduzione) {
		this.annoProduzione = annoProduzione;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}

}
