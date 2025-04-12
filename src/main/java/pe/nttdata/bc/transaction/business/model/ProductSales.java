package pe.nttdata.bc.transaction.business.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductSales {
    private Double amount;
    private TypeProduct typeProduct;
}
