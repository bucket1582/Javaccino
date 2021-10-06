package com.emotie.api.diary.dto;

import com.emotie.api.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class DiaryReadResponse {
    private final Integer id;
    private final String nickname;
    private final String emotion;
    private final String date;
    private final String content;
    private final Boolean isOpened;

    public DiaryReadResponse(
            Diary diary
    ) {
        this.id = diary.getId();
        this.nickname = diary.getWriter().getNickname();
        this.emotion = diary.getEmotion().getEmotion();
        this.date = diary.getCreatedAt().format(DateTimeFormatter.ISO_DATE);
        this.content = diary.getContent();
        this.isOpened = diary.getIsOpened();
    }

}
