package pojo;

import lombok.Data;


@Data
public class SignUpPojo {
    private String email;
    private String password;

    public SignUpPojo() {
    }

    public SignUpPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }
}