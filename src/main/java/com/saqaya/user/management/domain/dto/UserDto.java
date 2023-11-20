package com.saqaya.user.management.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.saqaya.user.management.common.config.annotation.ConditionalSerialization;
import com.saqaya.user.management.domain.entities.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 8087869099383637923L;
    private static final String SECRET_SALTED_KEY = "450d0b0db2bcf4adde5032eca1a7c416e560cf44";

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String id;
    private String firstName;
    private String lastName;
    @Email
    @ConditionalSerialization(using = UserSerializer.class)
    private String email;
    private boolean marketingConsent;
    protected LocalDateTime createdDate;
    protected LocalDateTime lastModifiedDate;

    public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
        if (!marketingConsent)
            this.email = null;
    }

    public void idGenerator() {
        String saltedEmail = email + SECRET_SALTED_KEY;
        this.setId(DigestUtils.sha1Hex(saltedEmail));
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

class UserSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof User user && user.isMarketingConsent()) {
            // Serialize the email property only if marketingConsent is true
            gen.writeObjectField("email", user.getEmail());
        }
    }
}