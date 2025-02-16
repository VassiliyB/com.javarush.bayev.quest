package com.quest.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Answer {
    String text;
    Integer nextQuestionId;
    boolean isWin;
    boolean isLose;
    String resultText;
}
