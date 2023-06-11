package org.decompression;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.drawtree.LoadingScreen;
import org.drawtree.PrintTree;
import org.menuButton.*;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

public class TreeController {
    final FileChooser fileChooser = new FileChooser();
    private String inputPath;
    private String nameFile;
    private ButtonStatus status;
    @FXML
    private Button backButton;
    @FXML
    private TextField extensionText;
    @FXML
    private TextArea textArea;
    @FXML
    private MenuButton menuButton;
    @FXML
    private Label info;
    @FXML
    private AnchorPane container;
    @FXML
    private Button decompress;
    @FXML
    private PasswordField passwordField;
    private String exten;
    private boolean isEncrypted;
    private final ChangeListener<String> zmianoSluchacz = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            hidePasswordField();
            hideButtonAnimation();
            decompress.setOnAction(e -> {
                try {
                    onDecompression();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    };


    // pain
    @FXML
    private void txt() {
        status = new txt();
        exten = status.changeExtension(menuButton);
    }


    @FXML
    private void doc() {
        status = new doc();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void docx() {
        status = new docx();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void jpeg() {
        status = new jpeg();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void png() {
        status = new png();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void gif() {
        status = new gif();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void mp3() {
        status = new mp3();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void mp4() {
        status = new mp4();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void mov() {
        status = new mov();
        exten = status.changeExtension(menuButton);
    }

    @FXML
    private void others() {
        menuButton.setVisible(false);
        extensionText.setVisible(true);
        backButton.setVisible(true);
        info.setVisible(true);
    }
    @FXML
    private void goBack() {
        menuButton.setVisible(true);
        menuButton.setText("Extension");
        extensionText.setVisible(false);
        backButton.setVisible(false);
        info.setVisible(false);
    }

    private void passwordRequired() {
        decompress.setOnAction(e ->  {
            try {
                if(passwordField.getText().isEmpty()) {
                    passwordField.setStyle("-fx-border-color: RED");
                }
                else {
                    nameFile = nameFile + exten;
                    goToDecompression(inputPath, nameFile, passwordField.getText());

                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    private void moveButtonAnimation() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3));
        translateTransition.setNode(decompress);
        translateTransition.setToY(70);
        translateTransition.play();
    }

    private void hideButtonAnimation() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3));
        translateTransition.setNode(decompress);
        translateTransition.setToY(0);
        translateTransition.play();
    }

    private void showPasswordField() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3));
        fadeTransition.setNode(passwordField);
        passwordField.setVisible(true);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void hidePasswordField() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3));
        fadeTransition.setNode(passwordField);
        passwordField.setVisible(false);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }



    private void changeData(File file) throws IOException {
        if (file != null) {
            inputPath = file.getAbsolutePath();
            Path pth = Paths.get("Decompressed Files");
            try {
                if (!Files.exists(pth)) {
                    Files.createDirectory(pth);
                }
            } catch (IOException e) {
                throw new IOException(e);
            }

            nameFile = pth.getName(0) + "/" + file.getName();
            int ifDot = nameFile.lastIndexOf('.');
            if (ifDot != -1) {
                nameFile = nameFile.substring(0, ifDot);
            }
        }
    }

    public void setItem() {
        changedFile();
        fileChooser.setTitle("Choose file");
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            textArea.setText(file.getAbsolutePath());
        }
    }

    //trochę głupie, ale inaczej będzie ich tyle, ile razy się kliknie Choose File lub pole do wpisywania ścieżki pliku
    @FXML
    private void changedFile() {
            textArea.textProperty().removeListener(zmianoSluchacz);
            textArea.textProperty().addListener(zmianoSluchacz);
    }

    public void onDecompression() throws Exception {
        File fil = new File(textArea.getText());
        try {
            changeData(fil);
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException();
        } catch (IOException ioe) {
            throw new IOException(ioe);
        }
        if(inputPath != null) {
            textArea.setStyle("-fx-border-color: #5e10d9");
            try {
                isEncrypted = CheckInput.isEncryptRequired(inputPath);
                if(!menuButton.isVisible()) {
                    if(!extensionText.getText().isEmpty()) {
                        nameFile = nameFile + "." + extensionText.getText();
                        //CheckInput.isEncryptRequired(inputPath);
                        goToDecompression(inputPath, nameFile, passwordField.getText());
                    } else {
                        menuButton.setVisible(true);
                        extensionText.setVisible(false);
                        backButton.setVisible(false);
                        info.setVisible(false);
                    }
                } else {
                    if(exten == null) {
                        PauseTransition del = new PauseTransition(Duration.seconds((0.3)));
                        del.setOnFinished(e -> menuButton.setStyle("-fx-border-color: RED"));
                        del.play();
                    }
                    else if(isEncrypted) {
                        PauseTransition delay1 = new PauseTransition(Duration.seconds(0.3));
                        delay1.setOnFinished(e -> showPasswordField());
                        delay1.play();
                        passwordRequired();
                        moveButtonAnimation();
                    } else {
                        nameFile = nameFile + exten;
                        goToDecompression(inputPath, nameFile, passwordField.getText());
                    }
                }
            } catch (EOFException | FileNotFoundException e) {
                WarningScreen warningScreen = new WarningScreen("File not found or empty!");
                warningScreen.start(StageSingleton.getStageInstance());
            }

        } else {
            textArea.setStyle("-fx-border-color: RED");
        }



    }
    private void goToDecompression(String inputFile, String outputFile, String password) throws Exception {
        Stage stage = (Stage) container.getScene().getWindow();
        AnchorPane root = (AnchorPane) container.getScene().getRoot();
        root.getChildren().clear();
        Decompression decompression = new Decompression(inputFile, outputFile, password);
        final boolean[] check = new boolean[1];
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                if(decompression.ifCheckIsCorrect) {
                    check[0] = decompression.decode();
                }
                return null;
            }
        };
        task.setOnRunning(event -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.start(stage);
        });
        task.setOnSucceeded(event -> {
            if(decompression.ifCheckIsCorrect) {
                if(check[0]) {
                    PrintTree printTree = new PrintTree(decompression.tree.root);
                    printTree.start(stage);
                } else {
                    WarningScreen warningScreen = new WarningScreen("Huffdecomp only works on 8-bit compressed files!");
                    warningScreen.start(stage);
                }
            } else {
                WarningScreen warningScreen = new WarningScreen("Input file cannot be decompressed - either incorrect or corrupted.");
                warningScreen.start(stage);
            }

        });
        new Thread(task).start();
        if(isEncrypted)
            decompression.XOR();

    }
}