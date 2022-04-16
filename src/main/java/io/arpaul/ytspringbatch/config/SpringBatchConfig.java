package io.arpaul.ytspringbatch.config;

import io.arpaul.ytspringbatch.payload.User;
import io.arpaul.ytspringbatch.payload.UserAddress;
import io.arpaul.ytspringbatch.payload.UserState;
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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Bean
    @Primary
    public Job createJob(JobBuilderFactory jobBuilderFactory,
         StepBuilderFactory stepBuilderFactory,
         ItemReader<User> readerUser, ItemProcessor<User, User> processorUser, ItemWriter<User> writerUser,
         ItemReader<UserAddress> readerUserAddress, ItemProcessor<UserAddress, UserAddress> processorUserAddress, ItemWriter<UserAddress> writerUserAddress,
         ItemProcessor<UserAddress, UserState> processorUserState, ItemWriter<UserState> writerUserState) {


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())//sets up the job id
                .start(getStep1(stepBuilderFactory, readerUser, processorUser, writerUser))
                .next(getStep2(stepBuilderFactory, readerUserAddress, processorUserAddress, writerUserAddress))
                .next(getStep2(stepBuilderFactory, readerUserAddress, processorUserAddress, writerUserAddress))
                .next(getStep3(stepBuilderFactory, readerUserAddress, processorUserState, writerUserState))
                .build();
    }

    private Step getStep1(StepBuilderFactory stepBuilderFactory,
                          ItemReader<User> itemReader,
                          ItemProcessor<User, User> itemProcessor,
                          ItemWriter<User> itemWriter) {
        return stepBuilderFactory.get("ETL-file-load")
                .<User, User> chunk(100)//chunks of data to be accessed each time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    private Step getStep2(StepBuilderFactory stepBuilderFactory,
                          ItemReader<UserAddress> itemReader,
                          ItemProcessor<UserAddress, UserAddress> itemProcessor,
                          ItemWriter<UserAddress> itemWriter) {
        return stepBuilderFactory.get("ETL-file-load")
                .<UserAddress, UserAddress> chunk(100)//chunks of data to be accessed each time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    private Step getStep3(StepBuilderFactory stepBuilderFactory,
                          ItemReader<UserAddress> itemReader,
                          ItemProcessor<UserAddress, UserState> itemProcessor,
                          ItemWriter<UserState> itemWriter) {
        return stepBuilderFactory.get("ETL-file-load")
                .<UserAddress, UserState> chunk(10000)//chunks of data to be accessed each time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
    // User
    @Bean
    public FlatFileItemReader<User> fileItemReaderUser(@Value("${input}") Resource resource) {
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

    // user address
    @Bean
    public FlatFileItemReader<UserAddress> fileItemReaderUserAddress(@Value("${inputUserAddress}") Resource resource) {
        FlatFileItemReader<UserAddress> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);

        //naming the reader
        flatFileItemReader.setName("CSV-Reader");
        //skip this lines if any issue occurs
        //also helps to skip the number of line at the beginning
        flatFileItemReader.setLinesToSkip(1);
        //maps each and every line with the pojo
        flatFileItemReader.setLineMapper(linemapperUserAddress());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<UserAddress> linemapperUserAddress() {
        DefaultLineMapper<UserAddress> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        //delimiter used to differentiate the data
        lineTokenizer.setDelimiter(",");
        //not set to be strict
        lineTokenizer.setStrict(false);
        //setting up column names
        lineTokenizer.setNames(new String[]{"source_id",
                "first_name", "middle_initial", "last_name", "email_address", "phone_number",
                "street", "city", "state", "zip", "birthdate", "action", "ssn"});

        //helps to set each field with each variable in the pojo
        BeanWrapperFieldSetMapper<UserAddress> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserAddress.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
