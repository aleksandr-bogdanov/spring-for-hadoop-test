package org.abogdanov.spring_for_hadoop_test.staging.stage2_words_usage_sum;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Calculating the sum of all words' usage values.
 *
 * Alexander Bogdanov
 * alexander.bogdanov.ru@gmail.com
 * 10.09.2014
 */
public class MStg extends Mapper<LongWritable, Text, NullWritable, IntWritable> {

	private static final NullWritable KEY 	= NullWritable.get();
	private static final IntWritable VALUE 	= new IntWritable();

	private static final int STG_WORD_INDEX = 0;
	private static final int STG_WORD_USAGE_INDEX = 1;

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] row = value.toString().split("\t");

		VALUE.set(Integer.parseInt(row[STG_WORD_USAGE_INDEX]));
		context.write(KEY, VALUE);
	}
}
