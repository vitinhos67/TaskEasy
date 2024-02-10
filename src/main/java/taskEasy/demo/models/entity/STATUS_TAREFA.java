package taskEasy.demo.models.entity;

public enum STATUS_TAREFA {

    CRIADA("CRIADA"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    REALIZADA("REALIZADA");

    private String status;


    private STATUS_TAREFA(String status) {
        this.setStatus(status);
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}
