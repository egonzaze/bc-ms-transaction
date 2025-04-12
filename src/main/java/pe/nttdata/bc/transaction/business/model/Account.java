package pe.nttdata.bc.transaction.business.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Account {
    private String accountId;
    private List<Product> products;
}
