package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private boolean isPaid;

    private boolean isPrintedArrived;

    private boolean isSubmit;

}
