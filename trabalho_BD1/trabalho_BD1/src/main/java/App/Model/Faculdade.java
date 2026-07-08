package App.Model;

public class Faculdade {
    private int idFaculdade;
    private String nome;
    private int idCampus; // chave estrangeira

    public Faculdade() {}
    public Faculdade(int idFaculdade, String nome, int idCampus) {
        this.idFaculdade = idFaculdade;
        this.nome = nome;
        this.idCampus = idCampus;
    }
    //Gets
    public int getIdFaculdade() {return idFaculdade;}
    public String getNome() {return nome;}
    public int getIdCampus() {return idCampus;}
    //Sets
    public void setIdFaculdade(int idFaculdade) {this.idFaculdade = idFaculdade;}
    public void setNome(String nome) {this.nome = nome;}
    public void setIdCampus(int idCampus) {this.idCampus = idCampus;}
}