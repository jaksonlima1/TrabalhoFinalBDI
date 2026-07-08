package App.Model;

public class Curso {
    private int idCurso;
    private String nome_curso;
    private String nivel_curso;
    private String duracao_curso;
    private int idFaculdade; // chave estrangeira

    public Curso(){};
    public Curso(int idCurso, String nome_curso, String nivel_curso, String duracao_curso, int idFaculdade){
        this.idCurso = idCurso;
        this.nome_curso = nome_curso;
        this.nivel_curso = nivel_curso;
        this.duracao_curso = duracao_curso;
        this.idFaculdade = idFaculdade;
    }
    //Gets
    public int getIdCurso() {return idCurso;}
    public String getNome_curso() {return nome_curso;}
    public String getNivel_curso() {return nivel_curso;}
    public String getDuracao_curso() {return duracao_curso;}
    public int getIdFaculdade() {return idFaculdade;}
    //Sets
    public void setIdCurso(int idCurso) {this.idCurso = idCurso;}
    public void setNome_curso(String nome_curso) {this.nome_curso = nome_curso;}
    public void setNivel_curso(String nivel_curso) {this.nivel_curso = nivel_curso;}
    public void setDuracao_curso(String duracao_curso) {this.duracao_curso = duracao_curso;}
    public void setIdFaculdade(int idFaculdade) {this.idFaculdade = idFaculdade;}
}
