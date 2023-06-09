package com.data.sewalaptop.model.master;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_stock", schema = "public")
public class MstStock {
    @Id
    @SequenceGenerator(name = "mst_stock_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_stock_seq")
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "spek_id")
    private Long spekId;

    @Column(name = "qty")
    private Integer qty;
}
