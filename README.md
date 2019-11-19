# README

Sovelluksen tarkoituksena on auttaa käyttäjää vieraan kielen sanojen opiskelemisessa. Sovellukseen voidaan luoda useita käyttäjiä, joista jokainen voi lisätä sanoja omaan opeteltavien sanojen listaansa. Tämän jälkeen käyttäjä voi harjoitella oman listansa sanoja joko suomesta vieraaseen kieleen tai päinvastoin. Sanalistat ja käyttäjätiedot tallennetaan erilliseen SQL-tiedostoon, josta sovellus hakee tiedot.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)<br/>
[Työaikaseuranta](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)<br/>
[Käyttöhje](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

## Komentorivitoiminnot

#### Ohjelman käynnistäminen

```console
mvn compile exec:java -Dexec.mainClass=kieltenharjoitteluohjelma.ui.Main
```

#### Testaus

Testit voi suorittaa komennolla
```console
mvn test
```

Testikattavuusraportti luodaan komennolla
```console
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*