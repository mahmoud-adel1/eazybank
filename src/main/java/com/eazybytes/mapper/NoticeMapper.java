package com.eazybytes.mapper;

import com.eazybytes.dto.response.NoticeResponseDto;
import com.eazybytes.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeMapper {
    public static NoticeResponseDto mapToResponseDto(Notice notice) {
        NoticeResponseDto noticeResponseDto = new NoticeResponseDto();
        noticeResponseDto.setNoticeId(notice.getNoticeId());
        noticeResponseDto.setNoticeSummary(notice.getNoticeSummary());
        noticeResponseDto.setNoticeDetails(notice.getNoticeDetails());
        noticeResponseDto.setNoticeBegDt(notice.getNoticeBegDt());
        noticeResponseDto.setNoticeEndDt(notice.getNoticeEndDt());
        return noticeResponseDto;
    }

    public static List<NoticeResponseDto> mapToResponseDtoList(List<Notice> notices) {
        List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
        for (Notice notice : notices) {
            noticeResponseDtoList.add(mapToResponseDto(notice));
        }
        return noticeResponseDtoList;
    }
}
