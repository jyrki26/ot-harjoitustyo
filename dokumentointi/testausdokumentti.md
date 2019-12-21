# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

### Yksikkö ja integraatitestaus

Sovelluslogiikkaa on testattu testaamalla pakkauksen [kieltenharjoitteluohjelma.domain](https://github.com/jyrki26/ot-harjoitustyo/tree/master/Kieltenharjoitteluohjelma/src/main/java/kieltenharjoitteluohjelma/domain) luokkia. 
Luokkia testaavat testiluokat [KieltenharjoitteluServiceTest.java](https://github.com/jyrki26/ot-harjoitustyo/blob/master/Kieltenharjoitteluohjelma/src/test/java/domain/KieltenharjoitteluServiceTest.java) ja [LanguageTest.java](https://github.com/jyrki26/ot-harjoitustyo/blob/master/Kieltenharjoitteluohjelma/src/test/java/domain/LanguageTest.java). 
Luokan User testaamista ei ole nähty tarpeelliseksi, koska luokassa on vain gettereitä ja settereitä ja luokka toiminta tulee testattua KieltenharjoitteluServiceTest.java luokan testien kautta.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia [FakeLanguageDao](https://github.com/jyrki26/ot-harjoitustyo/blob/master/Kieltenharjoitteluohjelma/src/test/java/domain/FakeLanguageDao.java) ja [FakeUserDao](https://github.com/jyrki26/ot-harjoitustyo/blob/master/Kieltenharjoitteluohjelma/src/test/java/domain/FakeUserDao.java).

### DAO-luokkien testaus

Molempia DAO-luokkia on testattu hyödyntämällä testausta varten luotua erillistä tietokantatiedostoa testdatabase, johon yhdistetään DatabaseConnection-rajapinnan kautta. Käyttäjän lisäämistä testattaessa tietokannasta poistetaan lisätyt tiedot testien ajamisen jälkeen.

### Testauskattavuus
Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 83% ja haarautumakattavuus 79%

![testraport](https://github.com/jyrki26/ot-harjoitustyo/blob/master/dokumentointi/testraport.png)

###

