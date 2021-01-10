package kr.hs.entrydsm.common.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module {

    private String name;

    public String toString() {
        return name;
    }

}
