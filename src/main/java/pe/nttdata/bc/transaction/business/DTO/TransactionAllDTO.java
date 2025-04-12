package pe.nttdata.bc.transaction.business.DTO;

import lombok.Builder;
import lombok.Data;
import pe.nttdata.bc.transaction.business.model.TypeOperation;

@Data
@Builder
public class TransactionAllDTO {
    private String transactionId;
    private String accountId;
    private String productId;
    private TypeOperation typeOperation;
    private Double amount;
    private String description;
}
