package by.belotskiy.movie_star.model.entity;

import by.belotskiy.movie_star.model.entity.enums.Status;

/**
 * Base entity contains id and status fields.
 *
 * @author Dmitriy Belotskiy
 */
public abstract class BaseEntity implements Identified<Integer>{

    private Integer id;
    private Status status;

    public BaseEntity(){}

    public BaseEntity(int id){
        this.id = id;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
