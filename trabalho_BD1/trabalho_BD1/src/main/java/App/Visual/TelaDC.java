package App.Visual;

import App.DAO_DataAccessObject.ConexaoBanco;
import App.DAO_DataAccessObject.Documento_comprovatorioDAO;
import App.DAO_DataAccessObject.CertificadoDAO;
import App.Model.Documento_comprovatorio;
import App.Model.Certificado;

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
import java.sql.Time;
import java.time.LocalTime;

public class TelaDC extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Documentacao e Certificados");

        TabPane tabPane = new TabPane();
        Tab tabCad = new Tab("Lançar Documentos"); tabCad.setClosable(false);
        Tab tabConsultas = new Tab("Painel de Integralizacao"); tabConsultas.setClosable(false);

        // --- PALETA DE CORES DARK ---
        String estiloFundo = "-fx-background-color: #1e1e24;";
        String estiloTitulo = "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3498db; -fx-padding: 5 0 5 0;"; // Azul tecnológico
        String estiloLabel = "-fx-text-fill: #ecf0f1; -fx-font-weight: bold;";
        String estiloTxt = "-fx-background-color: #ffffff; -fx-background-radius: 4px; -fx-padding: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 4px;";
        String estiloBtn = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-min-height: 30px;"; // Verde Sucesso
        String estiloBtnConsulta = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;";

        // ABA 1: FORMULaRIO DE CADASTRO ESTILIZADO
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle(estiloFundo);

        // Campos de Texto - Documento Comprovatorio
        TextField txtIdDocumentoComprovatorio = new TextField();
        TextField txtIdSolicitacaoAccFK = new TextField();
        TextField txtIdDicenteFK = new TextField();
        Button btnSalvarDocumento = new Button("1. Salvar Documento");

        // Campos de Texto - Certificado
        TextField txtIdCertificado = new TextField();
        TextField txtHorasCertificado = new TextField();
        TextField txtDescricaoTarefa = new TextField();
        DatePicker pickerData = new DatePicker();
        TextField txtEmissorCertificado = new TextField();
        TextField txtIdAtividadeComplementarFK = new TextField();
        TextField txtIdCoordenadorFK = new TextField();
        Button btnSalvarCertificado = new Button("2. Salvar Certificado");

        // Aplicacao de estilos em lote nos inputs
        TextField[] todosInputs = {txtIdDocumentoComprovatorio, txtIdSolicitacaoAccFK, txtIdDicenteFK, txtIdCertificado, txtHorasCertificado, txtDescricaoTarefa, txtEmissorCertificado, txtIdAtividadeComplementarFK, txtIdCoordenadorFK};
        for (TextField tf : todosInputs) tf.setStyle(estiloTxt);
        pickerData.setStyle(estiloTxt);
        btnSalvarDocumento.setStyle(estiloBtn);
        btnSalvarCertificado.setStyle(estiloBtn);

        // Montando Secao de Documento
        Label lblT1 = new Label("================ 1. DOCUMENTO ================"); lblT1.setStyle(estiloTitulo);
        grid.add(lblT1, 0, 0, 2, 1);
        grid.add(criarLabel("ID Documento:", estiloLabel), 0, 1); grid.add(txtIdDocumentoComprovatorio, 1, 1);
        grid.add(criarLabel("ID Solicitacao ACC:", estiloLabel), 0, 2); grid.add(txtIdSolicitacaoAccFK, 1, 2);
        grid.add(criarLabel("ID Discente:", estiloLabel), 0, 3); grid.add(txtIdDicenteFK, 1, 3);
        grid.add(btnSalvarDocumento, 1, 4);

        // Montando Secao de Certificado
        Label lblT2 = new Label("================ 2. CERTIFICADO ================"); lblT2.setStyle(estiloTitulo);
        grid.add(lblT2, 0, 5, 2, 1);
        grid.add(criarLabel("ID Certificado:", estiloLabel), 0, 6); grid.add(txtIdCertificado, 1, 6);
        grid.add(criarLabel("Horas Certificado:", estiloLabel), 0, 7); grid.add(txtHorasCertificado, 1, 7);
        grid.add(criarLabel("Descrição Tarefa:", estiloLabel), 0, 8); grid.add(txtDescricaoTarefa, 1, 8);
        grid.add(criarLabel("Data Emissão:", estiloLabel), 0, 9); grid.add(pickerData, 1, 9);
        grid.add(criarLabel("Emissor Certificado:", estiloLabel), 0, 10); grid.add(txtEmissorCertificado, 1, 10);
        grid.add(criarLabel("ID Atividade Comp.:", estiloLabel), 0, 11); grid.add(txtIdAtividadeComplementarFK, 1, 11);
        grid.add(criarLabel("ID Coordenador:", estiloLabel), 0, 12); grid.add(txtIdCoordenadorFK, 1, 12);
        grid.add(btnSalvarCertificado, 1, 13);
        tabCad.setContent(grid);

        // ABA 2: CONSULTAS GERENCIAIS
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(estiloFundo);

        Label lblT3 = new Label("MONITORAMENTO DE REGULAMENTO E AUDITORIA"); lblT3.setStyle(estiloTitulo);

        TextField txtFiltroAluno = new TextField(); txtFiltroAluno.setPromptText("Digite o ID do Aluno para buscar seus certificados");
        txtFiltroAluno.setStyle(estiloTxt);

        Button btnCertAlu = new Button("Buscar Certificados do Aluno"); btnCertAlu.setStyle(estiloBtnConsulta);
        Button btnStatus = new Button("Verificar Alunos Integralizados vs Pendentes"); btnStatus.setStyle(estiloBtnConsulta);

        Button btnListarCategorias = new Button("Mapear Categorias de ACC Ativas"); btnListarCategorias.setStyle(estiloBtnConsulta);
        Button btnHistoricoBlobs = new Button("Auditoria de Envio de Arquivos (BLOBs)"); btnHistoricoBlobs.setStyle(estiloBtnConsulta);

        TextArea txtRes = new TextArea(); txtRes.setEditable(false);
        txtRes.setStyle("-fx-font-family: 'Consolas'; -fx-control-inner-background: #2a2a35; -fx-text-fill: #ffffff; -fx-font-size: 12px;");
        txtRes.setPrefHeight(200);

        vbox.getChildren().addAll(lblT3, txtFiltroAluno, btnCertAlu, btnStatus, btnListarCategorias, btnHistoricoBlobs, txtRes);
        tabConsultas.setContent(vbox);

        tabPane.getTabs().addAll(tabCad, tabConsultas);

        // --- ACOES DOS BOTOES ---

        btnSalvarDocumento.setOnAction(e -> {
            try {
                byte[] arquivoSimulado = "CONTEUDO_DO_PDF_EM_BYTES".getBytes();
                Documento_comprovatorio documento = new Documento_comprovatorio(
                        Integer.parseInt(txtIdDocumentoComprovatorio.getText()), arquivoSimulado,
                        Time.valueOf(LocalTime.now()), Integer.parseInt(txtIdSolicitacaoAccFK.getText()),
                        Integer.parseInt(txtIdDicenteFK.getText())
                );
                new Documento_comprovatorioDAO().inserir(documento);
                new Alert(Alert.AlertType.INFORMATION, "Documento salvo com sucesso!").showAndWait();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        btnSalvarCertificado.setOnAction(e -> {
            try {
                Date dataSQL = Date.valueOf(pickerData.getValue());
                Certificado certificado = new Certificado(
                        Integer.parseInt(txtIdCertificado.getText()), Integer.parseInt(txtHorasCertificado.getText()),
                        txtDescricaoTarefa.getText(), dataSQL, txtEmissorCertificado.getText(),
                        Integer.parseInt(txtIdAtividadeComplementarFK.getText()), Integer.parseInt(txtIdCoordenadorFK.getText())
                );
                new CertificadoDAO().inserir(certificado);
                new Alert(Alert.AlertType.INFORMATION, "Certificado salvo com sucesso!").showAndWait();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        // Consulta Requisito: Certificados por Aluno especifico
        btnCertAlu.setOnAction(e -> {
            String sql = "SELECT c.idCertificado, c.emissor_certificado, c.horas_certificado, c.descricao_tarefa " +
                    "FROM Certificado c " +
                    "JOIN Atividade_complementar a ON c.idAtividade_complementar = a.idAtividade_complementar " +
                    "WHERE a.idDiscente = ?";
            try (Connection conn = ConexaoBanco.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(txtFiltroAluno.getText()));
                try (ResultSet rs = stmt.executeQuery()) {
                    StringBuilder sb = new StringBuilder("--- HISTORICO DE CERTIFICADOS APRESENTADOS PELO DISCENTE ---\n");
                    while(rs.next()) {
                        sb.append(String.format("ID Cert: %-5d | Emissor: %-15s | Horas: %-3d | Atividade: %s\n",
                                rs.getInt("idCertificado"), rs.getString("emissor_certificado"), rs.getInt("horas_certificado"), rs.getString("descricao_tarefa")));
                    }
                    txtRes.setText(sb.toString());
                }
            } catch (Exception ex) { txtRes.setText("Insira um ID de Aluno valido: " + ex.getMessage()); }
        });

        // Ação Consulta: Alunos que integralizaram vs Alunos Pendentes
        btnStatus.setOnAction(e -> {
            String sql = "SELECT d.nome_discente, COALESCE(SUM(a.carga_atividade), 0) as pontos_acumulados " +
                    "FROM Discente d " +
                    "LEFT JOIN Atividade_complementar a ON d.idDiscente = a.idDiscente " +
                    "GROUP BY d.idDiscente";

            try (Connection conn = ConexaoBanco.obterConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                StringBuilder sb = new StringBuilder("--- MONITORAMENTO DE INTEGRALIZACAO(META: 51 PONTOS) ---\n");

                while(rs.next()) {
                    int pontos = rs.getInt("pontos_acumulados");

                    // APLICACAO DO ART.2
                    String status = (pontos >= 51) ? " INTEGRALIZADO (Meta Concluida)"
                            : "PENDENTE (Faltam " + (51 - pontos) + " pontos)";

                    sb.append(String.format("Aluno: %-25s | Total Valido: %-3d pts | Status: %s\n",
                            rs.getString("nome_discente"), pontos, status));
                }
                txtRes.setText(sb.toString());

            } catch (Exception ex) {
                txtRes.setText("Erro ao processar regras da resolucao: " + ex.getMessage());
            }
        });
        // Listagem de Regras/Categorias Ativas no Regulamento
        btnListarCategorias.setOnAction(e -> {
            String sql = "SELECT idCategoria_acc, codigo_inciso, tipo_calculo, descricao FROM Categoria_acc ORDER BY idCategoria_acc DESC";
            try (Connection conn = ConexaoBanco.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                StringBuilder sb = new StringBuilder("--- MAPEAMENTO REVERSO DO REGULAMENTO DE ACC ---\n");
                while (rs.next()) {
                    sb.append(String.format("ID: %-2d | Inciso: %-10s | Cálculo: %-8s | Regra: %s\n",
                            rs.getInt("idCategoria_acc"), rs.getString("codigo_inciso"), rs.getString("tipo_calculo"), rs.getString("descricao")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) { txtRes.setText("Erro: " + ex.getMessage()); }
        });

        // Histórico bruto de envios de documentos (BLOBs)
        btnHistoricoBlobs.setOnAction(e -> {
            String sql = "SELECT idDocumento_comprovatorio, idSolicitacao_acc, idDiscente, data_upload FROM Documento_comprovatorio";
            try (Connection conn = ConexaoBanco.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                StringBuilder sb = new StringBuilder("--- HISTORICO GERENCIAL DE UPLOADS (AUDITORIA DE BLOBS) ---\n");
                while (rs.next()) {
                    sb.append(String.format("Doc ID: %-4d | Link Solicitação: %-4d | ID Aluno: %-4d | Carregado em: %s\n",
                            rs.getInt("idDocumento_comprovatorio"), rs.getInt("idSolicitacao_acc"), rs.getInt("idDiscente"), rs.getTime("data_upload").toString()));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) { txtRes.setText("Erro ao ler tabela BLOB: " + ex.getMessage()); }
        });

        // Configura as proporções
        primaryStage.setScene(new Scene(tabPane, -1, -1));
        primaryStage.show();
    }

    // Metodo auxiliar pratico para criar os textos informativos com o estilo CSS Dark
    private Label criarLabel(String texto, String estilo) {
        Label l = new Label(texto);
        l.setStyle(estilo);
        return l;
    }
}

