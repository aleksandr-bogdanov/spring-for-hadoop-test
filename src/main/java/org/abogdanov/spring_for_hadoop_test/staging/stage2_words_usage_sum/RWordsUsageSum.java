package org.abogdanov.spring_for_hadoop_test.staging.stage2_words_usage_sum;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Sum of all words' usage values.
 *
 * Alexander Bogdanov
 * alexander.bogdanov.ru@gmail.com
 * 10.09.2014
 */
public class RWordsUsageSum extends Reducer<NullWritable, IntWritable, Text, IntWritable> {

	private static final Text KEY 			= new Text("Words overall: ");
	private static final IntWritable VALUE 	= new IntWritable();

	@Override
	protected void reduce(NullWritable key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		VALUE.set(sum);
		context.write(KEY, VALUE);
	}

}
