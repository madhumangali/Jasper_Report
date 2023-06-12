package com.jasper_report.config;


import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("step")
@NoArgsConstructor
public class Writer implements ItemWriter<String> {

//    private StepExecution stepExecution;

    static long  min;
    static long max;
    static String pdfName;
    @Autowired
    private JobCompletionListener listener;

    public Writer(long min, long max,String pdfName) {
        this.max=max;
        this.min=min;
        this.pdfName=pdfName;
    }

    @Override
    public void write(List<? extends String> list) throws Exception {

    }

//    @Override
//    public void write(List<TreatmentDetailsDto> treatmentDetailsDtoList) throws Exception {
//
////        new ReportGenerationService().generateStaticReport( min, max);
//        System.out.println( max+"-------"+min);
////        System.out.println(
////                listener.getMin()
////        );
//
//    }

//    @Bean
//    @StepScope
//    FlatFileItemWriter transactionFileReader(@Value("#{jobParameters['min']}") String filename) {
//    ...
//    }

}
