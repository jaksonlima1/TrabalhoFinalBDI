package App.Model;

public class Categoria_acc {

    private int idCategoria_acc;
    private String codigo_inciso;
    private String descricao;
    private String tipo_calculo;

    public Categoria_acc() {}
    public Categoria_acc(int idCategoria_acc, String codigo_inciso, String descricao, String tipo_calculo) {
        this.idCategoria_acc = idCategoria_acc;
        this.codigo_inciso = codigo_inciso;
        this.descricao = descricao;
        this.tipo_calculo = tipo_calculo;
    }
    //Gets
    public int getIdCategoria_acc() {return idCategoria_acc;}
    public String getCodigo_inciso() {return codigo_inciso;}
    public String getDescricao() {return descricao;}
    public String getTipo_calculo() {return tipo_calculo;}
    //Sets
    public void setIdCategoria_acc(int idCategoria_acc) {this.idCategoria_acc = idCategoria_acc;}
    public void setCodigo_inciso(String codigo_inciso) {this.codigo_inciso = codigo_inciso;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public void setTipo_calculo(String tipo_calculo) {this.tipo_calculo = tipo_calculo;}
}
