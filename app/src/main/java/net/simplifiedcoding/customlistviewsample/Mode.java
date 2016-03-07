package net.simplifiedcoding.customlistviewsample;

import android.graphics.drawable.Drawable;

/**
 * Created by haneetsingh on 23/02/16.
 */
public class Mode {
    int questionId;

    String question;

    String answerOption;

    AnswerType answerType;

    String answerSelected;

    int fdraw;
    // int QAE = -1;
    // DateTime LastReceive;
    MessageType Type;

    public Mode(String s, MessageType qType, int draw, int qid, String aOption, AnswerType aType, String answeselect) {
        answerSelected = answeselect;

        questionId = qid;

        answerOption = aOption;

        answerType = aType;

        question = s;

        fdraw = draw;

        Type = qType;
        // QAE = qae;

    }


    // Object Content;
}

enum MessageType {
    Text, // normal text message
    Image, // image cover background
    Sticker, // emoticon message
    Audio, // recording message
    result, // video message
}

enum AnswerType {
    Text, // normal text message
    Image, // image cover background
    Sticker, // emoticon message
    Audio, // recording message
    result, // video message
}