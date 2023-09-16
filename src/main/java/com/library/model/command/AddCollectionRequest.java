package com.library.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddCollectionRequest {

    @JsonIgnore
    private String collectionId;

    @NotBlank(message = "title must be provided")
    private String title;

    @NotNull(message = "type must be provided")
    @Schema(example = "BOOK,JOURNAL")
    private String type;

}
