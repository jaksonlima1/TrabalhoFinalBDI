package App.Model;

import java.sql.Date;

public class Certificado {

    private int idCertificado;
    private int horas_certificado;
    private String descricao_tarefa;
    private Date data;
    private String emissor_certificado;
    private int idAtividade_complementar; //chave estrangeira
    private int Coordenador_idCoordenador; //chave estrangeira

    public Certificado() {}
    public Certificado(int idEndereco, int horas_certificado,String descricao_tarefa, Date data, String emissor_certificado, int idAtividade_complementar, int coordenador_idCoordenador) {
        this.idCertificado = idEndereco;
        this.horas_certificado = horas_certificado;
        this.descricao_tarefa = descricao_tarefa;
        this.data = data;
        this.emissor_certificado = emissor_certificado;
        this.idAtividade_complementar = idAtividade_complementar;
        this.Coordenador_idCoordenador = coordenador_idCoordenador;
    }
    //Gets
    public int getIdCertificado() {return idCertificado;}
    public int getHoras_certificado() {return horas_certificado;}
    public String getDescricao_tarefa() {return descricao_tarefa;}
    public Date getData() {return data;}
    public String getEmissor_certificado() {return emissor_certificado;}
    public int getCoordenador_idCoordenador() {return Coordenador_idCoordenador;}
    public int getIdAtividade_complementar() {return idAtividade_complementar;}
    //Sets
    public void setIdCertificado(int idCertificado) {this.idCertificado = idCertificado;}
    public void setHoras_certificado(int horas_certificado) {this.horas_certificado = horas_certificado;}
    public void setDescricao_tarefa(String descricao_tarefa) {this.descricao_tarefa = descricao_tarefa;}
    public void setData(Date data) {this.data = data;}
    public void setEmissor_certificado(String emissor_certificado) {this.emissor_certificado = emissor_certificado;}
    public void setIdAtividade_complementar(int idAtividade_complementar) {this.idAtividade_complementar = idAtividade_complementar;}
    public void setCoordenador_idCoordenador(int coordenador_idCoordenador) {Coordenador_idCoordenador = coordenador_idCoordenador;}
}
