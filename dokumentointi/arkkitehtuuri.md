# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/package_diagram.jpg " height="400" width="200">

Pakkaus kieltenharjoitteluohjelma.ui sisältää JavaFX:llä toteutetun käyttöliittymän kieltenharjoitteluohjelma.domain sovelluslogiikan ja kieltenharjoitteluohjelma.dao tietojen pysyväistallennuksesta vastaavan koodin.

## Sovelluslogiikka
Sovelluksen datamallin muodostavat luokat User ja Language. Luokat kuvaavat käyttäjiä ja sanojen harjoittelua varten tarvittavia asioita. <br/>

Toiminnallisista kokonaisuuksista vastaa luokka Kieltenharjoitteluservice, joka toteuttaa kaikki käyttöliittymän toiminnot. Luokasta löytyy esimerkiksi seuraavat metodit:
* public boolean createUser(String name, String password)
* public Boolean passwordCorrect(String username, String password)
* public Boolean addWord(String fin, String foreign)
* public void practiseFinForFirst()
* public String getWordToTranslate()

Kieltenharjoitteluservice pääsee käsiksi sanoihin ja käyttäjiin. Tämä tapahtuu rajapintojen LanguageDao ja UserDao kautta, joiden kautta SQL-tiedostosta database luetaan esimerkiksi harjoiteltavat sanat ja käyttäjätiedot. Lisäksi tiedostoon voidaan tallentaa rajapintojen kautta vastaavia tietoja. Yhteys tietokantaan otetaan rajapinnan DatabaseConnection kautta. Rajapinnan toteuttaa luokka Connect.

KieltenharjoitteluServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![package_diagram](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/pakkauskaavio.png)

## Tietojen pysyväistallennus

Ohjelma tallentaa käyttäjiä ja sanoja koskevat tiedot erilliseen SQL-tietokantaan. Varsinaisessa ohjelmassa käytetään database-nimistä tietokantaa ja testausta varten on testdatabase niminen tietokanta, johon on tallennettu käyttäjänimi hello salasanalla world ja sekä ruotsiksi että englanniksi sana pallo käännöksillä en boll ja ball.
Tietokannassa on kolme tietokantataulua Users, English ja Swedish. Taulujen luomiseen käytetty seuraavia SQL-komentoja:

```sql
CREATE TABLE English (
    ID           INTEGER      PRIMARY KEY,
    FINNISH_WORD VARCHAR (50) NOT NULL,
    FOREIGN_WORD VARCHAR (50) NOT NULL
);
```
```sql
CREATE TABLE Swedish (
    ID           INTEGER      PRIMARY KEY,
    FINNISH_WORD VARCHAR (50) NOT NULL,
    FOREIGN_WORD VARCHAR (50) NOT NULL
);
```
```sql
CREATE TABLE Users (
    ID       INTEGER      PRIMARY KEY,
    NAME     VARCHAR (30) NOT NULL,
    PASSWORD VARCHAR (30) NOT NULL
);
```


## Päätoiminnallisuudet

Alla on kuvattu muutamia ohjelman keskeisiä toiminallisuuksia sekvenssikaaviolla.

#### Kirjautuminen

Käyttäjä syöttäessä käyttäjätunnuksen ja salasanan ja painaessa loginBtn-nappia ohjelma etenee seuraavasti:

![Sekvenssikaavio_login](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/sekvenssikaavio_login.png)

Painallukseen reagoiva käyttöliittymä kutsuu sovelluslogiikan metodia passwordCorrect parametreilla käyttäjätunnus ja salasana. Sovelluslogiikka selvittää sitten userDaon avulla onko käyttäjä olemassa ja onko salasana oikein. Jos on, siirtyy ohjelma sovelluksen päänäkymään.

#### Sanan lisääminen tietokantaan

Käyttäjän syöttäessä suomen- ja vieraskielisen sanan ja painaessa addWordsBtn-nappia ohjelma etenee seuraavasti:
![Sekvenssikaavio_addWord](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/sekvenssikaavio_addWord.png)

