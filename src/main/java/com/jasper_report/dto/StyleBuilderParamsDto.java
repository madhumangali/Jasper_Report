package com.jasper_report.dto;

import com.jasper_report.model.Enum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StyleBuilderParamsDto {

    private FontStyles fontStyles;

    private BorderStyleEnum borderStyle;

    private BorderStyleEnum borderBottom;

    private Colors backgroundColor;

    private Colors borderColor;

    private Colors textColor;

    private VerticalAlignmentEnum verticalAlignmentEnum;

    private HorizontalAlignmentEnum horizontalAlignmentEnum;

    private PdfTitle pdfTitle;

}
