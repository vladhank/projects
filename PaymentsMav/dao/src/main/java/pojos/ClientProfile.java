package pojos;

import enums.ClientProfileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name="Client_Profile")
public class ClientProfile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = ClientProfileType.USER.getType();

    public ClientProfile(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PersonProfile [id=" + id + ",  type=" + type	+ "]";
    }
}
