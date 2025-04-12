package pe.nttdata.bc.transaction.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    private String codeOperation;
    private String descOperation;
}
