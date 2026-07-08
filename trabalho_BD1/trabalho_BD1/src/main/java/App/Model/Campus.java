package App.Model;

public class Campus {
    private int idCampus;
    private String nome;
    private String sigla;
    private int id_endereco; // chave estrangeira

    public Campus() {} ;
    public Campus(int idCampus, String nome, String sigla, int id_endereco) {
        this.idCampus = idCampus;
        this.nome = nome;
        this.sigla = sigla;
        this.id_endereco = id_endereco;
    }

    //gets
    public int getIdCampus() { return idCampus;}
    public String getNome() { return nome;}
    public String getSigla() { return sigla;}

    public int getId_endereco() { return id_endereco;}
    //sets
    public void setId_endereco(int id_endereco) { this.id_endereco = id_endereco;}
    public void setNome(String nome) { this.nome = nome;}
    public void setSigla(String sigla) { this.sigla = sigla;}

}
