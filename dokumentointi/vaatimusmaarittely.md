# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksen  tarkoituksena on auttaa käyttäjää vieraan kielen sanojen opiskelemisessa. Sovellukseen voidaan luoda useita käyttäjiä, joista jokainen voi lisätä sanoja omaan opeteltavien sanojen listaansa. Tämän jälkeen käyttäjä voi harjoitella oman listansa sanoja joko suomesta vieraaseen kieleen tai päinvastoin. Sanalistat ja käyttäjätiedot tallennetaan erilliseen SQL-tiedostoon, josta sovellus hakee tiedot.

## Käyttäjät
Sovellukseen tulee aluksi ainoastaan yksi käyttäjäryhmä, tavalliset käyttäjät. Myöhemmin mahdollisesti lisätään ylläpitokäyttäjät, joilla on mahdollisuus muokata tallennettuja sanalistoja laajemmin.

## Suunnitellut toiminnallisuudet
* Sovellus toimii graafisessa käyttöliittymässä. **Tehty** (Viikko 6)

#### Ennen kirjautumista
* Käyttäjä voi luoda järjestelmään käyttäjätunnuksen **Tehty**
  * Käyttäjätunnuksen tulee olla uniikki ja vähintään kolme merkkiä pitkä
  * Käyttäjätunnukseen liittyy salasana **Tehty** 
* Käyttäjä pääsee kirjautumaan järjestelmään syöttämällä käyttäjätunnuksen ja salasanan **Tehty**

#### Kirjautumisen jälkeen
* Käyttäjä siirtyy näkymään, jossa voi lisätä sanoja haluamalleen kielelle antamalla sanan ja käännöksen molemmilla kielillä **Tehty**
  * Kielen voi valita näkymässä olevista napeista **Tehty**
* Näkymässä on painike, josta pääsee harjoittelu-tilaan **Tehty**
* Harjoittelu-tilassa ohjelma kysyy sanoja joko antamalla suomenkielisen sanan, jolle pitää antaa vastine vieraalla kielellä tai päinvastoin **Tehty** (Viikko 6)
  * Käyttäjä voi valita haluamansa suunnan painamalla näkymässä olevaa painiketta **Tehty**
* Ohjelma tilastoi oikeat ja väärät vastaukset sanakohtaisesti.
* Ohjelma arpoo kysyttävät sanat painottaen vähemmän kysyttyjä tai sanoja, joihin on vastattu useasti väärin.
* Käyttäjä voi kirjautua ulos järjestelmästä kummassakin näkymässä.


## Jatkokehitysideoita
* Tilastonäkymän luominen
  * Näkymässä näkyisivät käyttäjän antamia vastauksia koskevat tilastot, kuten annettujen vastausten määrä ja oikeiden vastausten prosenttimäärä.
* Harjoittelu-tilan laajentaminen
  * Esim. mahdollisuus harjoitella sanoja vain tietystä sanaluokasta tai aihealueesta
  * Tämä edellyttäisi kyseisten tietojen tallentamismahdollisuuden lisäämistä sanojen lisäys -näkymään
* Ylläpitokäyttäjän lisääminen
  * Käyttäjä voisi esim. muokata kaikkien käyttäjien sanalistoja tai yhdistellä näitä.
  
