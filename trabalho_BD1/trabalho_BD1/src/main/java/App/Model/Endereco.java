package App.Model;

public class Endereco {

    private int idEndereco;
    private String logadouro;
    private String numero;
    private String cidade;
    private String estado;
    private String bairro;
    private String cep;

    public Endereco() {}
    public Endereco(int idEndereco, String logadouro, String cidade, String numero, String estado, String bairro, String cep) {
        this.idEndereco = idEndereco;
        this.logadouro = logadouro;
        this.cidade = cidade;
        this.numero = numero;
        this.estado = estado;
        this.bairro = bairro;
        this.cep = cep;
    }
    //Gets
    public int getIdEndereco() {return idEndereco;}
    public String getLogadouro() {return logadouro;}
    public String getNumero() {return numero;}
    public String getCidade() {return cidade;}
    public String getEstado() {return estado;}
    public String getBairro() {return bairro;}
    public String getCep() {return cep;}
    //Sets
    public void setIdEndereco(int idEndereco) {this.idEndereco = idEndereco;}
    public void setLogadouro(String logadouro) {this.logadouro = logadouro;}
    public void setNumero(String numero) {this.numero = numero;}
    public void setCidade(String cidade) {this.cidade = cidade;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setBairro(String bairro) {this.bairro = bairro;}
    public void setCep(String cep) {this.cep = cep;}
}
