package App.Visual;

import App.DAO_DataAccessObject.ConexaoBanco;
import App.DAO_DataAccessObject.EnderecoDAO;
import App.DAO_DataAccessObject.CampusDAO;
import App.DAO_DataAccessObject.FaculdadeDAO;
import App.DAO_DataAccessObject.CursoDAO;
import App.Model.Endereco;
import App.Model.Campus;
import App.Model.Faculdade;
import App.Model.Curso;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaECFC extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Estrutura Acadêmica (Endereço, Campus, Faculdade, Curso)");

        // Divisão por abas para não amontoar os dados
        TabPane tabPane = new TabPane();
        Tab tabCad = new Tab("Lançar Estrutura"); tabCad.setClosable(false);
        Tab tabConsultas = new Tab("Auditoria Estrutural"); tabConsultas.setClosable(false);

        // --- PALETA DE CORES DARK PREMIUM ---
        String estiloFundo = "-fx-background-color: #1e1e24;";
        String estiloTitulo = "-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #3498db; -fx-padding: 5 0 5 0;"; // Azul tecnológico
        String estiloLabel = "-fx-text-fill: #ecf0f1; -fx-font-weight: bold;";
        String estiloTxt = "-fx-background-color: #ffffff; -fx-background-radius: 4px; -fx-padding: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 4px;";
        String estiloBtn = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-min-height: 26px;"; // Verde Sucesso
        String estiloBtnTudo = "-fx-background-color: #f1c40f; -fx-text-fill: #2c3e50; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-min-height: 32px; -fx-min-width: 200px;"; // Amarelo Destaque
        String estiloBtnConsulta = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;";

        // ==========================================
        // ABA 1: FORMULÁRIO DE CADASTRO DISTRIBUÍDO
        // ==========================================
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle(estiloFundo);

        // Campos de texto - Endereco
        TextField txtIdEndereco = new TextField();     TextField txtLogadouro = new TextField();
        TextField txtNumero = new TextField();         TextField txtCidade = new TextField();
        TextField txtEstado = new TextField();         TextField txtBairro = new TextField();
        TextField txtCep = new TextField();            Button btnSalvarEndereco = new Button("1. Salvar Endereço");

        // Campos de texto - Campus
        TextField txtIdCampus = new TextField();       TextField txtNomeCampus = new TextField();
        TextField txtSigla  = new TextField();         TextField txtIdEnderecoFK = new TextField();
        Button btnSalvarCampus = new Button("2. Salvar Campus");

        // Campos de texto - Faculdade
        TextField txtIdFaculdade = new TextField();    TextField txtNomeFaculdade = new TextField();
        TextField txtIdCampusFK = new TextField();     Button btnSalvarFaculdade = new Button("3. Salvar Faculdade");

        // Campos de texto - Curso
        TextField txtIdCurso = new TextField();        TextField txtNome_curso = new TextField();
        TextField txtNivel_curso = new TextField();    TextField txtDuracao_curso = new TextField();
        TextField txtIdFaculdadeFK = new TextField();  Button btnSalvarCurso = new Button("4. Salvar Curso");
        Button btnSalvarTudo = new Button("Salvar Estrutura Completa");

        // Estilização em lote para manter a interface uniforme
        TextField[] todosInputs = {txtIdEndereco, txtLogadouro, txtNumero, txtCidade, txtEstado, txtBairro, txtCep, txtIdCampus, txtNomeCampus, txtSigla, txtIdEnderecoFK, txtIdFaculdade, txtNomeFaculdade, txtIdCampusFK, txtIdCurso, txtNome_curso, txtNivel_curso, txtDuracao_curso, txtIdFaculdadeFK};
        for (TextField tf : todosInputs) tf.setStyle(estiloTxt);

        Button[] botoesIndiretos = {btnSalvarEndereco, btnSalvarCampus, btnSalvarFaculdade, btnSalvarCurso};
        for (Button btn : botoesIndiretos) btn.setStyle(estiloBtn);
        btnSalvarTudo.setStyle(estiloBtnTudo);

        // Lado Esquerdo (Colunas 0 e 1) - Endereço e Campus
        Label lblT1 = new Label("============= 1. ENDEREÇO ============"); lblT1.setStyle(estiloTitulo);
        grid.add(lblT1, 0, 0, 2, 1);
        grid.add(criarLabel("ID Endereço:", estiloLabel), 0, 1);     grid.add(txtIdEndereco, 1, 1);
        grid.add(criarLabel("Logradouro:", estiloLabel), 0, 2);      grid.add(txtLogadouro, 1, 2);
        grid.add(criarLabel("Número:", estiloLabel), 0, 3);          grid.add(txtNumero, 1, 3);
        grid.add(criarLabel("Cidade:", estiloLabel), 0, 4);          grid.add(txtCidade, 1, 4);
        grid.add(criarLabel("Estado:", estiloLabel), 0, 5);          grid.add(txtEstado, 1, 5);
        grid.add(criarLabel("Bairro:", estiloLabel), 0, 6);          grid.add(txtBairro, 1, 6);
        grid.add(criarLabel("CEP:", estiloLabel), 0, 7);            grid.add(txtCep, 1, 7);
        grid.add(btnSalvarEndereco, 1, 8);

        Label lblT2 = new Label("============ 2. CAMPUS ============"); lblT2.setStyle(estiloTitulo);
        grid.add(lblT2, 0, 9, 2, 1);
        grid.add(criarLabel("ID Campus:", estiloLabel), 0, 10);     grid.add(txtIdCampus, 1, 10);
        grid.add(criarLabel("Nome Campus:", estiloLabel), 0, 11);   grid.add(txtNomeCampus, 1, 11);
        grid.add(criarLabel("Sigla:", estiloLabel), 0, 12);         grid.add(txtSigla, 1, 12);
        grid.add(criarLabel("ID Endereço (FK):", estiloLabel), 0, 13); grid.add(txtIdEnderecoFK, 1, 13);
        grid.add(btnSalvarCampus, 1, 14);

        // Lado Direito (Colunas 2 e 3) - Faculdade e Curso
        Label lblT3 = new Label("============ 3. FACULDADE ============"); lblT3.setStyle(estiloTitulo);
        grid.add(lblT3, 2, 0, 2, 1);
        grid.add(criarLabel("ID Faculdade:", estiloLabel), 2, 1);   grid.add(txtIdFaculdade, 3, 1);
        grid.add(criarLabel("Nome Faculdade:", estiloLabel), 2, 2); grid.add(txtNomeFaculdade, 3, 2);
        grid.add(criarLabel("ID Campus (FK):", estiloLabel), 2, 3);  grid.add(txtIdCampusFK, 3, 3);
        grid.add(btnSalvarFaculdade, 3, 4);

        Label lblT4 = new Label("=========== 4. CURSO ============="); lblT4.setStyle(estiloTitulo);
        grid.add(lblT4, 2, 6, 2, 1);
        grid.add(criarLabel("ID Curso:", estiloLabel), 2, 7);       grid.add(txtIdCurso, 3, 7);
        grid.add(criarLabel("Nome Curso:", estiloLabel), 2, 8);     grid.add(txtNome_curso, 3, 8);
        grid.add(criarLabel("Nível:", estiloLabel), 2, 9);      grid.add(txtNivel_curso, 3, 9);
        grid.add(criarLabel("Duração:", estiloLabel), 2, 10);        grid.add(txtDuracao_curso, 3, 10);
        grid.add(criarLabel("ID Faculdade (FK):", estiloLabel), 2, 11); grid.add(txtIdFaculdadeFK, 3, 11);
        grid.add(btnSalvarCurso, 3, 12);

        // Botão de transação mestre unificada centralizado embaixo
        grid.add(btnSalvarTudo, 2, 14, 2, 1);
        tabCad.setContent(grid);

        // ==========================================
        // ABA 2: CONSULTAS GERENCIAIS DO SEMINÁRIO
        // ==========================================
        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(estiloFundo);

        Label lblT5 = new Label("AUDITORIA RELACIONAL DE INFRAESTRUTURA"); lblT5.setStyle(estiloTitulo);

        Button btnMapear = new Button("Auditar Infraestrutura (Campus + Endereço via JOIN)"); btnMapear.setStyle(estiloBtnConsulta);

        TextArea txtRes = new TextArea(); txtRes.setEditable(false);
        txtRes.setStyle("-fx-font-family: 'Consolas'; -fx-control-inner-background: #2a2a35; -fx-text-fill: #ffffff; -fx-font-size: 12px;");
        txtRes.setPrefHeight(340);

        vbox.getChildren().addAll(lblT5, btnMapear, txtRes);
        tabConsultas.setContent(vbox);

        tabPane.getTabs().addAll(tabCad, tabConsultas);

        // --- LÓGICA DE PROGRAMAÇÃO DAS TRANSACÕES (SEUS BOTÕES) ---

        btnSalvarTudo.setOnAction(e -> {
            try (Connection conn = ConexaoBanco.obterConexao()){
                conn.setAutoCommit(false);

                Endereco endereco = new Endereco(Integer.parseInt(txtIdEndereco.getText()),txtLogadouro.getText(),txtNumero.getText(),txtCidade.getText(),txtEstado.getText(),txtBairro.getText(),txtCep.getText());
                Campus campus = new Campus(Integer.parseInt(txtIdCampus.getText()) ,txtNomeCampus.getText(), txtSigla.getText(), Integer.parseInt(txtIdEnderecoFK.getText()));
                Faculdade faculdade = new Faculdade(Integer.parseInt(txtIdFaculdade.getText()),txtNomeFaculdade.getText(),Integer.parseInt(txtIdCampusFK.getText()));
                Curso curso = new Curso(Integer.parseInt(txtIdCurso.getText()), txtNome_curso.getText(), txtNivel_curso.getText(), txtDuracao_curso.getText(), Integer.parseInt(txtIdFaculdadeFK.getText()));

                new EnderecoDAO().inserir(endereco);
                new CampusDAO().inserir(campus);
                new FaculdadeDAO().inserir(faculdade);
                new CursoDAO().inserir(curso);

                conn.commit();
                new Alert(Alert.AlertType.INFORMATION, "ECFC Salvo com sucesso!").showAndWait();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro de integridade:" + ex.getMessage()).showAndWait(); }
        });

        // SALVAR INDIVIDUAL 1
        btnSalvarEndereco.setOnAction(e -> {
            try (Connection conn = ConexaoBanco.obterConexao()) {
                conn.setAutoCommit(false);
                Endereco endereco = new Endereco(Integer.parseInt(txtIdEndereco.getText()), txtLogadouro.getText(), txtNumero.getText(), txtCidade.getText(), txtEstado.getText(), txtBairro.getText(), txtCep.getText());
                new EnderecoDAO().inserir(endereco);
                conn.commit();
                new Alert(Alert.AlertType.INFORMATION, "Endereço Salvo com sucesso!").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro de integridade:" + ex.getMessage()).showAndWait();
            }
        });

        // SALVAR INDIVIDUAL 2
        btnSalvarCampus.setOnAction(e -> {
            try (Connection conn = ConexaoBanco.obterConexao()) {
                conn.setAutoCommit(false);
                Campus campus = new Campus(Integer.parseInt(txtIdCampus.getText()), txtNomeCampus.getText(), txtSigla.getText(), Integer.parseInt(txtIdEnderecoFK.getText()));
                new CampusDAO().inserir(campus);
                conn.commit();
                new Alert(Alert.AlertType.INFORMATION, "Campus Salvo com sucesso!").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro de integridade:" + ex.getMessage()).showAndWait();
            }
        });

        // SALVAR INDIVIDUAL 3
        btnSalvarFaculdade.setOnAction(e -> {
            try (Connection conn = ConexaoBanco.obterConexao()) {
                conn.setAutoCommit(false);
                Faculdade faculdade = new Faculdade(Integer.parseInt(txtIdFaculdade.getText()), txtNomeFaculdade.getText(), Integer.parseInt(txtIdCampusFK.getText()));
                new FaculdadeDAO().inserir(faculdade);
                conn.commit();
                new Alert(Alert.AlertType.INFORMATION, "Faculdade Salva com sucesso!").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro de integridade:" + ex.getMessage()).showAndWait();
            }
        });

        // SALVAR INDIVIDUAL 4
        btnSalvarCurso.setOnAction(e -> {
            try (Connection conn = ConexaoBanco.obterConexao()) {
                conn.setAutoCommit(false);
                Curso curso = new Curso(Integer.parseInt(txtIdCurso.getText()), txtNome_curso.getText(), txtNivel_curso.getText(), txtDuracao_curso.getText(), Integer.parseInt(txtIdFaculdadeFK.getText()));
                new CursoDAO().inserir(curso);
                conn.commit();
                new Alert(Alert.AlertType.INFORMATION, "Curso Salvo com sucesso!").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro de integridade:" + ex.getMessage()).showAndWait();
            }
        });

        // Consulta de Seminario 3: INNER JOIN estrutural entre Campus e Endereço
        btnMapear.setOnAction(e -> {
            String sql = "SELECT c.nome, c.sigla, e.logadouro, e.numero, e.cidade " +
                    "FROM Campus c " +
                    "INNER JOIN Endereco e ON c.idEndereco = e.idEndereco";
            try (Connection conn = ConexaoBanco.obterConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                StringBuilder sb = new StringBuilder("--- INFRAESTRUTURA: CAMPUS E LOCALIDADES ATIVAS ---\n");
                while (rs.next()) {
                    sb.append(String.format("Campus: %-20s (%-3s) | Logradouro: %s, Nº %s - %s\n",
                            rs.getString("nome"), rs.getString("sigla"), rs.getString("logadouro"), rs.getString("numero"), rs.getString("cidade")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) {
                txtRes.setText("Erro ao extrair junção relational: " + ex.getMessage());
            }
        });

        // Redimensionamento proporcional para visualização lado a lado limpa
        primaryStage.setScene(new Scene(tabPane, -1, -1));
        primaryStage.show();
    }

    // Metodo auxiliar com sobrecarga opcional para compatibilidade
    private Label criarLabel(String texto, Object opcional, String estilo) {
        return criarLabel(texto, estilo);
    }

    private Label criarLabel(String texto, String estilo) {
        Label l = new Label(texto);
        l.setStyle(estilo);
        return l;
    }
}

