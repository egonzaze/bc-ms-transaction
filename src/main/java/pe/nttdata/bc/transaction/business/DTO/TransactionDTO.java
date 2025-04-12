package pe.nttdata.bc.transaction.business.DTO;

import lombok.Builder;
import lombok.Data;
import pe.nttdata.bc.transaction.business.model.TypeProduct;
import pe.nttdata.bc.transaction.business.model.TypeOperation;

@Data
@Builder
public class TransactionDTO {
    private String idTransaction;
    private String accountId;
    private String productId;
    private TypeOperation typeOperation;
    private TypeProduct typeProduct;
    private String description;
    private Double amount;
}
