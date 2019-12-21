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

Kieltenharjoitteluservice pääsee käsiksi sanoihin ja käyttäjiin. Tämä  tapahtuu rajapintojen LanguageDao ja UserDao kautta, joiden kautta SQL-tiedostosta database luetaan esimerkiksi harjoiteltavat sanat ja käyttäjätiedot. Lisäksi tiedostoon voidaan tallentaa rajapintojen kautta vastaavia tietoja.

## Päätoiminnallisuudet

#### Kirjautuminen

Käyttäjä syöttäessä käyttäjätunnuksen ja salasanan ja painaessa loginBtn-nappia ohjelma etenee seuraavasti:

![Sekvenssikaavio](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/sekvenssikaavio_login.png)

Painallukseen reagoiva käyttöliittymä kutsuu sovelluslogiikan metodia passwordCorrect parametreilla käyttäjätunnus ja salasana. Sovelluslogiikka selvittää sitten userDaon avulla onko käyttäjä olemassa ja onko salasana oikein. Jos on, siirtyy ohjelma sovelluksen päänäkymään.

### Alustava luokkakaavio
![Luokkakaavio](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/class_diagram.jpg)