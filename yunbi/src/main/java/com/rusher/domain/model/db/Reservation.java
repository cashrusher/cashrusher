package com.rusher.domain.model.db;

import com.rusher.common.db.PersistenceSupport;
import javax.persistence.*;

@Entity
@Table(
    name = "reservation",
    indexes = {
        @Index(name = "idx_channel", columnList = "channelCode"),
        @Index(name = "idx_hotelCode", columnList = "hotelCode"),
        @Index(name = "idx_channelReservationNo", columnList = "channelReservationNo"),
        @Index(name = "idx_supplierConfirmedNo", columnList = "supplierConfirmedNo")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"ersp"},
            name = "idx_ersp"
        )
    }
)
public class Reservation extends PersistenceSupport {
  @Column(nullable = false)
  private String ersp;

  @Column(nullable = false)
  private String channelCode;

  @Column(nullable = false)
  private String channelReservationNo;

  @Column(nullable = false)
  private String hotelCode;

  @Column
  private String providerCode;

//  @Embedded
//  private ReservationRoomStay roomStay;

//  @Enumerated(EnumType.STRING)
//  @Column(length = 20, nullable = false)
//  private ReservationStatus status = ReservationStatus.DRAFT;

  @Column
  private String supplierConfirmedNo;

  @Column
  private String supplierCancelledNo;

//  @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//  @JoinColumn(name = "id")
//  private ReservationDetail reservationDetail;

  public String getErsp() {
    return ersp;
  }

  public void setErsp(String ersp) {
    this.ersp = ersp;
  }

  public String getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }

  public String getChannelReservationNo() {
    return channelReservationNo;
  }

  public void setChannelReservationNo(String channelReservationNo) {
    this.channelReservationNo = channelReservationNo;
  }

  public String getHotelCode() {
    return hotelCode;
  }

  public void setHotelCode(String hotelCode) {
    this.hotelCode = hotelCode;
  }


  public String getSupplierConfirmedNo() {
    return supplierConfirmedNo;
  }

  public void setSupplierConfirmedNo(String supplierConfirmedNo) {
    this.supplierConfirmedNo = supplierConfirmedNo;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getSupplierCancelledNo() {
    return supplierCancelledNo;
  }

  public void setSupplierCancelledNo(String supplierCancelledNo) {
    this.supplierCancelledNo = supplierCancelledNo;
  }

}
