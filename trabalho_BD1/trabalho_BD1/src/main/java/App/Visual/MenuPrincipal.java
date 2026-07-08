package App.Visual;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Painel Administrativo - ACC");

        // CABECALHO
        Label lblTitulo = new Label("SISTEMA DE GESTAO ACADEMICA");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: 800; -fx-text-fill: #ffffff; -fx-font-family: 'Segoe UI', Helvetica; -fx-letter-spacing: 1px;");

        Label lblSubtitulo = new Label("Selecione um modulo operacional para insercao e consultas");
        lblSubtitulo.setStyle("-fx-font-size: 11px; -fx-text-fill: #7f8c8d; -fx-font-family: 'Segoe UI'; -fx-padding: 0 0 15 0;");

        // INSTANCIACAO DOS BOTOES
        Button btnECFC = new Button("Estrutura Academica (ECFC)");
        Button btnCD = new Button("Secretaria e Discentes (CD)");
        Button btnCSA = new Button("Processos de Validação (CSA)");
        Button btnDC = new Button("Documentacao e Certificados (DC)");

        // ESTILIZACAO VIA CSS
        String estiloBotaoBase =
                "-fx-background-color: #2a2a35; "
                        + "-fx-text-fill: #ffffff; "
                        + "-fx-font-size: 13px; "
                        + "-fx-font-weight: 600; "
                        + "-fx-font-family: 'Segoe UI'; "
                        + "-fx-min-width: 320px; "
                        + "-fx-min-height: 48px; "
                        + "-fx-background-radius: 8px; "
                        + "-fx-border-color: #3a3a45; "
                        + "-fx-border-radius: 8px; "
                        + "-fx-border-width: 1px; "
                        + "-fx-cursor: hand; "
                        + "-fx-transition: all 0.2s ease-in-out;";

        String estiloHover =
                "-fx-background-color: #3a3a4a; " //clareia levemente no hover
                        + "-fx-text-fill: #3498db; " //destaca o texto em azul
                        + "-fx-border-color: #3498db;"; //borda acende em azul

        // Aplicando estilo base
        Button[] botoes = {btnECFC, btnCD, btnCSA, btnDC};
        for (Button btn : botoes) {
            btn.setStyle(estiloBotaoBase);

            // SOMBRA SUAVE INDIVIDUAL NOS BOTOES
            DropShadow sombraBotao = new DropShadow(4, Color.web("#000000", 0.15));
            sombraBotao.setOffsetY(2);
            btn.setEffect(sombraBotao);

            // EFEITO VISUAL DINAMICO (HOVER) AO PASSAR O MOUSE
            btn.setOnMouseEntered(e -> btn.setStyle(estiloBotaoBase + estiloHover));
            btn.setOnMouseExited(e -> btn.setStyle(estiloBotaoBase));
        }

        // --- MAPEAMENTO DAS ACOES DAS JANELAS ---
        btnECFC.setOnAction(e -> new TelaECFC().start(new Stage()));
        btnCD.setOnAction(e -> new TelaCD().start(new Stage()));
        btnCSA.setOnAction(e -> new TelaCSA().start(new Stage()));
        btnDC.setOnAction(e -> new TelaDC().start(new Stage()));

        // --- CONTAINER E BACKGROUND PRINCIPAL ---
        VBox layout = new VBox(14);
        layout.setPadding(new Insets(35));
        layout.setAlignment(Pos.CENTER);

        // Fundo Dark
        layout.setStyle("-fx-background-color: #1e1e24;");
        layout.getChildren().addAll(lblTitulo, lblSubtitulo, btnECFC, btnCD, btnCSA, btnDC);

        // Ajustando a proporcao da janela para nao sobrar espaco morto
        Scene scene = new Scene(layout, -1, -1);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
