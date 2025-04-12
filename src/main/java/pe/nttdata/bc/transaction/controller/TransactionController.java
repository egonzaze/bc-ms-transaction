package pe.nttdata.bc.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.nttdata.bc.transaction.business.DTO.TransactionAllDTO;
import pe.nttdata.bc.transaction.business.model.ProductsWithBalance;
import pe.nttdata.bc.transaction.business.model.TransactionSaleRequest;
import pe.nttdata.bc.transaction.business.model.TransactionRequest;
import pe.nttdata.bc.transaction.business.model.TransactionResponse;
import pe.nttdata.bc.transaction.service.TransactionMealService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionMealService transactionMealService;

    public TransactionController(TransactionMealService transactionMealService) {
        this.transactionMealService = transactionMealService;
    }

    @PostMapping(
            value = "/getBalanceAllProducts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<ProductsWithBalance> getBalanceAllProducts(
            @RequestBody TransactionSaleRequest accountRequest
            ){
        return this.transactionMealService.getBalanceAllProducts(accountRequest);
    }

    @PostMapping(
            value = "/addTransaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Flux<TransactionResponse> addTransaction(
            @RequestBody TransactionRequest transactionRequest
    ){
        return Flux.just(this.transactionMealService.addOperation(transactionRequest));
    }

    @GetMapping(
            value = "/getAllTransaction",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Flux<TransactionAllDTO> getAllTransaction(){
        return transactionMealService.getTransactionAll(null);
    }

}
