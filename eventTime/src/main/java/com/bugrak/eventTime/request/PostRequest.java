package com.bugrak.eventTime.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String content;
    private LocalDate eventTime;
    private String image;
    private Integer userId;
    private Integer cityId;
}
