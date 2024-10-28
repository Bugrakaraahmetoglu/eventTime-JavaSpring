package com.bugrak.eventTime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String content;
    private LocalDate eventTime;
    private String image;
    private String userName;
    private String cityName;
}
