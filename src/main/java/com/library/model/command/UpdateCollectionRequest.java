package com.library.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class UpdateCollectionRequest {

    @JsonIgnore
    private String collectionId;

    @NotBlank
    private String title;

    @NotBlank
    @Schema(example = "BOOK,JOURNAL")
    private String type;

    @Schema(example = "AVAILABLE,NOT_AVAILABLE")
    private String status = "AVAILABLE";
}
