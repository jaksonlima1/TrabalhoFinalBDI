package App.Model;
import java.sql.Time;

public class Documento_comprovatorio {

    private int idDocumento_comprovatorio;
    private byte[] caminho_arquivo;
    private Time data_upload;
    private int idSolicitacao_acc;//chave estrangeira
    private int idDiscente;//chave estrangeira

    public Documento_comprovatorio() {}
    public Documento_comprovatorio(int idDocumento_comprovatorio, byte[] caminho_arquivo, Time data_upload, int idSolicitacao_acc, int idDiscente) {
        this.idDocumento_comprovatorio = idDocumento_comprovatorio;
        this.caminho_arquivo = caminho_arquivo;
        this.data_upload = data_upload;
        this.idSolicitacao_acc = idSolicitacao_acc;
        this.idDiscente = idDiscente;
    }
    //Gets
    public int getIdDocumento_comprovatorio() {return idDocumento_comprovatorio;}
    public byte[] getCaminho_arquivo() {return caminho_arquivo;}
    public Time getData_upload() {return data_upload;}
    public int getIdSolicitacao_acc() {return idSolicitacao_acc;}
    public int getIdDiscente() {return idDiscente;}
    //Sets
    public void setIdDocumento_comprovatorio(int idDocumento_comprovatorio) {this.idDocumento_comprovatorio = idDocumento_comprovatorio;}
    public void setCaminho_arquivo(byte[] caminho_arquivo) {this.caminho_arquivo = caminho_arquivo;}
    public void setData_upload(Time data_upload) {this.data_upload = data_upload;}
    public void setIdSolicitacao_acc(int idSolicitacao_acc) {this.idSolicitacao_acc = idSolicitacao_acc;}
    public void setIdDiscente(int idDiscente) {this.idDiscente = idDiscente;}
}
