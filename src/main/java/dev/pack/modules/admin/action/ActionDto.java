package dev.pack.modules.admin.action;

import dev.pack.modules.admin.enums.Role;
import lombok.Data;

@Data
public class ActionDto {
    private String path;
    private String method;
    private String module;
    private Role roleAccess;
    private String descriptionAction;
    private Boolean isBug;
    private String bugDescription;
}
