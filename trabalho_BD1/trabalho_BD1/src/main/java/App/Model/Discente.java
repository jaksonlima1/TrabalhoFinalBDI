package App.Model;
import java.sql.Date;

public class Discente {
    private int idDiscente;
    private String matricula_discente;
    private Date data_ingresso;
    private int periodo_letivo;
    private String nome_discente;
    private Date data_nascimento;
    private String cpf;
    private String sexo;
    private int idCurso; //chave estrangeira

    public Discente() {}
    public Discente(int idDiscente, String matricula_discente, Date data_ingresso, int periodo_letivo,
                    String nome_discente, Date data_nascimento, String cpf, String sexo, int idCurso) {
        this.idDiscente = idDiscente;
        this.matricula_discente = matricula_discente;
        this.data_ingresso = data_ingresso;
        this.periodo_letivo = periodo_letivo;
        this.nome_discente = nome_discente;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.idCurso = idCurso;
    }
    //Gets
    public int getIdDiscente() {return idDiscente;}
    public String getMatricula_discente() {return matricula_discente;}
    public Date getData_ingresso() {return data_ingresso;}
    public int getPeriodo_letivo() {return periodo_letivo;}
    public String getNome_discente() {return nome_discente;}
    public Date getData_nascimento() {return data_nascimento;}
    public String getCpf() {return cpf;}
    public String getSexo() {return sexo;}
    public int getIdCurso() {return idCurso;}
    //Sets
    public void setIdDiscente(int idDiscente) {this.idDiscente = idDiscente;}
    public void setMatricula_discente(String matricula_discente) {this.matricula_discente = matricula_discente;}
    public void setData_ingresso(Date data_ingresso) {this.data_ingresso = data_ingresso;}
    public void setPeriodo_letivo(int periodo_letivo) {this.periodo_letivo = periodo_letivo;}
    public void setNome_discente(String nome_discente) {this.nome_discente = nome_discente;}
    public void setData_nascimento(Date data_nascimento) {this.data_nascimento = data_nascimento;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setIdCurso(int idCurso) {this.idCurso = idCurso;}
}
