package org.paolino.sb2026.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
}
