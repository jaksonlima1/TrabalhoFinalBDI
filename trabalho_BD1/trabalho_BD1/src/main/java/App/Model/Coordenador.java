package App.Model;
import java.sql.Date;

public class Coordenador {

    private int idCoordenador;
    private String nome_coordenador;
    private Date data_nascimento;
    private String cpf;
    private String sexo;
    private String matricula;

    public Coordenador() {}
    public Coordenador(int idCoordenador, String nome_coordenador, Date data_nascimento, String cpf, String sexo, String matricula) {
        this.idCoordenador = idCoordenador;
        this.nome_coordenador = nome_coordenador;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.matricula = matricula;
    }
    //Gets
    public int getIdCoordenador() {return idCoordenador;}
    public String getNome_coordenador() {return nome_coordenador;}
    public Date getData_nascimento() {return data_nascimento;}
    public String getCpf() {return cpf;}
    public String getSexo() {return sexo;}
    public String getMatricula() {return matricula;}
    //Sets
    public void setIdCoordenador(int idCoordenador) {this.idCoordenador = idCoordenador;}
    public void setNome_coordenador(String nome_coordenador) {this.nome_coordenador = nome_coordenador;}
    public void setData_nascimento(Date data_nascimento) {this.data_nascimento = data_nascimento;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setMatricula(String matricula) {this.matricula = matricula;}
}
