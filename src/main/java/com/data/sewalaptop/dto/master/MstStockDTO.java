package com.data.sewalaptop.dto.master;
import lombok.*;

@Getter @Setter
public class MstStockDTO {
    private Long stockId;
    private Long brandId;
    private Long vendorId;
    private Integer stockQty;
    private String codeQr;
    private String status;

}
