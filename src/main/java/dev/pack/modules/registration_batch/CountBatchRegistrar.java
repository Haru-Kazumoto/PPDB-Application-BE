package dev.pack.modules.registration_batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CountBatchRegistrar {

    private Integer id;
    private String name;
    private Long countStudent;

    public CountBatchRegistrar(Integer id, String name, Long countStudent) {
        this.id = id;
        this.name = name;
        this.countStudent = countStudent;
    }

    public CountBatchRegistrar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(Long countStudent) {
        this.countStudent = countStudent;
    }
}
