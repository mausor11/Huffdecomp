package org.decompression;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.drawtree.LoadingScreen;
import org.drawtree.PrintTree;

import java.io.File;

public class TreeController  {
    final FileChooser fileChooser = new FileChooser();
    String inputPath;
    String nameFile;
    @FXML
    private Button button;
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
    public void setItem() {
        fileChooser.setTitle("Choose file");
        File file = fileChooser.showOpenDialog(null);
        menuButton.setText("Extension");
        if(file != null ) {
            textArea.setText(file.getAbsolutePath());
            inputPath = file.getAbsolutePath();
            nameFile = file.getName();
            int ifDot = nameFile.lastIndexOf('.');
            if(ifDot != -1) {
                nameFile = nameFile.substring(0, nameFile.lastIndexOf('.'));
            }

        }

    }
    public void txt() {
        menuButton.setText("txt");
        exten =  ".txt";
    }

    public void doc() {
        menuButton.setText("doc");
        exten =  ".doc";
    }

    public void docx() {
        menuButton.setText("docx");
        exten =  ".docx";
    }

    public void jpeg() {
        menuButton.setText("jpeg");
        exten =  ".jpeg";
    }

    public void png() {
        menuButton.setText("png");
        exten =  ".png";
    }

    public void gif() {
        menuButton.setText("gif");
        exten =  ".gif";
    }

    public void mp3() {
        menuButton.setText("mp3");
        exten =  ".mp3";
    }

    public void mp4() {
        menuButton.setText("mp4");
        exten =  ".mp4";
    }

    public void mov() {
        menuButton.setText("mov");
        exten =  ".mov";
    }

    public void others() {
        menuButton.setVisible(false);
        extensionText.setVisible(true);
        backButton.setVisible(true);
        info.setVisible(true);
    }
    public void goBack() {
        menuButton.setVisible(true);
        menuButton.setText("Extension");
        extensionText.setVisible(false);
        backButton.setVisible(false);
        info.setVisible(false);
    }
    //todo: poprawić Label(jest czarny text), zmienić passwordField zeby byl pod decompress
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
/*
    private void extensionRequired() {
        try {
            if (menuButton.getText().equals("Extension")) {
                menuButton.setStyle("-fx-border-color: RED");
            }
            else {
                goToDecompression(inputPath, nameFile, passwordField.getText());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
}
*/


    private void moveButtonAnimation() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3));
        translateTransition.setNode(decompress);
        translateTransition.setToY(70);
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
    public void onDecompression() throws Exception {
        System.out.println(inputPath);
        if(inputPath != null) {
            textArea.setStyle("-fx-border-color: #5e10d9");
            if(!menuButton.isVisible()) {
                System.out.println("a");
                if(!extensionText.getText().isEmpty()) {
                    nameFile = nameFile + "." + extensionText.getText();
                    boolean isEncrypted = CheckInput.isEncryptRequired(inputPath);
                    System.out.println("Encryption: " + isEncrypted);
                    goToDecompression(inputPath, nameFile, passwordField.getText());
                } else {
                    menuButton.setVisible(true);
                    extensionText.setVisible(false);
                    backButton.setVisible(false);
                    info.setVisible(false);
                }
            } else {
                System.out.println("b");
                boolean isEncrypted = CheckInput.isEncryptRequired(inputPath);
                System.out.println("Encryption: " + isEncrypted);
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
        } else {
            textArea.setStyle("-fx-border-color: RED");
        }



    }
    private void goToDecompression(String inputFile, String outputFile, String password) throws Exception {
        Stage stage = (Stage) container.getScene().getWindow();
        AnchorPane root = (AnchorPane) container.getScene().getRoot();
        root.getChildren().clear();
        Decompression decompression = new Decompression(inputFile, outputFile, password);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                decompression.decode();
                return null;
            }
        };
        task.setOnRunning(event -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.start(stage);
        });
        task.setOnSucceeded(event -> {
            PrintTree printTree = new PrintTree(decompression.tree.root);
            printTree.start(stage);
        });
        new Thread(task).start();

    }
}
