package com.viimeiset.koiranvaatekauppa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tuoteNimi;
    private Tyyppi tyyppi;
    private String vari;
    private Koko koko;
    private double hinta;
    private Integer maara;

    @ManyToOne
    @JoinColumn(name = "valmistaja_id")
    private Valmistaja valmistaja;

    public Tuote() {

    }

    public Tuote(String tuoteNimi, Tyyppi tyyppi, String vari, Koko koko, double hinta, Valmistaja valmistaja,
            Integer maara) {
        this.tuoteNimi = tuoteNimi;
        this.tyyppi = tyyppi;
        this.vari = vari;
        this.koko = koko;
        this.hinta = hinta;
        this.valmistaja = valmistaja;
        this.maara = maara;
    }

    public Long getId() {
        return this.id;
    }

    public String getTuoteNimi() {
        return this.tuoteNimi;
    }

    public void setTuoteNimi(String tuoteNimi) {
        this.tuoteNimi = tuoteNimi;
    }

    public Tyyppi getTyyppi() {
        return this.tyyppi;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getVari() {
        return this.vari;
    }

    public void setVari(String vari) {
        this.vari = vari;
    }

    public Koko getKoko() {
        return this.koko;
    }

    public void setKoko(Koko koko) {
        this.koko = koko;
    }

    public double getHinta() {
        return this.hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Valmistaja getValmistaja() {
        return valmistaja;
    }

    public void setValmistaja(Valmistaja valmistaja) {
        this.valmistaja = valmistaja;
    }

    public Integer getMaara() {
        return this.maara;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

}