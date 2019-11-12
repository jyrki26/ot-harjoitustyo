
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti saldoRiittaa;
    Maksukortti saldoEiRiita;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        saldoRiittaa = new Maksukortti(1000);
        saldoEiRiita = new Maksukortti(10);
    }
    
    @Test
    public void kassapaatteessaRahaaOikein(){
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraOikeinMaukkaat(){
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenLounaidenMaaraOikeinEdulliset(){
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassakasvaaOikeinEdulliset(){
        kassapaate.syoEdullisesti(300);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassakasvaaOikeinMaukkaat(){
        kassapaate.syoMaukkaasti(450);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdulliset(){
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void vaihtorahaOikeinMaukkaat(){
        assertEquals(50, kassapaate.syoMaukkaasti(450));
    }
    
    @Test
    public void myytejenMaaraKasvaaOikeinEdulliset(){
        kassapaate.syoEdullisesti(300);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MyytyjenMaaraKasvaaOikeinMaukkaat(){
        kassapaate.syoMaukkaasti(450);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void myytejenMaaraEiKasvaEdulliset(){
        kassapaate.syoEdullisesti(1);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MyytyjenMaaraEiKasvaMaukkaat(){
        kassapaate.syoMaukkaasti(1);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahaMaaraEiKasvaEdulliset(){
        kassapaate.syoEdullisesti(1);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahaMaaraEiKasvaMaukkaat(){
        kassapaate.syoMaukkaasti(1);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdullisetLiianVahanRahaa(){
        assertEquals(1, kassapaate.syoEdullisesti(1));
    }
    
    @Test
    public void vaihtorahaOikeinMaukkaatLiianVahanRahaa(){
        assertEquals(1, kassapaate.syoMaukkaasti(1));
    }
    
    @Test
    public void saldoVeloitetaanKortiltaEdullinen(){
        kassapaate.syoEdullisesti(saldoRiittaa);
        assertEquals(760, saldoRiittaa.saldo());
    }
    
    @Test
    public void saldoVeloitetaanKortiltaMaukas(){
        kassapaate.syoMaukkaasti(saldoRiittaa);
        assertEquals(600, saldoRiittaa.saldo());
    }
    
    @Test
    public void myytejenMaaraKasvaaKortillaEdullinen(){
        kassapaate.syoEdullisesti(saldoRiittaa);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytejenMaaraKasvaaKortillaMaukas(){
        kassapaate.syoMaukkaasti(saldoRiittaa);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaMaksettaessaPalauttaaTrueEdullinen(){
        assertTrue(kassapaate.syoEdullisesti(saldoRiittaa));
    }
    
    @Test
    public void kortillaMaksettaessaPalauttaaTrueMaukas(){
        assertTrue(kassapaate.syoMaukkaasti(saldoRiittaa));
    }
    
    @Test
    public void kortillaMaksettaessaPalauttaaFalseEdullinen(){
        assertFalse(kassapaate.syoEdullisesti(saldoEiRiita));
    }
    
    @Test
    public void kortillaMaksettaessaPalauttaaFalseMaukas(){
        assertFalse(kassapaate.syoMaukkaasti(saldoEiRiita));
    }
    
    @Test
    public void myytejenMaaraEiKasvaKortillaEdullinen(){
        kassapaate.syoEdullisesti(saldoEiRiita);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytejenMaaraEiKasvaKortillaMaukas(){
        kassapaate.syoMaukkaasti(saldoEiRiita);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinSaldoEiMuutuEdullinen(){
        kassapaate.syoEdullisesti(saldoEiRiita);
        assertEquals(10, saldoEiRiita.saldo());
    }
    
    @Test
    public void kortinSaldoEiMuutuMaukas(){
        kassapaate.syoMaukkaasti(saldoEiRiita);
        assertEquals(10, saldoEiRiita.saldo());
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuEdullinen(){
        kassapaate.syoEdullisesti(saldoRiittaa);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuMaukas(){
        kassapaate.syoMaukkaasti(saldoRiittaa);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void LataaRahaaKortilleTesti(){
        kassapaate.lataaRahaaKortille(saldoRiittaa, 1000);
        assertEquals(2000, saldoRiittaa.saldo());
    }
    
    @Test
    public void LataaRahaaKortilleTestiNegatiivinen(){
        kassapaate.lataaRahaaKortille(saldoRiittaa, -1000);
        assertEquals(1000, saldoRiittaa.saldo());
    }
}
