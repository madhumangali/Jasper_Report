package com.jasper_report.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.itextpdf.text.DocumentException;
import com.jasper_report.dto.dummy.KrProductionReportDto;
import com.jasper_report.dto.dummy.TreatmentDetailsDto;
import com.jasper_report.model.Employee;
import com.jasper_report.model.dummy.KrProductionReport;
import com.jasper_report.serviceImpl.GitServiceImpl;
import com.jasper_report.serviceImpl.ReportGenerationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import net.sf.jasperreports.engine.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.*;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpringBatchConfig {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ExecutionContext executionContext;


//    long max;
//
//    long min;

    @Bean
    public Job processJob() throws Exception {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();

    }

    @Autowired
    public DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/springbatch");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcCursorItemReader<TreatmentDetailsDto> reader(){
        JdbcCursorItemReader<TreatmentDetailsDto> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id,name FROM user");
        reader.setRowMapper(new UserRowMapper());

        return reader;
    }

    @Bean
    public Processor processor(){
        return new Processor();
    }

//    @Bean
//    public UserItemProcessor processor(){
//        return new UserItemProcessor();
//    }



    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<TreatmentDetailsDto, TreatmentDetailsDto> chunk(10)
                .reader(reader())
                . processor(processor())
                .writer(writer())
//                .taskExecutor(new SimpleAsyncTaskExecutor())
//                .throttleLimit(20)
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

    @Bean
    public FlatFileItemWriter<TreatmentDetailsDto> writer(){


        FlatFileItemWriter<TreatmentDetailsDto> writer = new FlatFileItemWriter<TreatmentDetailsDto>();

        writer.setResource(new ClassPathResource("users.csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<TreatmentDetailsDto>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<TreatmentDetailsDto>() {{
                setNames(new String[] { "id", "name" });
            }});
        }});

        return writer;
    }


//    @Bean
//    public JobLauncher jobLauncher() throws Exception {
//        SimpleJobLauncher jobLauncher=new SimpleJobLauncher();
//        jobLauncher.setTaskExecutor(taskExecutor());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }


//    @Bean
//    public SimpleAsyncTaskExecutor taskExecutor(){
//        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor=new SimpleAsyncTaskExecutor();
//        simpleAsyncTaskExecutor.setConcurrencyLimit(1);
//        simpleAsyncTaskExecutor.setThreadPriority(0);
//        simpleAsyncTaskExecutor.setThreadNamePrefix("Madhu");
//        return simpleAsyncTaskExecutor;
//    }

//    @BeforeStep
//    public void beforeStep(final StepExecution stepExecution) {
//        JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
//        //use your parameters
//        min=parameters.getLong("min");
//        max=parameters.getLong("max");
//
//        System.out.println(min);
//        System.out.println(max);
//
//    }

}

