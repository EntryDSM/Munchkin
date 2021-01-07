package kr.hs.entrydsm.common.module.info;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleNameInfo {

    private String name;

    public String toString() {
        return name;
    }

}
