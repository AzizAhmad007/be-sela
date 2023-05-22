package com.data.sewalaptop.service.master;

import com.data.sewalaptop.common.*;
import com.data.sewalaptop.dto.master.*;
import com.data.sewalaptop.model.master.*;
import com.data.sewalaptop.repository.master.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static com.data.sewalaptop.common.Checker.*;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepo;

    public ResponseEntity<?> saveStock(MstStockDTO requestDTO){
        if (requestDTO.getStockId() == null){
            return createStock(requestDTO);
        } else {
            return updateStock(requestDTO);
        }
    }

    public ResponseEntity<?> deleteStock(Long stockId){
        ResponseDTO response = new ResponseDTO();
        MstStock stock = stockRepo.findByStockId(stockId);
        if (stock == null){
            response.setCode("204");
            response.setMessage("Brand ID not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        stockRepo.delete(stock);

        response.setCode("200");
        response.setData(null);
        response.setMessage("Brand id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public byte[] generateBarcode(String barcodeNumber) {
        // Generate barcode image using ZXing
        ByteArrayOutputStream barcode = QRCode.from(barcodeNumber).withSize(250, 250).stream();

        return barcode.toByteArray();
    }

    private ResponseEntity<?> createStock(MstStockDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        List<MstStock> stockList = stockRepo.findAllByBrandId(requestDTO.getBrandId());
        if (stockList.size() > 0) {
            response.setCode("409");
            response.setMessage("data already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        if (requestDTO.getBrandId() == null) {
            response.setCode("204");
            response.setMessage("Brand Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        if (requestDTO.getStockQty() == null) {
            response.setCode("204");
            response.setMessage("Stock Qty cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        for (int i = 0; i < requestDTO.getStockQty(); i++) {
            MstStock stockEntity = new MstStock();

            stockEntity.setBrandId(requestDTO.getBrandId());
            stockEntity.setCodeQr(UUID.randomUUID().toString());
            stockEntity.setStatus("ACTIVE");

            stockRepo.save(stockEntity);
        }

        response.setCode("201");
        response.setData(null);
        response.setMessage("Brand has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updateStock(MstStockDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstStock stockEntity = new MstStock();
        MstStock stockList = stockRepo.findByStockId(requestDTO.getStockId());
        if (stockList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        if (requestDTO.getStockId() == null){
            stockEntity.setStockId(stockList.getStockId());
        } else {
            stockEntity.setStockId(requestDTO.getStockId());
        }

        if (isNullStr(requestDTO.getCodeQr())){
            stockEntity.setCodeQr(requestDTO.getCodeQr());
        } else {
            stockEntity.setCodeQr(UUID.randomUUID().toString());
        }

        if (isNullStr(requestDTO.getStatus())){
            stockEntity.setStatus(requestDTO.getStatus());
        } else {
            stockEntity.setStatus(stockList.getStatus());
        }

        if (requestDTO.getBrandId() == null){
            stockEntity.setStockId(stockList.getBrandId());
        } else {
            stockEntity.setStockId(requestDTO.getBrandId());
        }

        stockRepo.save(stockEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Brand has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}