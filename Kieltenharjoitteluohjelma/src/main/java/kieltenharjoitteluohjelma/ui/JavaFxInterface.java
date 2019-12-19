package kieltenharjoitteluohjelma.ui;

import java.sql.SQLException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import kieltenharjoitteluohjelma.dao.FileLanguageDao;
import kieltenharjoitteluohjelma.domain.KieltenharjoitteluService;
import kieltenharjoitteluohjelma.dao.UserDao;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.dao.LanguageDao;
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
    private Text loginText = new Text();
    private Text wordToTranslate = new Text();
    private static Stage stage;

    @Override
    public void init() throws Exception {
        FileUserDao userDao = new FileUserDao();
        FileLanguageDao languageDao = new FileLanguageDao();
        service = new KieltenharjoitteluService(userDao, languageDao);
        word = "";
        wordToTranslate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        getStage().setTitle("Kieltenharjoitteluohjelma");
        getStage().setScene(loginScene());
        getStage().show();

    }

    public Scene loginScene() {
        GridPane loginPane = layout();

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

        loginPane.add(loginText, 0, 6, 2, 2);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    if (service.passwordCorrect(userTextField.getText(), pwBox.getText())) {
                        getStage().setScene(mainScene());
                    } else {
                        loginText.setFill(Color.FIREBRICK);
                        loginText.setText("Käyttäjätunnus tai salasana ei ole oikein.");
                        userTextField.clear();
                        pwBox.clear();
                    }
                } catch (SQLException ex) {
                    loginText.setText("Tietokantayhteydessä on virhe. Yritä myöhemmin uudestaan.");
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
                getStage().setScene(addUserScene());
            }
        });
        login = new Scene(loginPane, 600, 400);
        return login;
    }

    public Scene addUserScene() {
        GridPane newUserPane = layout();

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

        Button returnButton = new Button("Palaa kirjautumiseen");
        newUserPane.add(returnButton, 0, 4);

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                getStage().setScene(loginScene());
            }
        });
        final Text addUserText = new Text();
        newUserPane.add(addUserText, 1, 6);

        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (newUserTextField.getText().length() < 2 || pw1Box.getText().length() < 2) {
                    addUserText.setFill(Color.FIREBRICK);
                    addUserText.setText("Käyttäjätunnuksen ja salasanan tulee olla vähintään kahden merkin mittaisia.");
                } else if (!pw1Box.getText().equals(pw2Box.getText())) {
                    addUserText.setText("Salasanat eivät vastaa toisiaan.");
                    pw2Box.clear();
                } else if (service.createUser(newUserTextField.getText(), pw1Box.getText())) {
                    loginText.setFill(Color.GREEN);
                    loginText.setText("Uusi käyttäjä " + newUserTextField.getText() + " lisätty");
                    getStage().setScene(loginScene());
                } else if (service.createUser(newUserTextField.getText(), pw1Box.getText()) == false) {
                    addUserText.setFill(Color.FIREBRICK);
                    addUserText.setText("Käyttäjätunnus on jo käytössä.");
                    pw2Box.clear();
                } else {
                    addUserText.setText("Tietokantayhteydessä on ongelma. Yritä myöhemmin uudestaan.");
                }
            }
        });

        addUserScene = new Scene(newUserPane, 600, 400);
        return addUserScene;
    }

    public Scene mainScene() {
        BorderPane bp = new BorderPane();
        GridPane mainPane = layout();

        Text mainText = new Text("Valitse kieli, jota haluat harjoitella.");
        mainText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        mainPane.add(mainText, 0, 0, 2, 1);

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
                    try {
                        service.WordsFromDatabase();
                    } catch (SQLException ex) {
                    }
                    getStage().setScene(languageMainScene());
                }
            });
            languageButtons.getChildren().add(button);
        }
        languageButtons.setSpacing(10);
        mainPane.add(languageButtons, 0, 1);

        bp.setCenter(mainPane);
        bp.setTop(logoutBox());

        main = new Scene(bp, 400, 350);
        return main;
    }

    public Scene languageMainScene() {
        BorderPane bp = new BorderPane();
        GridPane languagePane = layout();

        Text languageText = new Text("Valitse joko sanojen lisääminen tai harjoittelu \nsuomesta vieraalle kielelle tai päinvastoin");
        languageText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Button addWords = new Button("Lisää sanoja");
        Button practiseFinFor = new Button("Harjoittele suomesta vieraaseen kieleen");
        Button practiseForFin = new Button("Harjoittelu vieraasta kielestä suomeen");

        addWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getStage().setScene(addNewWordsScene());
            }
        });

        practiseFinFor.setOnAction((event) -> {
            service.practiseFinForFirst();
            word = service.getWordToTranslate();
            wordToTranslate.setText("Käännä seuraava sana: " + word);
            getStage().setScene(practiseScene());
        });

        practiseForFin.setOnAction((event) -> {
            service.practiseForFinFirst();
            word = service.getWordToTranslate();
            wordToTranslate.setText("Käännä seuraava sana: " + word);
            getStage().setScene(practiseScene());
        });

        languagePane.add(languageText, 0, 2, 4, 2);
        languagePane.add(addWords, 0, 4);
        languagePane.add(practiseFinFor, 0, 5);
        languagePane.add(practiseForFin, 0, 6);

        languagePane.setAlignment(Pos.TOP_CENTER);

        bp.setCenter(languagePane);
        bp.setTop(logoutBox());

        languageMain = new Scene(bp, 600, 400);
        return languageMain;
    }

    public Scene addNewWordsScene() {
        BorderPane bp = new BorderPane();
        GridPane addWordPane = layout();

        Text addWordText = new Text("Lisää sana antamalla sana suomeksi ja \nvieraalla kielellä alla oleviin kenttiin.");
        addWordText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Text wordFin = new Text("Sana suomeksi:");
        Text wordFor = new Text("Sana vieraalla kielellä:");
        Button add = new Button("Lisää");
        TextField finnish = new TextField();
        TextField foreign = new TextField();

        addWordPane.add(addWordText, 0, 0, 4, 2);
        addWordPane.add(wordFin, 0, 2);
        addWordPane.add(finnish, 1, 2);
        addWordPane.add(wordFor, 0, 3);
        addWordPane.add(foreign, 1, 3);
        addWordPane.add(add, 0, 4);

        add.setOnAction((event) -> {
            service.addWord(finnish.getText(), foreign.getText());
            finnish.clear();
            foreign.clear();
        });

        bp.setCenter(addWordPane);
        bp.setLeft(sidePanel());
        bp.setTop(logoutBox());

        addWord = new Scene(bp, 600, 400);
        return addWord;
    }

    public Scene practiseScene() {
        BorderPane bp = new BorderPane();
        GridPane practisePane = layout();

        Label translationLabel = new Label("Käännös");
        TextField translation = new TextField();
        translation.maxWidth(Double.MAX_VALUE);
        Button answer = new Button("Vastaa");
        Button newWord = new Button("Uusi sana");
        Label responseText = new Label();
        HBox answerBox = new HBox();
        answerBox.getChildren().addAll(translationLabel, translation, answer);
        answerBox.setSpacing(10);

        practisePane.add(wordToTranslate, 0, 0, 2, 1);
        practisePane.add(answerBox, 0, 2);

        answer.setDefaultButton(true);

        answer.setOnAction((ActionEvent event) -> {
            if (service.practiseSecond(translation.getText())) {
                responseText.setText("Oikein!");
                practisePane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                responseText.setText("Väärin. Vastasit " + translation.getText() + ". Oikea vastaus on: " + service.getCorrectAnswer());
                practisePane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            practisePane.add(responseText, 0, 3);
            practisePane.add(newWord, 0, 4);
            translation.clear();
            answer.setDisable(true);
            answer.setDefaultButton(false);
            newWord.setDefaultButton(true);
        });

        newWord.setOnAction((event) -> {
            practisePane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
            if (service.getDirection() == 1) {
                service.practiseFinForFirst();
            } else {
                service.practiseForFinFirst();
            }
            word = service.getWordToTranslate();
            wordToTranslate.setText("Käännä seuraava sana: " + word);
            practisePane.getChildren().remove(responseText);
            practisePane.getChildren().remove(newWord);
            answer.setDisable(false);
            newWord.setDefaultButton(false);
            answer.setDefaultButton(true);
        });

        bp.setCenter(practisePane);
        bp.setLeft(sidePanel());
        bp.setTop(logoutBox());

        practise = new Scene(bp, 600, 400);
        return practise;
    }

    public HBox logoutBox() {
        StackPane userStack = new StackPane();
        User user = service.getLoggedIn();
        Text username = new Text("Käyttäjä " + user.getName());
        username.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 12));

        userStack.getChildren().add(username);
        userStack.setAlignment(Pos.CENTER_LEFT);

        StackPane exitStack = new StackPane();
        Button exit = new Button("Kirjaudu ulos");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginText.setText("Olet kirjautunut ulos.");
                loginText.setFill(Color.GREEN);
                service.logout();
                getStage().setScene(loginScene());
            }
        });

        exitStack.getChildren().add(exit);
        exitStack.setAlignment(Pos.CENTER_RIGHT);

        HBox logout = new HBox();
        HBox.setHgrow(exitStack, Priority.ALWAYS);
        logout.getChildren().addAll(userStack, exitStack);
        logout.setSpacing(10);
        logout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        return logout;
    }

    public GridPane layout() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5));
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        return gridPane;
    }

    public VBox sidePanel() {
        VBox vbox = new VBox();

        Button selectLanguage = new Button("Valitse kieli");
        selectLanguage.setMaxWidth(Double.MAX_VALUE);
        selectLanguage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getStage().setScene(mainScene());
            }
        });

        Button addWords = new Button("Lisää sanoja");
        addWords.setMaxWidth(Double.MAX_VALUE);
        addWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getStage().setScene(addNewWordsScene());
            }
        });

        Button practiseWords = new Button("Harjoittele");
        practiseWords.setMaxWidth(Double.MAX_VALUE);
        practiseWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getStage().setScene(languageMainScene());
            }
        });

        vbox.getChildren().addAll(selectLanguage, addWords, practiseWords);
        vbox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