Painallukseen reagoiva käyttöliittymä kutsuu sovelluslogiikan metodia addWord metodia parametreilla suomenkielinen sana ja vieraskielinen sana. Tämän jälkeen sovelluslogiikka kutsuu LanguageDaoa metodilla addWord parametreilla kieltä kuvaava numero, suomenkielinen sana ja vieraskielinen sana. Lisäksi sovelluslogiikka kutsuu luokkaa Language metodilla addWord parametreilla suomenkielinen sana ja vieraskielinen sana. Luokkaan Language sanalista on jo ladattu aiemmin, joten sanat tallentuvat sinne vain käyttökerran ajaksi ja myöhemmillä käyttökerroilla ne haetaan tietokannasta, kuten muutkin sanat. Mikäli sanan tallentaminen tietokantaan onnistuu, vastaa LanguageDao True. Tämän jälkeen sovelluslogiikka vastaa käyttöliittymälle True.

#### Sanojen harjoittelua

Sanojen harjoitelussa käännettäessä suomenkielinen sana vieraalle kielelle ohjelma etenee seuraavasti:

![Sekvenssikaavio_practise](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/sekvenssikaavio_addWord.png)

Käyttäjän painaessa nappia practiseFinFor ohjelma kutsuu sovelluslogiikkaa metodeilla practiseFinForFirst ja getWordToTranslate. Tämän jälkeen sovelluslogiikka kutsuu luokkaa Language metodilla randomFin ja saa vastauksena satunnaisen käännettävän sanan, jonka sovelluslogiikka palauttaa käyttöliittymälle. Seuraavaksi käyttöliittymä siirtyy harjoittelunäkemään. Käyttäjän kirjoittaessa vastauksen käännettävälle sanalle käyttöliittymä kutsuu sovelluslogiikkaa metodilla practiseSecond parametrilla käyttäjän vastaus. Seuraavaksi käyttöliittymä kutsuu Language-luokkaa metodilla translationFinFor parametrilla käännettävä sana ja Language palauttaa käännöksen. Mikäli käännös vastaa käyttäjän vastausta vastaa sovelluslogiikka True ja käyttöliittymä kertoo vastauksen olleen oikein.

#### Muut toiminnallisuudet
Muut toiminnallisuudet toimivat vastaavalla logiikalla. Käyttöliittymä kutsuu sovelluslogiikkaa, joka kutsuu joko luokkaa Language, johon on ladattu sanalista valmiiksi, kun ohjelmassa valitaan harjoiteltava kieli tai vaihtoehtoisesti ohjelma kutsuu LanguageDao- ja UserDao- luokkia, joiden avulla haetaan tietokannasta tarvittavat tiedot. Vaihtoehtoisesti luokkien avulla tallennetaan tietokantaan tietoa.

## Ohjelman rakenteelliset heikkoudet

#### Käyttöliittymä
Käyttöliittymässä on jonkin verran toisteista koodia. Vaikka käyttöliittymän eri näkymät on erotettu omiksi metodeikseen ja käyttöliittymän pohja haetaan omasta metodistaan, on metodien välillä osittain toisteista koodia. Tämä voitaisiin ratkaista erottamalla tämä toisteinen koodi omaksi metodikseen, joka haettaisiin jokaiseen käyttöliittymän toteuttavaan metodiin tarvittaessa.

#### Käyttäjän tekemät virheet
Ohjelmassa ei ole pystytty huomioimaan kaikkia mahdollisia käyttäjän tekemiä virheitä, jotka voivat aiheuttaa ohjelmassa virhetilanteen esimerksi yhteydessä tietokantaan. Tämä voitaisiin ratkaista laajemmalla testaamisella ja lisäämällä testauksen perusteella käyttöliittymään erilaisia validaatioita käyttäjän syötteeseen.