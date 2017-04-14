package lookedge.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guoxu.wu on 16/6/27.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private Date createDate = new Date();
    @Column
    private Date updateDate = new Date();

    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId()!= null ? this.getId().hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        BaseModel other = (BaseModel) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
