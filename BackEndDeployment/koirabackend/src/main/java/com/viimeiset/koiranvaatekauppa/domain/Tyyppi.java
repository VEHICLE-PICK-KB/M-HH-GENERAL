package com.viimeiset.koiranvaatekauppa.domain;

public enum Tyyppi {
    VAATE,
    LELU,
    RUOKA;

    public String getDisplayName() {

        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }
}