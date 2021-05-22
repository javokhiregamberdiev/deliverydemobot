package uz.javohir.telegram_bot.entities.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isNew(){
        return this.getId() == null;
    }

    @Column(nullable = false)
    private boolean deleted;


    @Column(nullable = false)
    private Date createdDate;


    @Column(nullable = false)
    private Date modifiedDate;



    protected BaseEntity(){
        this.deleted = false;
    }

    @PreUpdate
    public void preUpdate(){
        this.setModifiedDate(new Date(System.currentTimeMillis()));
    }

    @PrePersist
    public void prePersist(){
        if( this.isNew() || this.getCreatedDate() == null){
            this.setCreatedDate(new Date(System.currentTimeMillis()));
        }
        this.setModifiedDate(new Date(System.currentTimeMillis()));
    }



}
