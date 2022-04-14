package io.arpaul.ytspringbatch.config;

import io.arpaul.ytspringbatch.payload.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Bean
    public Job createJob(JobBuilderFactory jobBuilderFactory,
                         StepBuilderFactory stepBuilderFactory,
                         ItemReader<User> itemReader,
                         ItemProcessor<User, User> itemProcessor,
                         ItemWriter<User> itemWriter) {
        Step step = stepBuilderFactory.get("ETL-file-load")
                .<User, User> chunk(100)//chunks of data to be accessed each time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())//sets up the job id
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<User> fileItemReader(@Value("${input}") Resource resource) {
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);

        //naming the reader
        flatFileItemReader.setName("CSV-Reader");
        //skip this lines if any issue occurs
        //also helps to skip the number of line at the beginning
        flatFileItemReader.setLinesToSkip(1);
        //maps each and every line with the pojo
        flatFileItemReader.setLineMapper(linemapper());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<User> linemapper() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        //delimiter used to differentiate the data
        lineTokenizer.setDelimiter(",");
        //not set to be strict
        lineTokenizer.setStrict(false);
        //setting up column names
        lineTokenizer.setNames(new String[]{"id", "name","dept","salary"});

        //helps to set each field with each variable in the pojo
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
