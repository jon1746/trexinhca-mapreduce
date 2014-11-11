/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trexinhca;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import com.trexinhca.MapperMessage;
import com.trexinhca.ReducerMessage;


import java.util.Iterator;

public class TrexinHCATest {

	public static KieServices ks;
	public static KieContainer kContainer;
	public static KieSession ksession;

	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, Text> {

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

		
			// System.out.println( strings.length );

			// System.out.println("Made it to rules");
			// long startTime = System.nanoTime();
		//	System.out.println(key);
			MapperMessage message = new MapperMessage(value.toString());
			FactHandle fh = TrexinHCATest.ksession.insert(message);
			TrexinHCATest.ksession.fireAllRules();
			TrexinHCATest.ksession.delete(fh);
			// long endTime = System.nanoTime();
			// long duration = (endTime - startTime); 
			// System.out.println(duration)
			Iterator<String> iterator = message.getClassifications().iterator();
			  while (iterator.hasNext()) {
	        	Text newKey=new Text(iterator.next());
	        //	System.out.println(newKey);
	        	context.write(newKey, new Text(message.toString()));
	       
	        }
		

		}
	}

	public static class TrexinHCAReducer extends
			Reducer<Text, Text, Text, Text> {
	
		@Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {		
			Iterator<Text> iterator = values.iterator();
			ReducerMessage reducedMessage = new ReducerMessage(iterator.next().toString());
			  while (iterator.hasNext()) {
				  ReducerMessage consumedMessage = new ReducerMessage(iterator.next().toString());
				  reducedMessage.consume(consumedMessage);
			//System.out.println(val.toString());
			}
			
			Text textResult = new Text(reducedMessage.toString());
			context.write(key, textResult);
		}
	}	

	public static void main(String[] args) throws Exception {

		
		ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
		ksession = TrexinHCATest.kContainer.newKieSession("MapReduceKS");
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length < 2) {
			System.err.println("Usage: TrexinHCATest <in> [<in>...] <out>");
			System.exit(2);
		}
		Job job = Job.getInstance(conf);
		job.setJobName("HCATest");
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(TrexinHCAReducer.class);
		job.setReducerClass(TrexinHCAReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(TextOutputFormat.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		job.setJarByClass(TrexinHCATest.class);
		job.waitForCompletion(true);

	}

}