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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import kieltenharjoitteluohjelma.domain.KieltenharjoitteluService;
import kieltenharjoitteluohjelma.dao.UserDao;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.domain.User;

public class JavaFxInterface extends Application {

    KieltenharjoitteluService service;
    private Scene main;
    private Scene languageMain;
    private Scene practise;
    private Scene addWord;
    private Scene login;
    private Scene addUserScene;
    private String word;

    @Override
    public void init() throws Exception {
        FileUserDao userDao = new FileUserDao();
        service = new KieltenharjoitteluService(userDao);
        word = "";
    }

    @Override
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
        languages = service.getLanguages();
        HBox languageButtons = new HBox();

        for (Integer lang : languages.keySet()) {
            Button button = new Button(languages.get(lang));
            button.setUserData(lang);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int i = (Integer) button.getUserData();
                    service.setLanguage(i);
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
        Text wordToTranslate = new Text();

        addWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(addWord);
            }
        });

        practiseFinFor.setOnAction((event) -> {
            service.practiseFinForFirst();
            word = service.getWordToTranslate();
            wordToTranslate.setText("Käännä seuraava sana: " + word);
            primaryStage.setScene(practise);
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
            service.addWord(finnish.getText(), foreign.getText());
            finnish.clear();
            foreign.clear();
        });

        addWordsPane.setTop(addWordText);
        addWordsPane.setCenter(addWordLayout);
        addWordsPane.setRight(exit);

        addWord = new Scene(addWordsPane, 600, 400);

        // Practise
        BorderPane practisePane = new BorderPane();

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

        practisePane.setTop(wordToTranslate);
        practisePane.setRight(exit);
        practisePane.setCenter(translationBox);

        answer.setOnAction((ActionEvent event) -> {
            responseText.setText(service.practiseFinForSec(translation.getText()));
            practisePane.setBottom(newWordBox);
        });

        newWord.setOnAction((event) -> {
            service.practiseFinForFirst();
            word = service.getWordToTranslate();
            translation.clear();
            wordToTranslate.setText("Käännä seuraava sana: " + word);
            practisePane.setBottom(null);
        });

        practise = new Scene(practisePane, 600, 400);

        // Login scene
        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setHgap(20);
        loginPane.setVgap(10);
        loginPane.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Tervetuloa");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        loginPane.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Käyttäjätunnus:");
        loginPane.add(userName, 0, 1);

        TextField userTextField = new TextField();
        loginPane.add(userTextField, 1, 1);

        Label pw = new Label("Salasana:");
        loginPane.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Salasana");
        loginPane.add(pwBox, 1, 2);

        Button btn = new Button("Kirjaudu sisään");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        loginPane.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        loginPane.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (service.passwordCorrect(userTextField.getText(), pwBox.getText())) {
                    primaryStage.setScene(main);
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Käyttäjätunnus tai salasana ei ole oikein.");
                    userTextField.clear();
                    pwBox.clear();
                }
            }
        });

        Button newUserbtn = new Button("Uusi käyttäjä");
        HBox newUserBox = new HBox();
        newUserBox.setAlignment(Pos.BOTTOM_LEFT);
        newUserBox.getChildren().add(newUserbtn);
        loginPane.add(newUserBox, 0, 4);

        newUserbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.setScene(addUserScene);
            }
        });

        // New User
        GridPane newUserPane = new GridPane();
        newUserPane.setAlignment(Pos.CENTER);
        newUserPane.setHgap(10);
        newUserPane.setVgap(10);
        newUserPane.setPadding(new Insets(25, 25, 25, 25));

        Text newUserTitle = new Text("Lisää uusi käyttäjä");
        newUserTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        newUserPane.add(newUserTitle, 0, 0, 2, 1);

        Label newUserName = new Label("Anna käyttäjätunnus:");
        newUserPane.add(newUserName, 0, 1);

        TextField newUserTextField = new TextField();
        newUserPane.add(newUserTextField, 1, 1);

        Label pw1 = new Label("Salasana:");
        newUserPane.add(pw1, 0, 2);

        PasswordField pw1Box = new PasswordField();
        pw1Box.setPromptText("Salasana");
        newUserPane.add(pw1Box, 1, 2);

        Label pw2 = new Label("Salasana uudestaan:");
        newUserPane.add(pw2, 0, 3);

        PasswordField pw2Box = new PasswordField();
        pw2Box.setPromptText("Salasana uudestaan");
        newUserPane.add(pw2Box, 1, 3);

        Button addUser = new Button("Luo käyttäjä");
        HBox addUserButton = new HBox(10);
        addUserButton.setAlignment(Pos.BOTTOM_RIGHT);
        addUserButton.getChildren().add(addUser);
        newUserPane.add(addUserButton, 1, 4);

        final Text addUserText = new Text();
        newUserPane.add(addUserText, 1, 6);

        addUser.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (pw1Box.getText().equals(pw2Box.getText())) {
                    service.createUser(newUserTextField.getText(), pw1Box.getText());
                    primaryStage.setScene(login);
                } else {
                    addUserText.setFill(Color.FIREBRICK);
                    addUserText.setText("Salasanat eivät vastaa toisiaan.");
                    pw2Box.clear();
                }
            }
        });

        addUserScene = new Scene(newUserPane, 300, 275);

        login = new Scene(loginPane, 300, 275);

        primaryStage.setTitle("Kieltenharjoitteluohjelma");
        primaryStage.setScene(login);
        primaryStage.show();

    }

    public Button exitButton() {
        Button exit = new Button("Poistu");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        return exit;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
