package com.hansel.FriseurPlan.infra.port.output.entities;

import com.hansel.FriseurPlan.core.domain.email.Email;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailVo {
    private String email;
    private boolean verified;

    public static EmailVo fromEmailDomain(Email email) {
        return new EmailVo(
            email.getEmail(),
            email.isVerified()
        );
    }

    public Email toEmailDomain() {
        return Email.create(this.email, this.verified);
    }
}
