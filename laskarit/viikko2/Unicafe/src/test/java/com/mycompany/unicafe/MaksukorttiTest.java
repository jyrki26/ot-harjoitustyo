package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein (){
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void saldoKasvaaOikein(){
        kortti.lataaRahaa(990);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneJosEiRahaaTarpeeksi(){
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi(){
        kortti.lataaRahaa(990);
        kortti.otaRahaa(240);
        assertEquals("saldo: 7.60", kortti.toString());
    }
    
    @Test
    public void ostaminenPalauttaaTrueJosSaldoRiittaa(){
        kortti.lataaRahaa(990);
        assertTrue(kortti.otaRahaa(240));
    }
    
    @Test
    public void ostaminenPalauttaaFalseJosSaldoRiittaa(){
        assertFalse(kortti.otaRahaa(240));
    }
}
