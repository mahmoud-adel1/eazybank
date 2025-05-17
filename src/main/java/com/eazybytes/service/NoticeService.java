package com.eazybytes.service;

import com.eazybytes.dto.response.NoticeResponseDto;
import com.eazybytes.mapper.NoticeMapper;
import com.eazybytes.model.Notice;
import com.eazybytes.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeResponseDto> getValidNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            return NoticeMapper.mapToResponseDtoList(notices);
        } else {
            return Collections.emptyList();
        }
    }
}
