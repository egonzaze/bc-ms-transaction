package pe.nttdata.bc.transaction.business.model;

import lombok.Getter;

@Getter
public enum TypeMessage {
    CODE_SUCCESSFUL("REP-001", "Completado"),
    CODE_UNSUCCESSFUL("REP-101", "Fallido"),
    ;

    private final String code;
    private final String msj;

    TypeMessage(String code, String msj){
        this.code = code;
        this.msj = msj;
    }
}
