package pe.nttdata.bc.transaction.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionRequest {
    private TransactionSaleRequest account;
    private Double amount;
    private TypeOperation typeOperation;
}
