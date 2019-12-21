# README

Sovelluksen tarkoituksena on auttaa käyttäjää vieraan kielen sanojen opiskelemisessa. Sovellukseen voidaan luoda useita käyttäjiä, joista jokainen voi lisätä sanoja omaan opeteltavien sanojen listaan. Tämän jälkeen käyttäjä voi harjoitella sanoja joko suomesta vieraaseen kieleen tai päinvastoin. Sanalistat ja käyttäjätiedot tallennetaan erilliseen SQL-tiedostoon, josta sovellus hakee tiedot.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)<br/>
[Työaikaseuranta](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)<br/>
[Käyttöhje](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)<br/>
[Arkkitehtuurikuvaus](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)<br/>
[Testausdokumentti](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

## Releaset
[Viikko 5](https://github.com/jyrki26/ot-harjoitustyo/releases/tag/viikko5) <br/>
[Viikko 6](https://github.com/jyrki26/ot-harjoitustyo/releases/tag/viikko6) <br/>
[Loppupalautus](https://github.com/jyrki26/ot-harjoitustyo/releases/tag/Loppupalautus)


## Komentorivitoiminnot

#### Ohjelman käynnistäminen

Ohjelman voi käynnistää antamlla terminaalissa kansiossa, johon on ladannut Loppupalautus-releasesta kieltenharjoittelu.jar -tiedoston, komennon:

```console
java -jar kieltenharjoittelu.jar
```

tai lataamalla ohjelman kokonaan ja antamalla komennon.

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
mvn test jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*

#### Checkstyle tarkastuksen teko
Koodille voi tehdä checkstyletarkastuksen antamalla terminaalissa kansiossa, jossa sovellus on seuraavan komennon.

``` console
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*

#### Jarin luonti
Ohjelmasta voi luoda jar-tiedoston antamalla konsolissa kansiossa, jossa ohjelma on komennon:
``` console
mvn package
```
Komento generoi hakemistoon target suoritettavan jar-tiedoston *Kieltenharjoitteluohjelma-1.0-SNAPSHOT.jar*

#### JavaDoc
JavaDoc generoidaan komennolla:
```console
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html