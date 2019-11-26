package kieltenharjoitteluohjelma.ui;

import java.util.TreeMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import kieltenharjoitteluohjelma.domain.Kieltenharjoittelu;

public class JavaFxInterface extends Application {

    Kieltenharjoittelu domain;
    private Scene main;
    private Scene languageMain;
    private Scene practise;
    private Scene addWord;
    private int language;
    private String translatableWord;

    @Override
    public void init() throws Exception {
        domain = new Kieltenharjoittelu();
        language = 0;
    }

    public void start(Stage primaryStage) {
        //main window

        Label mainText = new Label("Valitse kieli, jota haluat harjoitella.");
        BorderPane mainPane = new BorderPane();
        HBox buttons = new HBox();

        mainPane.setTop(mainText);
        mainPane.setCenter(buttons);

        BorderPane.setMargin(mainText, new Insets(0.0f, 0.0f, 0.0f, 0.0f));

        Button exit = new Button("Poistu");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        TreeMap<Integer, String> languages = new TreeMap<>();
        languages = domain.getLanguages();
        HBox languageButtons = new HBox();

        for (Integer lang : languages.keySet()) {
            Button button = new Button(languages.get(lang));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    language = lang;
                    primaryStage.setScene(languageMain);
                }
            });
            languageButtons.getChildren().add(button);
        }

        BorderPane.setMargin(buttons, new Insets(40.0f, 0.0f, 0.0f, 0.0f));

        mainPane.setRight(exit);
        mainPane.setTop(mainText);
        mainPane.setCenter(languageButtons);

        main = new Scene(mainPane, 600, 400);

        // Language Main Scene
        GridPane languagePane = new GridPane();

        Label languageText = new Label("Valitse joko sanojen lisääminen tai harjoittelu suomesta vieraalle kielelle tai päinvastoin");
        Button addWords = new Button("Lisää sanoja");
        Button practiseFinFor = new Button("Harjoittele suomesta vieraaseen kieleen");
        Button practiseForFin = new Button("Harjoittelu vieraasta kielestä suomeen");

        addWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(addWord);
            }
        });

        practiseFinFor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(practise);
            }
        });

        languagePane.add(exit, 4, 1);
        languagePane.add(languageText, 0, 2, 3, 1);
        languagePane.add(addWords, 0, 4);
        languagePane.add(practiseFinFor, 0, 5);
        languagePane.add(practiseForFin, 0, 6);

        languagePane.setAlignment(Pos.TOP_CENTER);

        languageMain = new Scene(languagePane, 600, 400);

        // Add New Words
        BorderPane addWordsPane = new BorderPane();
        GridPane addWordLayout = new GridPane();

        Label addWordText = new Label("Lisää sana antamalla sana suomeksi ja vieraalla kielellä alla oleviin kenttiin.");
        Label wordFin = new Label("Sana suomeksi:");
        Label wordFor = new Label("Sana vieraalla kielellä:");
        Button add = new Button("Lisää");
        TextField finnish = new TextField();
        TextField foreign = new TextField();

        addWordLayout.add(wordFin, 1, 1);
        addWordLayout.add(finnish, 2, 1);
        addWordLayout.add(wordFor, 1, 2);
        addWordLayout.add(foreign, 2, 2);
        addWordLayout.add(add, 1, 3);

        add.setOnAction((event) -> {
            domain.addWord(language, finnish.getText(), foreign.getText());
            finnish.clear();
            foreign.clear();
        });

        addWordsPane.setTop(addWordText);
        addWordsPane.setCenter(addWordLayout);
        addWordsPane.setRight(exit);

        addWord = new Scene(addWordsPane, 600, 400);

        // Practise
        BorderPane practisePane = new BorderPane();

        translatableWord = domain.practiseFinForFirst(language);
        Label wordToTranslate = new Label("Käännä seuraava sana: " + translatableWord);

        Label translationLabel = new Label("Käännös");
        TextField translation = new TextField();
        Button answer = new Button("Vastaa");
        Button newWord = new Button("Uusi sana");
        Label responseText = new Label();

        HBox translationBox = new HBox();
        translationBox.getChildren().addAll(translationLabel, translation, answer);
        GridPane newWordBox = new GridPane();
        newWordBox.add(responseText, 1, 1, 3, 1);
        newWordBox.add(newWord, 1, 4);

        answer.setOnAction((event) -> {
            responseText.setText(domain.practiseFinForSec(language, translatableWord, answer.getText()));
            practisePane.setBottom(newWordBox);
        });

        newWord.setOnAction((event) -> {
            translatableWord = domain.practiseFinForFirst(language);
            String word2 = translatableWord;
            translation.clear();
            wordToTranslate.setText("Käännä seuraava sana: " + word2);
            practisePane.setBottom(null);
        });

        practisePane.setTop(wordToTranslate);
        practisePane.setRight(exit);
        practisePane.setCenter(translationBox);

        practise = new Scene(practisePane, 600, 400);

        primaryStage.setTitle("Kieltenharjoitteluohjelma");
        primaryStage.setScene(main);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
