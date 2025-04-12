package pe.nttdata.bc.transaction.business.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductsWithBalance {
    private String accountId;
    private String productId;
    private Double amount;
    private TypeProduct typeProduct;
}
