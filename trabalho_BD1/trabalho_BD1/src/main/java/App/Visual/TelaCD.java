package App.Visual;

import App.DAO_DataAccessObject.ConexaoBanco;
import App.DAO_DataAccessObject.CoordenadorDAO;
import App.DAO_DataAccessObject.DiscenteDAO;
import App.Model.Coordenador;
import App.Model.Discente;

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
import java.sql.Date;

public class TelaCD extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Coordenador e Discente");

        // Painel de Abas para separar Cadastros de Relatorios de Auditoria
        TabPane tabPane = new TabPane();
        Tab tabCad = new Tab("Lançar Cadastros"); tabCad.setClosable(false);
        Tab tabConsultas = new Tab("Auditoria e Relatórios Gerenciais"); tabConsultas.setClosable(false);

        // --- PALETA DE CORES DARK ---
        String estiloFundo = "-fx-background-color: #1e1e24;";
        String estiloTitulo = "-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #3498db; -fx-padding: 5 0 5 0;"; // Azul tecnológico
        String estiloLabel = "-fx-text-fill: #ecf0f1; -fx-font-weight: bold;";
        String estiloTxt = "-fx-background-color: #ffffff; -fx-background-radius: 4px; -fx-padding: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 4px;";
        String estiloBtn = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-min-height: 28px;"; // Verde Sucesso
        String estiloBtnConsulta = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;";

        // ABA 1: CONFIGURACAO DO FORMULARIO
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle(estiloFundo);

        // Campo de texto - Coordenador
        TextField txtIdCoordenador = new TextField();
        TextField txtNome_coordenador = new TextField();
        DatePicker pickerData_nascimento = new DatePicker();
        TextField txtCpf = new TextField();
        TextField txtSexo = new TextField();
        TextField txtMatricula = new TextField();
        Button btnCadastrarCoordenador = new Button("Cadastrar Coordenador");

        // Campo de texto - Discente
        TextField txtIdDiscente = new TextField();
        TextField txtMatricula_discente = new TextField();
        DatePicker pickerData_ingresso = new DatePicker();
        TextField txtPeriodoLetivo = new TextField();
        TextField txtNome_discente = new TextField();
        DatePicker pickerData_nascimentoDiscente = new DatePicker();
        TextField txtCpfDiscente = new TextField();
        TextField txtSexoDiscente = new TextField();
        TextField txtIdCursoFK = new TextField();
        Button btnCadastrarDiscente = new Button("Cadastrar Discente");

        // Estilizacao em lote para manter a interface uniforme
        TextField[] todosInputs = {txtIdCoordenador, txtNome_coordenador, txtCpf, txtSexo, txtMatricula, txtIdDiscente, txtMatricula_discente, txtPeriodoLetivo, txtNome_discente, txtCpfDiscente, txtSexoDiscente, txtIdCursoFK};
        for (TextField tf : todosInputs) tf.setStyle(estiloTxt);
        pickerData_nascimento.setStyle(estiloTxt);
        pickerData_ingresso.setStyle(estiloTxt);
        pickerData_nascimentoDiscente.setStyle(estiloTxt);
        btnCadastrarCoordenador.setStyle(estiloBtn);
        btnCadastrarDiscente.setStyle(estiloBtn);

        // Coordenador
        Label lblT1 = new Label("=============== CADASTRO COORDENADOR ==============="); lblT1.setStyle(estiloTitulo);
        grid.add(lblT1, 0, 0, 2, 1);
        grid.add(criarLabel("ID Coordenador:", estiloLabel), 0, 1);      grid.add(txtIdCoordenador, 1, 1);
        grid.add(criarLabel("Nome Completo:", estiloLabel), 0, 2);       grid.add(txtNome_coordenador, 1, 2);
        grid.add(criarLabel("Data Nascimento:", estiloLabel), 0, 3);     grid.add(pickerData_nascimento, 1, 3);
        grid.add(criarLabel("CPF:", estiloLabel), 0, 4);                 grid.add(txtCpf, 1, 4);
        grid.add(criarLabel("Sexo:", estiloLabel), 0, 5);                grid.add(txtSexo, 1, 5);
        grid.add(criarLabel("Matricula:", estiloLabel), 0, 6);           grid.add(txtMatricula, 1, 6); // CORRIGIDO: Era 16, mudado para 6
        grid.add(btnCadastrarCoordenador, 1, 7);

        // Discente
        Label lblT2 = new Label("================= CADASTRO DISCENTE ================="); lblT2.setStyle(estiloTitulo);
        grid.add(lblT2, 2, 0, 2, 1);
        grid.add(criarLabel("ID Discente:", estiloLabel), 2, 1);         grid.add(txtIdDiscente, 3, 1);
        grid.add(criarLabel("Matricula:", estiloLabel), 2, 2);           grid.add(txtMatricula_discente, 3, 2);
        grid.add(criarLabel("Data Ingresso:", estiloLabel), 2, 3);       grid.add(pickerData_ingresso, 3, 3);
        grid.add(criarLabel("Período Letivo:", estiloLabel), 2, 4);      grid.add(txtPeriodoLetivo, 3, 4);
        grid.add(criarLabel("Nome Completo:", estiloLabel), 2, 5);       grid.add(txtNome_discente, 3, 5);
        grid.add(criarLabel("Data Nascimento:", estiloLabel), 2, 6);     grid.add(pickerData_nascimentoDiscente, 3, 6);
        grid.add(criarLabel("CPF Aluno:", estiloLabel), 2, 7);           grid.add(txtCpfDiscente, 3, 7);
        grid.add(criarLabel("Sexo Aluno:", estiloLabel), 2, 8);          grid.add(txtSexoDiscente, 3, 8);
        grid.add(criarLabel("Vincular ID Curso (FK):", estiloLabel), 2, 9); grid.add(txtIdCursoFK, 3, 9);
        grid.add(btnCadastrarDiscente, 3, 10);

        tabCad.setContent(grid);

        // ABA 2: CONSULTAS GERENCIAIS DO REQUISITO
        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(estiloFundo);

        Label lblT3 = new Label("HISTORICO DE ACUMULO DE HORAS"); lblT3.setStyle(estiloTitulo);

        Button btnPontuacaoTotal = new Button("Gerar Relatorio de Pontuacao Acumulada por Discente"); btnPontuacaoTotal.setStyle(estiloBtnConsulta);
        Button btnAguardando = new Button("Listar Solicitacoes com Validacoo Pendente"); btnAguardando.setStyle(estiloBtnConsulta);

        TextArea txtRes = new TextArea(); txtRes.setEditable(false);
        txtRes.setStyle("-fx-font-family: 'Consolas'; -fx-control-inner-background: #2a2a35; -fx-text-fill: #ffffff; -fx-font-size: 12px;");
        txtRes.setPrefHeight(260);

        vbox.getChildren().addAll(lblT3, btnPontuacaoTotal, btnAguardando, txtRes);
        tabConsultas.setContent(vbox);

        tabPane.getTabs().addAll(tabCad, tabConsultas);

        // --- EXECUCAO DE OPERACOES DO BANCO DE DADOS ---

        btnCadastrarCoordenador.setOnAction(e -> {
            try {
                Date dataSQL = Date.valueOf(pickerData_nascimento.getValue());
                Coordenador coordenador = new Coordenador(Integer.parseInt(txtIdCoordenador.getText()), txtNome_coordenador.getText(), dataSQL, txtCpf.getText(), txtSexo.getText(), txtMatricula.getText());
                new CoordenadorDAO().inserir(coordenador);
                new Alert(Alert.AlertType.INFORMATION, "Coordenador salvo com sucesso!").show();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        btnCadastrarDiscente.setOnAction(e -> {
            try {
                Date dataSQL = Date.valueOf(pickerData_ingresso.getValue());
                Date datanascimentoSQL = Date.valueOf(pickerData_nascimentoDiscente.getValue());

                // Mapeamento dos construtores baseados no tipo correto
                Discente discente = new Discente(Integer.parseInt(txtIdDiscente.getText()), txtMatricula_discente.getText(), dataSQL, Integer.parseInt(txtPeriodoLetivo.getText()), txtNome_discente.getText(), datanascimentoSQL, txtCpfDiscente.getText(), txtSexoDiscente.getText(), Integer.parseInt(txtIdCursoFK.getText()));
                new DiscenteDAO().inserir(discente);
                new Alert(Alert.AlertType.INFORMATION, "Discente salvo com sucesso!").show();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        // Consulta Pontuacao acumulada por discente (Funcao de Agregacao SUM)
        btnPontuacaoTotal.setOnAction(e -> {
            String sql = "SELECT d.nome_discente, COALESCE(SUM(a.carga_atividade), 0) AS horas_totais " +
                    "FROM Discente d " +
                    "LEFT JOIN Atividade_complementar a ON d.idDiscente = a.idDiscente " +
                    "GROUP BY d.idDiscente";

            try (Connection conn = ConexaoBanco.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                StringBuilder sb = new StringBuilder("--- RELATÓRIO DO ALUNADO: TOTAL DE HORAS ACUMULADAS ---\n");
                while (rs.next()) {
                    sb.append(String.format("Discente: %-25s | Total Consolidado: %d hrs\n",
                            rs.getString("nome_discente"), rs.getInt("horas_totais")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) { txtRes.setText("Erro na juncao de tabelas: " + ex.getMessage()); }
        });

        // Consulta Atividades que aguardam validacao (Filtro Condicional WHERE)
        btnAguardando.setOnAction(e -> {
            String sql = "SELECT d.nome_discente, s.data_submissao, s.carga_horaria_declarada " +
                    "FROM Solicitacao_acc s " +
                    "INNER JOIN Discente d ON s.idDiscente = d.idDiscente " +
                    "WHERE s.situacao_solicitacao = 'ANALISE'";

            try (Connection conn = ConexaoBanco.obterConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                StringBuilder sb = new StringBuilder("--- AUDITORIA OPERACIONAL: REQUISICOES EM ANALISE ---\n");
                while (rs.next()) {
                    sb.append(String.format("Requerente: %-23s | Submetido em: %s | Horas Exigidas: %d\n",
                            rs.getString("nome_discente"), rs.getDate("data_submissao"), rs.getInt("carga_horaria_declarada")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) {
                txtRes.setText("Erro de selecao: " + ex.getMessage());
            }
        });

        primaryStage.setScene(new Scene(tabPane, -1, 500));
        primaryStage.show();
    }

    private Label criarLabel(String texto, String estilo) {
        Label l = new Label(texto);
        l.setStyle(estilo);
        return l;
    }
}
