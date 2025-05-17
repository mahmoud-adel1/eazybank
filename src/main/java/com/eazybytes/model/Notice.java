package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "notice_details")
@Getter
@Setter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notice_beg_dt")
    private LocalDate noticeBegDt;

    @Column(name = "notice_end_dt")
    private LocalDate noticeEndDt;

    @Column(name = "create_dt")
    private LocalDate createDt;

    @Column(name = "update_dt")
    private LocalDate updateDt;

}