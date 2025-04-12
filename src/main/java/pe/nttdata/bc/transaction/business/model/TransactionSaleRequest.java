package pe.nttdata.bc.transaction.business.model;

import lombok.Data;

@Data
public class TransactionSaleRequest {
    private String accountId;
    private String productId;
}
