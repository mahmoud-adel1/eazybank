package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NoticeResponseDto {

    private Long noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private LocalDate noticeBegDt;

    private LocalDate noticeEndDt;

}
