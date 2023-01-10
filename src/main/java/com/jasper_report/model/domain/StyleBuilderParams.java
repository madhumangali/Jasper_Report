package com.jasper_report.model.domain;

import com.jasper_report.model.Enum.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "style_builder_params",schema = "jasper_report")
public class StyleBuilderParams {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "style_id")
  private long styleId;

  @Enumerated(value = EnumType.STRING)
  private FontStyles fontStyles;

  @Enumerated(value = EnumType.STRING)
  private BorderStyleEnum borderStyle;

  @Enumerated(value = EnumType.STRING)
  private BorderStyleEnum borderBottom;

  @Enumerated(value = EnumType.STRING)
  private Colors backgroundColor;

  @Enumerated(value = EnumType.STRING)
  private Colors textColor;

  @Enumerated(value = EnumType.STRING)
  private VerticalAlignmentEnum verticalAlignmentEnum;

  @Enumerated(value = EnumType.STRING)
  private HorizontalAlignmentEnum horizontalAlignmentEnum;

  @Enumerated(value = EnumType.STRING)
  private PdfTitle pdfTitle;

  @Enumerated(value = EnumType.STRING)
  private Colors borderColor;

  private String titleName;

  private String subtitleName;

  private String fileName;

  private String reportHeader;

  private String description;

  private String subject;
  
  private Date date;

  private Time time;

}
