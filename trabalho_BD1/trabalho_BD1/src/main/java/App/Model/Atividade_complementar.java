package App.Model;

public class Atividade_complementar {
    private int idAtividade_complementar;
    private String descricao_Atividade;
    private int carga_horaria;
    private int idSolicitacao_acc; //chave estrangeira
    private int idDiscente; //chave estrangeira
    private int idCategoria_acc; //chave estrangeira

    public Atividade_complementar() {}
    public Atividade_complementar(int idAtividade_complementar, String descricao_Atividade, int carga_horaria, int idSolicitacao_acc, int idDiscente, int idCategoria_acc) {
        this.idAtividade_complementar = idAtividade_complementar;
        this.descricao_Atividade = descricao_Atividade;
        this.carga_horaria = carga_horaria;
        this.idSolicitacao_acc = idSolicitacao_acc;
        this.idDiscente = idDiscente;
        this.idCategoria_acc = idCategoria_acc;
    }
    //Gets
    public int getIdAtividade_complementar() {return idAtividade_complementar;}
    public String getDescricao_Atividade() {return descricao_Atividade;}
    public int getCarga_horaria() {return carga_horaria;}
    public int getIdSolicitacao_acc() {return idSolicitacao_acc;}
    public int getIdDiscente() {return idDiscente;}
    public int getIdCategoria_acc() {return idCategoria_acc;}
    //Sets
    public void setIdAtividade_complementar(int idAtividade_complementar) {this.idAtividade_complementar = idAtividade_complementar;}
    public void setDescricao_Atividade(String descricao_Atividade) {this.descricao_Atividade = descricao_Atividade;}
    public void setCarga_horaria(int carga_horaria) {this.carga_horaria = carga_horaria;}
    public void setIdSolicitacao_acc(int idSolicitacao_acc) {this.idSolicitacao_acc = idSolicitacao_acc;}
    public void setIdDiscente(int idDiscente) {this.idDiscente = idDiscente;}
    public void setIdCategoria_acc(int idCategoria_acc) {this.idCategoria_acc = idCategoria_acc;}
}
