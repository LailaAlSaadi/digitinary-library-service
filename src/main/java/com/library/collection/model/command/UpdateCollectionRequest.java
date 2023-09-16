package com.library.collection.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

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