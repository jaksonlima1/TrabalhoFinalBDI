package App.Visual;

import App.DAO_DataAccessObject.ConexaoBanco;
import App.DAO_DataAccessObject.Categoria_accDAO;
import App.DAO_DataAccessObject.SolicitacaoAccDAO;
import App.DAO_DataAccessObject.Atividade_complementarDAO;
import App.Model.Categoria_acc;
import App.Model.SolicitacaoACC;
import App.Model.Atividade_complementar;

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

public class TelaCSA extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Módulo de Processos e Validação (CSA)");

        // Divisão por abas para organizar o sistema
        TabPane tabPane = new TabPane();
        Tab tabCad = new Tab("Lançar Processos"); tabCad.setClosable(false);
        Tab tabConsultas = new Tab("Estatísticas de ACC"); tabConsultas.setClosable(false);

        // PALETA DE CORES DARK
        String estiloFundo = "-fx-background-color: #1e1e24;";
        String estiloTitulo = "-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #3498db; -fx-padding: 5 0 5 0;"; // Azul tecnológico
        String estiloLabel = "-fx-text-fill: #ecf0f1; -fx-font-weight: bold;";
        String estiloTxt = "-fx-background-color: #ffffff; -fx-background-radius: 4px; -fx-padding: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 4px;";
        String estiloBtn = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-min-height: 28px;"; // Verde Sucesso
        String estiloBtnConsulta = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand;";

        // ABA 1: FORMULÁRIO DE CADASTRO DISTRIBUÍDO
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle(estiloFundo);

        // Componentes - Categoria acc
        TextField txtIdCategoria = new TextField();
        TextField txtCodigo_inciso = new TextField();
        TextField txtDescricao = new TextField();
        TextField txtTipo_calculo = new TextField();
        Button btnSalvarCategoria = new Button("Salvar Categoria");

        // Componentes - Solicitacao acc
        TextField txtIdSolicitacao_acc = new TextField();
        DatePicker pickerData_Submissao = new DatePicker();
        TextField txtSituacao_Solicitacao = new TextField();
        TextField txtCargaHoraria_Declarada = new TextField();
        TextField txtNa_area = new TextField();
        TextField txtIdDiscenteFK = new TextField();
        Button btnSalvarSolicitacao = new Button("Salvar Solicitação");

        // Componentes - Atividade Complementar
        TextField txtIdAtividade_complementar = new TextField();
        TextField txtDescricao_Atividade = new TextField();
        TextField txtCargaHoraria = new TextField();
        TextField txtIdSolicitacao_accFK = new TextField();
        TextField txtIdDiscente_Atividade_FK = new TextField();
        TextField txtIdCategoria_accFK = new TextField();
        TextField txtQuantidadeMultiplicador = new TextField("1"); // Valor padrão inicial é 1
        ComboBox<String> comboEscopoCurso = new ComboBox<>();
        comboEscopoCurso.setDisable(true); // Deixa o campo cinza (desativado) ao abrir a janela
        comboEscopoCurso.getItems().addAll("Computação (Padrão/Na Área)", "Fora da Área");
        comboEscopoCurso.setValue("Computação (Padrão/Na Área)"); // Valor padrão inicial
        comboEscopoCurso.setStyle(estiloTxt); // Aplica o design arredondado branco
        Button btnSalvarAtividade = new Button("Salvar Atividade");

        // Estilizacao em lote
        TextField[] todosInputs = {txtIdCategoria, txtCodigo_inciso, txtDescricao, txtTipo_calculo, txtIdSolicitacao_acc, txtSituacao_Solicitacao, txtCargaHoraria_Declarada, txtNa_area, txtIdDiscenteFK, txtIdAtividade_complementar, txtDescricao_Atividade, txtCargaHoraria, txtIdSolicitacao_accFK, txtIdDiscente_Atividade_FK, txtIdCategoria_accFK,txtQuantidadeMultiplicador};
        for (TextField tf : todosInputs) tf.setStyle(estiloTxt);
        pickerData_Submissao.setStyle(estiloTxt);
        btnSalvarCategoria.setStyle(estiloBtn);
        btnSalvarSolicitacao.setStyle(estiloBtn);
        btnSalvarAtividade.setStyle(estiloBtn);
        txtQuantidadeMultiplicador.setStyle(estiloTxt);

        // Categorias
        Label lblT1 = new Label("================ 1. CATEGORIA ================"); lblT1.setStyle(estiloTitulo);
        grid.add(lblT1, 0, 0, 2, 1);
        grid.add(criarLabel("ID Categoria:", estiloLabel), 0, 1);       grid.add(txtIdCategoria, 1, 1);
        grid.add(criarLabel("Código Inciso:", estiloLabel), 0, 2);      grid.add(txtCodigo_inciso, 1, 2);
        grid.add(criarLabel("Descrição:", estiloLabel), 0, 3);          grid.add(txtDescricao, 1, 3);
        grid.add(criarLabel("Tipo Cálculo:", estiloLabel), 0, 4);       grid.add(txtTipo_calculo, 1, 4);
        grid.add(btnSalvarCategoria, 1, 5);

        // Solicitacoes
        Label lblT2 = new Label("============= 2. SOLICITAÇÃO ACC ============="); lblT2.setStyle(estiloTitulo);
        grid.add(lblT2, 0, 6, 2, 1);
        grid.add(criarLabel("ID Solicitação:", estiloLabel), 0, 7);     grid.add(txtIdSolicitacao_acc, 1, 7);
        grid.add(criarLabel("Data Submissão:", estiloLabel), 0, 8);     grid.add(pickerData_Submissao, 1, 8);
        grid.add(criarLabel("Situação Solicit.:", estiloLabel), 0, 9);   grid.add(txtSituacao_Solicitacao, 1, 9);
        grid.add(criarLabel("Carga H. Decl.:", estiloLabel), 0, 10);    grid.add(txtCargaHoraria_Declarada, 1, 10);
        grid.add(criarLabel("Área da ACC:", estiloLabel), 0, 11);       grid.add(txtNa_area, 1, 11);
        grid.add(criarLabel("ID Discente (FK):", estiloLabel), 0, 12);  grid.add(txtIdDiscenteFK, 1, 12);
        grid.add(btnSalvarSolicitacao, 1, 13);

        // Atividades
        Label lblT3 = new Label("========== 3. ATIVIDADE COMPLEMENTAR =========="); lblT3.setStyle(estiloTitulo);
        grid.add(lblT3, 2, 0, 2, 1);
        grid.add(criarLabel("ID Atividade:", estiloLabel), 2, 1);       grid.add(txtIdAtividade_complementar, 3, 1);
        grid.add(criarLabel("Descrição Ativ.:", estiloLabel), 2, 2);    grid.add(txtDescricao_Atividade, 3, 2);
        grid.add(criarLabel("Carga Horária:", estiloLabel), 2, 3);      grid.add(txtCargaHoraria, 3, 3);
        grid.add(criarLabel("Verificacao(Inciso XVI):", estiloLabel), 2, 4); grid.add(comboEscopoCurso, 3, 4);
        grid.add(criarLabel("Qtd (Ano,meses ou vezes):", estiloLabel), 2, 5); grid.add(txtQuantidadeMultiplicador, 3, 5);
        grid.add(criarLabel("ID Solicit. (FK):", estiloLabel), 2, 6);   grid.add(txtIdSolicitacao_accFK, 3, 6);
        grid.add(criarLabel("ID Discente (FK):", estiloLabel), 2, 7);   grid.add(txtIdDiscente_Atividade_FK, 3, 7);
        grid.add(criarLabel("ID Categ. (FK):", estiloLabel), 2, 8);     grid.add(txtIdCategoria_accFK, 3, 8);
        grid.add(btnSalvarAtividade, 3, 9);

        tabCad.setContent(grid);

        // ABA 2: CONSULTAS GERENCIAIS
        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(estiloFundo);

        Label lblT4 = new Label("PAINEL DE ENGAJAMENTO E VOLUMETRIA DE ACC"); lblT4.setStyle(estiloTitulo);

        Button btnFrequencia = new Button("Analisar Categorias Mais Frequentes"); btnFrequencia.setStyle(estiloBtnConsulta);
        Button btnVolumeACC = new Button("Analisar Volume Total de Participação"); btnVolumeACC.setStyle(estiloBtnConsulta);

        TextArea txtRes = new TextArea(); txtRes.setEditable(false);
        txtRes.setStyle("-fx-font-family: 'Consolas'; -fx-control-inner-background: #2a2a35; -fx-text-fill: #ffffff; -fx-font-size: 12px;");
        txtRes.setPrefHeight(250);

        vbox.getChildren().addAll(lblT4, btnFrequencia, btnVolumeACC, txtRes);
        tabConsultas.setContent(vbox);

        tabPane.getTabs().addAll(tabCad, tabConsultas);

        // --- INTERCEPTAÇÃO DOS CLIQUES ---

        // OUVINTE DINÂMICO: Ativa o campo de escopo apenas para o Inciso XVI
        txtIdCategoria_accFK.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int id = Integer.parseInt(newValue.trim());
                if (id == 16) {
                    comboEscopoCurso.setDisable(false); // Acende o campo se for o Inciso XVI
                } else {
                    comboEscopoCurso.setDisable(true);  // Apaga/Desativa para qualquer outro inciso
                }
            } catch (NumberFormatException ex) {
                comboEscopoCurso.setDisable(true);      // Mantém desativado se o campo estiver vazio ou com texto
            }
        });

        btnSalvarCategoria.setOnAction(e -> {
            try {
                Categoria_acc categoria_acc = new Categoria_acc(Integer.parseInt(txtIdCategoria.getText()), txtCodigo_inciso.getText(), txtDescricao.getText(), txtTipo_calculo.getText());
                new Categoria_accDAO().inserir(categoria_acc);
                new Alert(Alert.AlertType.INFORMATION, "Categoria de ACC criada!").showAndWait();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        btnSalvarSolicitacao.setOnAction(e -> {
            try {
                Date dateSQL = Date.valueOf(pickerData_Submissao.getValue());
                SolicitacaoACC solicitacao_acc = new SolicitacaoACC(Integer.parseInt(txtIdSolicitacao_acc.getText()), dateSQL, txtSituacao_Solicitacao.getText(), Integer.parseInt(txtCargaHoraria_Declarada.getText()), txtNa_area.getText(), Integer.parseInt(txtIdDiscenteFK.getText()));
                new SolicitacaoAccDAO().inserir(solicitacao_acc);
                new Alert(Alert.AlertType.INFORMATION, "Solicitacao ACC criada!").showAndWait();
            } catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Erro: " + ex.getMessage()).showAndWait(); }
        });

        btnSalvarAtividade.setOnAction(e -> {
            try {
                int idCategoria = Integer.parseInt(txtIdCategoria_accFK.getText());
                int horasBrutas = Integer.parseInt(txtCargaHoraria.getText());
                int qtd = Integer.parseInt(txtQuantidadeMultiplicador.getText());
                int pontosFinaisCalculados = 0;

                // INTERCEPTACAO E APLICAÇAO DAS REGRAS DO REGULAMENTO DA FACSI
                switch (idCategoria) {
                    case 1: // INCISO I: Maratona ou Gincana (1º ou 2º lugar)
                        // Como varia entre 15 e 10, o usuário pode digitar o ponto direto no campo de horas
                        pontosFinaisCalculados = horasBrutas;
                        break;

                    case 2: // INCISO II: Empresa Júnior (5 a 10 pontos)
                        pontosFinaisCalculados = horasBrutas;
                        break;

                    case 3: // INCISO III: Projeto de Extensoo (20 pontos por ano comprovado)
                    case 4: // INCISO IV: Projeto de Pesquisa (20 pontos por ano comprovado)
                        pontosFinaisCalculados = 20 * qtd; // Multiplica pela quantidade de anos digitada
                        break;

                    case 5: // INCISO V: Projeto de Ensino (10 pontos por ano comprovado)
                    case 10: // INCISO X: Grupo de Estudos (10 pontos por ano comprovado)
                        pontosFinaisCalculados = 10 * qtd;
                        break;

                    case 6: // INCISO VI: Monitoria (10 pontos por atividade)
                        pontosFinaisCalculados = 10 * qtd; // Multiplica por quantas monitorias fez
                        break;

                    case 7: // INCISO VII: Curso EaD por IES (5 a 10 pontos)
                        pontosFinaisCalculados = horasBrutas;
                        break;

                    case 8: // INCISO VIII: Concurso Excelencia em TCC (15 ou 10 pontos)
                        pontosFinaisCalculados = horasBrutas;
                        break;

                    case 9: // INCISO IX: Estagio nao obrigatorio (3 pontos por MÊS)
                        pontosFinaisCalculados = 3 * qtd; // Multiplica pela quantidade de meses de estágio
                        break;

                    case 11: // INCISO XI: Aluno Empregado na area (2 pontos por MES)
                        pontosFinaisCalculados = 2 * qtd; // Multiplica pela quantidade de meses trabalhados
                        break;

                    case 12: // INCISO XII: Representacao Discente (3 pontos por SEMESTRE)
                        pontosFinaisCalculados = 3 * qtd; // Multiplica pelos semestres atuados
                        break;

                    case 13: // INCISO XIII: Visitas Tecnicas (5 pontos por VISITA)
                        pontosFinaisCalculados = 5 * qtd; // Multiplica por quantas visitas realizou
                        break;

                    case 14: // INCISO XIV: Palestrante Extracurricular (3 pontos por PALESTRA)
                    case 17: // INCISO XVII: Atividades culturais ou esportivas (2 pontos por EVENTO)
                        int pontosPorEvento = (idCategoria == 14) ? 3 : 2;
                        pontosFinaisCalculados = pontosPorEvento * qtd;
                        break;

                    case 15: // INCISO XV: Ministrante de Minicurso (1/2 das horas)
                        pontosFinaisCalculados = horasBrutas / 2;
                        break;

                    case 16: // INCISO XVI: Ouvinte de Curso (Computacao: 1/2 das horas | Fora: 1/4 das horas)
                        if (comboEscopoCurso.getValue().equals("Fora da Área")) {
                            pontosFinaisCalculados = horasBrutas / 4;
                        } else {
                            pontosFinaisCalculados = horasBrutas / 2;
                        }
                        break;

                    default:
                        // Caso digite um ID fora de 1-17, assume o valor bruto digitado
                        pontosFinaisCalculados = horasBrutas;
                        break;
                }

                // Cria o objeto passando o resultado do cálculo para a coluna do banco
                Atividade_complementar atividade_complementar = new Atividade_complementar(
                        Integer.parseInt(txtIdAtividade_complementar.getText()),
                        txtDescricao_Atividade.getText(),
                        pontosFinaisCalculados, // Grava os pontos calculados pela regra
                        Integer.parseInt(txtIdSolicitacao_accFK.getText()),
                        Integer.parseInt(txtIdDiscente_Atividade_FK.getText()),
                        idCategoria
                );

                new Atividade_complementarDAO().inserir(atividade_complementar);

                new Alert(Alert.AlertType.INFORMATION, "INSERT efetuado! Regra do " + idCategoria + " aplicada. Saldo: " + pontosFinaisCalculados + " pontos.").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro nos parametros de calculo: " + ex.getMessage()).showAndWait();
            }
        });


        // Requisito Enunciado: Quais categorias sao mais frequentes?
        btnFrequencia.setOnAction(e -> {
            String sql = "SELECT c.codigo_inciso, COUNT(a.idAtividade_complementar) AS total_insercoes " +
                    "FROM Categoria_acc c " +
                    "LEFT JOIN Atividade_complementar a ON c.idCategoria_acc = a.idCategoria_acc " +
                    "GROUP BY c.idCategoria_acc " +
                    "ORDER BY total_insercoes DESC";

            try (Connection conn = ConexaoBanco.obterConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                StringBuilder sb = new StringBuilder("--- CATEGORIAS DE ACC MAIS FREQUENTES NO SISTEMA ---\n");
                while (rs.next()) {
                    sb.append(String.format("Codigo Regulamento (Inciso): %-12s | Quantidade Registrada: %d\n",
                            rs.getString("codigo_inciso"), rs.getInt("total_insercoes")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) {
                txtRes.setText("Erro ao computar frequencia: " + ex.getMessage());
            }
        });

        // Requisito Enunciado: Quais tipos de ACC geram maior volume de participação? (Por Horas)
        btnVolumeACC.setOnAction(e -> {
            String sql = "SELECT c.codigo_inciso, COALESCE(SUM(a.carga_atividade), 0) AS total_horas_geradas " +
                    "FROM Categoria_acc c " +
                    "LEFT JOIN Atividade_complementar a ON c.idCategoria_acc = a.idCategoria_acc " +
                    "GROUP BY c.idCategoria_acc " +
                    "ORDER BY total_horas_geradas DESC";

            try (Connection conn = ConexaoBanco.obterConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                StringBuilder sb = new StringBuilder("--- TIPOS DE ACC COM MAIOR VOLUME DE HORAS CONCEDIDAS ---\n");
                while (rs.next()) {
                    sb.append(String.format("Codigo Regulamento (Inciso): %-12s | Volume Acumulado: %-4d hrs\n",
                            rs.getString("codigo_inciso"), rs.getInt("total_horas_geradas")));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) {
                txtRes.setText("Erro ao extrair volumetria: " + ex.getMessage());
            }
        });

        primaryStage.setScene(new Scene(tabPane, -1, -1));
        primaryStage.show();
    }

    private Label criarLabel(String texto, String estilo) {
        Label l = new Label(texto);
        l.setStyle(estilo);
        return l;
    }
}

