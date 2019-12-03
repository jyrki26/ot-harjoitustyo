# Arkkitehtuurikuvaus

Dao-luokkia ei ole vielä toteutettu.

## Sovelluslogiikka

### Alustava luokkakaavio
![Luokkakaavio](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/class_diagram.jpg)

### Alustava rakennekuvaus
<img src="https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/package_diagram.jpg " height="400" width="200">

## Päätoiminnallisuudet

#### Kirjautuminen

Käyttäjä syöttäessä käyttäjätunnuksen ja salasanan ja painaessa loginBtn-nappia ohjelma etenee seuraavasti:

![Sekvenssikaavio](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/sekvenssikaavio_login.png)

Painallukseen reagoiva käyttöliittymä kutsuu sovelluslogiikan metodia passwordCorrect parametreilla käyttäjätunnus ja salasana. Sovelluslogiikka selvittää sitten userDaon avulla onko käyttäjä olemassa ja onko salasana oikein. Jos on, siirtyy ohjelma sovelluksen päänäkymään.