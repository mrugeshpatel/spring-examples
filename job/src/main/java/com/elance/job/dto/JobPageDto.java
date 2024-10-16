package com.elance.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPageDto {

    private List<JobDto> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean last;
}
