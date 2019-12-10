# Käyttöohje
Ohjelma käynnistetään lataamalla releasessa Viikko 6 oleva tiedosto ja käynnistämällä se terminaalissa kansioissa, jossa tiedosto on komennolla.
```console
java -jar Kieltenharjoittelu.jar
```
 <br/> <br/>
Ohjelmassa on tällä hetkellä luotu ohjelman runko. Kaikki perustoiminnallisuudet toimivat, mutta ne eivät toimi suhteessa toisiinsa, koska tiedon tallentamista SQL-tiedostoon ei ole vielä toteutettu.

<br/>

Kirjautuessa kirjaudutaan käyttäjänimellä hello ja salasanalla world. Uuden käyttäjän lisäämistä voi myös testata, mutta koska näkymästä ei pääse toistaiseksi takaisin kirjautumisikkunaan, joutuu ohjelman sammuttamaan välissä, jolloin käyttäjä katoaa.

<br/>

Varsinaisessa käyttönäkymässä sanojen lisäämistä voi käyttää valitsemalla sanojen lisäämisen ja sanoja voi harjoitella suomesta vieraaseen kieleen. Vieraasta kielestä suomen harjoittelu ei toimi toistaiseksi. Tällä hetkellä harjoittelun testaamiseksi on lisätty ainoastaan muutama sana suomeksi ja englanniksi, joten myös ruotsiksi harjoitellessa tulee vastata englanniksi.