package dev.pack.modules.action;

import dev.pack.modules.authorization.Role;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class ActionDto {
    private String path;
    private String method;
    private Role roleAccess;
    private String descriptionAction;
    private Boolean isBug;
    private String bugDescription;
}
