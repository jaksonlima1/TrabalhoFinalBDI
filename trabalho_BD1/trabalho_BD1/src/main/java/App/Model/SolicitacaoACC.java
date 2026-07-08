package App.Model;

import java.sql.Date;

public class SolicitacaoACC {
    private int idSolicitacao_acc;
    private Date data_submissao;
    private String situacao_solitacao;
    private int carga_horaria_declarada;
    private String na_area;
    private int idDiscente; //chave estrangeira

    public SolicitacaoACC() {}
    public SolicitacaoACC(int idSolicitacao_acc, Date data_ingresso, String situacao_soliitacao, int carga_horaria_declarada, String na_area, int idDiscente) {
        this.idSolicitacao_acc = idSolicitacao_acc;
        this.data_submissao = data_ingresso;
        this.situacao_solitacao =  situacao_soliitacao;
        this.carga_horaria_declarada = carga_horaria_declarada;
        this.na_area = na_area;
        this.idDiscente = idDiscente;
    }
    //Gets
    public int getIdSolicitacao_acc() {return idSolicitacao_acc;}
    public Date getData_submissao() {return data_submissao;}
    public String getSituacao_solitacao() {return situacao_solitacao;}
    public int getCarga_horaria_declarada() {return carga_horaria_declarada;}
    public String getNa_area() {return na_area;}
    public int getIdDiscente() {return idDiscente;}
    //Sets
    public void setIdSolicitacao_acc(int idSolicitacao_acc) {this.idSolicitacao_acc = idSolicitacao_acc;}
    public void setData_submissao(Date data_ingresso) {this.data_submissao = data_ingresso;}
    public void setSituacao_solitacao(String situacao_soliitacao) {this.situacao_solitacao = situacao_soliitacao;}
    public void setCarga_horaria_declarada(int carga_horaria_declarada) {this.carga_horaria_declarada = carga_horaria_declarada;}
    public void setNa_area(String na_area) {this.na_area = na_area;}
    public void setIdDiscente(int idDiscente) {this.idDiscente = idDiscente;}
}
