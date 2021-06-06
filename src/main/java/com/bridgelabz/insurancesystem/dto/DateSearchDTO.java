package com.bridgelabz.insurancesystem.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

public @Data class DateSearchDTO {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
