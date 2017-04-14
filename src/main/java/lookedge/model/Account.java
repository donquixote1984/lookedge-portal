package lookedge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by wgx on 16/9/26.
 */
@Entity
@Getter
@Setter
public class Account  extends BaseModel implements java.io.Serializable  {

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private String role = "ROLE_USER";

    public Account(){}
    public Account(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
