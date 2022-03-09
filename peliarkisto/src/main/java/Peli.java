/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Peli {
    
    private String nimi;
    private String jarjestelma;
    private String kommentit;

    
    public Peli(String nimi, String jarjestelma, String kommentit) {
        this.nimi = nimi;
        this.jarjestelma = jarjestelma;
        this.kommentit = kommentit;
    }
    public String getNimi() {
        return nimi;
    }
    public String getJarjestelma() {
        return this.jarjestelma;
    }
    public String getKommentit() {
        return this.kommentit;
    }
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public void setJarjestelma(String jarjestelma) {
        this.jarjestelma = jarjestelma;
    }
    public void setKommentit(String kommentit) {
        this.kommentit = kommentit;
    }  
}
