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
import org.apache.hadoop.io.IntWritable;
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

public class TrexinHCATest {

  public static class TokenizerMapper 
       extends Mapper<Object, Text, Text, Text>{
    
    

      
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	System.err.println("Made it to Mapper");
   	String[] strings = value.toString().split("\\t");
    	System.err.println("Made it to Mapper with Drools");	
    	Text newKey = new Text(strings[1]);
        context.write( newKey, value);
      
    }
  }
  
  public static class TrexinHCAReducer 
       extends Reducer<Text,Text,Text,Text> {
    private IntWritable result = new IntWritable();
    @Override
    public void reduce(Text key, Iterable<Text> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
   //   System.err.println("Made it to Reducer");
      for (Text val : values) {
          sum ++;
        }
      Text textResult = new Text(Integer.toString(sum));
      context.write(key, textResult);
    }
  }

  public static void main(String[] args) throws Exception {
	  
  
 	try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	     KieSession ksession = kContainer.newKieSession("HelloWorldKS");

            // go !
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            ksession.insert(message);
            ksession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }	

	  System.err.println("Made it to main"); 
	  
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length < 2) {
      System.err.println("Usage: wordcount <in> [<in>...] <out>");
      System.exit(2);
    }
    Job job =   Job.getInstance(conf);
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
  
  public static class Message {

      public static final int HELLO = 0;
      public static final int GOODBYE = 1;

      private String message;

      private int status;

      public String getMessage() {
          return this.message;
      }

      public void setMessage(String message) {
          this.message = message;
      }

      public int getStatus() {
          return this.status;
      }

      public void setStatus(int status) {
          this.status = status;
      }

  }

}
