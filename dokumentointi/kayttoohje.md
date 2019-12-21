# Käyttöohje
Lataa tiedosto ja samassa releasessa olevat tietokannat database ja testdatabase. Tietokannat tulee sijoittaa Kieltenharjoitteluohjelman juurikansioon.

### Käynnistäminen
Ohjelma käynnistetään komennolla:
```console
java -jar Kieltenharjoittelu.jar
```

### Kirjautuminen ja uuden käyttäjän luominen
Ohjelmaan voi kirjautua käyttäjätunnuksella *hello* ja salasanalla *world*, jotka on jätetty tietokantaan ohjelman kokeilemista varten. Vaihtoehtoisesti voit luoda uuden käyttäjätunnuksen painamalla Uusi käyttäjä -nappia. Kaikilla käyttäjillä on samat oikeudet ja samat sanalistat käytössä, joten käytettävällä käyttäjällä ei ole väliä.

Alla kuvat pääikkunasta ja uusi käyttäjä -ikkunasta:

![main window](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/mainWindow.png)
![new user](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/newUser.png)

Kirjautuminen tapahtuu syöttämällä olemassa oleva käyttäjätunnus ja salasana ja painamalla Kirjaudu sisään. Uusi käyttäjä luodaan antamalla käyttäjätunnus ja salasana kahteen kertaan ja painamalla Luo käyttäjä. Kumpikaan ei saa olla yli 30:n merkin mittainen. Kun käyttäjä on luotu, palaa ohjelma automaattisesti kirjautumisikkunaan.

### Harjoittelu
Sisäänkirjautumisen jälkeen ohjelma siirtyy valintaan, jossa valitaan kieli. Tällä hetkellä kieleksi voi valita ruotsin tai englannin. Valinta tapahtuu painamalla kyseistä kieltä.
</br>
Tämän jälkeen valitaan joko sanojen lisääminen tai harjoittelu painamalla kyseistä nappia. Harjoitella voi joko sanojen kääntämistä suomesta vieraaseen kieleen tai päinvastoin. Ohjelma arpoo satunnaisen sanan kaikille käyttäjille yhteisestä kyseisen kielen sanalistasta. Tietokantaan on jätetty joitakin sanoja ohjelman testaamista varten.
</br>
Harjoittelu tapahtuu kääntämällä annettu sana ja painamalla Vastaa-nappia. Tämän jälkeen ohjelma ilmoittaa oliko sana oikein vai väärin. Jos vastaus on väärin, kertoo ohjelma oikean vastauksen. Uuden sanan saa harjoiteltavaksi tämän jälkeen painamalla Uusi sana -nappia.

![practise](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/practise.png)

### Sanojen lisääminen
Uusi sana lisätään siirtymällä Lisää sanoja -valikkoon. Valikkoon voi siirtyä joko kielen valinnan jälkeisestä ikkunasta tai harjoittelu-ikkunan sivupalkissa näkyvästä Lisää sanoja -napista.
Uusi sana lisätään antamalla sana suomeksi ja vieraalla kielellä ja painamalla Lisää-nappia. Sana ei saa olla kummallakaan kielellä yli 50 merkkiä pitkä. Ohjelma ilmoittaa napin painamisen jälkeen onnistuiko sanan lisääminen. Tämän jälkeen voi halutessaan lisätä lisää sanoja.
![add words](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/addWords.png)

