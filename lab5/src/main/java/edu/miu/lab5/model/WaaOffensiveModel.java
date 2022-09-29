package edu.miu.lab5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaaOffensiveModel {

    private int UserID;

    private int wordsCount;

    private boolean isUnderBlocked;

    private Date bannedDateTime;
}
