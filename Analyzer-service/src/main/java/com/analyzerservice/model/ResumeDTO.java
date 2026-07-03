package com.analyzerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {
	private Long id;
    private String candidateName;
    private String content;
}
