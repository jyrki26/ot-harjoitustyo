# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksen  tarkoituksena on auttaa käyttäjää vieraan kielen sanojen opiskelemisessa. Sovellukseen voidaan luoda useita käyttäjiä, joista jokainen voi lisätä sanoja opeteltavien sanojen listaan. Tämän jälkeen käyttäjä voi harjoitella sanoja joko suomesta vieraaseen kieleen tai päinvastoin. Sanalistat ja käyttäjätiedot tallennetaan erilliseen SQL-tiedostoon, josta sovellus hakee tiedot.

## Käyttäjät
Sovellukseen tulee aluksi ainoastaan yksi käyttäjäryhmä, tavalliset käyttäjät. Myöhemmin mahdollisesti lisätään ylläpitokäyttäjät, joilla on mahdollisuus muokata tallennettuja sanalistoja laajemmin.

## Suunnitellut toiminnallisuudet
* Sovellus toimii graafisessa käyttöliittymässä.

#### Ennen kirjautumista
* Käyttäjä voi luoda järjestelmään käyttäjätunnuksen 
  * Käyttäjätunnuksen tulee olla uniikki ja vähintään kaksi merkkiä pitkä
  * Käyttäjätunnukseen liittyy salasana
 * Ohjelma estää virheelliset syötteet.
* Käyttäjä pääsee kirjautumaan järjestelmään syöttämällä käyttäjätunnuksen ja salasanan

#### Kirjautumisen jälkeen
* Käyttäjä siirtyy näkymään, jossa valitaan harjoiteltava kieli. Tällä hetkellä harjoiteltavia kieliä ovat englanti ja ruotsi.
* Tämän jälkeen käyttäjä siirtyy näkymään, jossa voi valita joko harjoittelun sanojen kääntämisestä suomesta vieraaseen kieleen tai päinvastoin tai valita sanojen lisäämisen valitsemalleen kielelle.
	* Harjoiteltaessa ohjelma kertoo onko vastaus väärin vai oikein ja jos vastaus on väärin, kertoo oikean vastauksen.
* Ohjelmassa on sivupalkki, josta pääsee joko kielen valintaan, harjoittelun tai sanojen lisäämisen valintaan tai suoraan sanojen lisäämiseen.
* Ohjelma arpoo kysyttävät sanat.
* Ohjelma estää virheelliset syötteet.
* Käyttäjä voi kirjautua ulos järjestelmästä kaikissa näkymissä.


## Jatkokehitysideoita
* Ohjelma arpoo kysyttävät sanat painottaen vähemmän kysyttyjä tai sanoja, joihin on vastattu useasti väärin.
* Ohjelma tilastoi oikeat ja väärät vastaukset sanakohtaisesti.
* Käyttäjäkohtaiset sanalistat.
* Tilastonäkymän luominen
  * Näkymässä näkyisivät käyttäjän antamia vastauksia koskevat tilastot, kuten annettujen vastausten määrä ja oikeiden vastausten prosenttimäärä.
* Harjoittelu-tilan laajentaminen
  * Esim. mahdollisuus harjoitella sanoja vain tietystä sanaluokasta tai aihealueesta
  * Tämä edellyttäisi kyseisten tietojen tallentamismahdollisuuden lisäämistä sanojen lisäys -näkymään
* Ylläpitokäyttäjän lisääminen
  * Käyttäjä voisi esim. muokata kaikkien käyttäjien sanalistoja tai yhdistellä näitä.
  
