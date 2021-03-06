package com.cuzer.springbatch.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				System.out.println(">> Step 1 executed.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet((contribution, chuckContext) -> {
			System.out.println(">> Step 2 executed.");
			return RepeatStatus.FINISHED;
		}).build();
	}

	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println(">> Step 3 executed.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	// @Bean
	// public Job hellowWorldJob() {
	// return jobBuilderFactory.get("helloWorldJob")
	// .start(step1())
	// .next(step2())
	// .next(step3())
	// .build();
	// }

	// @Bean
	// public Job TransitionJob() {
	// return jobBuilderFactory.get("splitJob")
	// .start(step1())
	// .on("COMPLETED").to(step2())
	//// .from(step2()).on("COMPLETED").to(step3())
	// .from(step2()).on("COMPLETED").stopAndRestart(step3())
	// .from(step3()).end()
	// .build();
	// }

}
