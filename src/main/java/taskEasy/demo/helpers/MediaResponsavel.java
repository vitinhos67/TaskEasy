package taskEasy.demo.helpers;


public class MediaResponsavel {

    Long media;
    Long totalHoras;
    String nome;
    String email;

    public MediaResponsavel(Long media, Long totalHoras, String nome, String email) {
        this.media = media;
        this.nome = nome;
        this.email = email;
        this.totalHoras = totalHoras;
    }

    public String getNome() {
        return nome;
    }

    public Long getMedia() {
        return media;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMedia(Long media) {
        this.media = media;
    }

    public long getTotalHoras() {
        return totalHoras;
    }


}
