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
   	String[] strings = value.toString().split(",");
    	Text newKey = new Text(strings[1]);
    	
    	
	     
	     if (strings.length > 140 ){
    	try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	     KieSession ksession = kContainer.newKieSession("HelloWorldKS");

            // go !
    
            Message message = new Message(strings);
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            ksession.insert(message);
            ksession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }	};

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
	  
  
 /*	try {
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
*/
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


	public String getLINE_ICD9_DGNS_CD_13() {
		return LINE_ICD9_DGNS_CD_13;
	}

	public void setLINE_ICD9_DGNS_CD_13(String lINE_ICD9_DGNS_CD_13) {
		LINE_ICD9_DGNS_CD_13 = lINE_ICD9_DGNS_CD_13;
	}

	public static final int HELLO = 0;
      public static final int GOODBYE = 1;

      private String message;
      private String DESYNPUF_ID;
      private String CLM_ID;
      private String CLM_FROM_DT;
      private String CLM_THRU_DT;
      private String ICD9_DGNS_CD_1;
      private String ICD9_DGNS_CD_2;
      private String ICD9_DGNS_CD_3;
      private String ICD9_DGNS_CD_4;
      private String ICD9_DGNS_CD_5;
      private String ICD9_DGNS_CD_6;
      private String ICD9_DGNS_CD_7;
      private String ICD9_DGNS_CD_8;
      private String PRF_PHYSN_NPI_1;
      private String PRF_PHYSN_NPI_2;
      private String PRF_PHYSN_NPI_3;
      private String PRF_PHYSN_NPI_4;
      private String PRF_PHYSN_NPI_5;
      private String PRF_PHYSN_NPI_6;
      private String PRF_PHYSN_NPI_7;
      private String PRF_PHYSN_NPI_8;
      private String PRF_PHYSN_NPI_9;
      private String PRF_PHYSN_NPI_10;
      private String PRF_PHYSN_NPI_11;
      private String PRF_PHYSN_NPI_12;
      private String PRF_PHYSN_NPI_13;
      private String TAX_NUM_1;
      private String TAX_NUM_2;
      private String TAX_NUM_3;
      private String TAX_NUM_4;
      private String TAX_NUM_5;
      private String TAX_NUM_6;
      private String TAX_NUM_7;
      private String TAX_NUM_8;
      private String TAX_NUM_9;
      private String TAX_NUM_10;
      private String TAX_NUM_11;
      private String TAX_NUM_12;
      private String TAX_NUM_13;
      private String HCPCS_CD_1;
      private String HCPCS_CD_2;
      private String HCPCS_CD_3;
      private String HCPCS_CD_4;
      private String HCPCS_CD_5;
      private String HCPCS_CD_6;
      private String HCPCS_CD_7;
      private String HCPCS_CD_8;
      private String HCPCS_CD_9;
      private String HCPCS_CD_10;
      private String HCPCS_CD_11;
      private String HCPCS_CD_12;
      private String HCPCS_CD_13;
      private String LINE_NCH_PMT_AMT_1;
      private String LINE_NCH_PMT_AMT_2;
      private String LINE_NCH_PMT_AMT_3;
      private String LINE_NCH_PMT_AMT_4;
      private String LINE_NCH_PMT_AMT_5;
      private String LINE_NCH_PMT_AMT_6;
      private String LINE_NCH_PMT_AMT_7;
      private String LINE_NCH_PMT_AMT_8;
      private String LINE_NCH_PMT_AMT_9;
      private String LINE_NCH_PMT_AMT_10;
      private String LINE_NCH_PMT_AMT_11;
      private String LINE_NCH_PMT_AMT_12;
      private String LINE_NCH_PMT_AMT_13;
      private String LINE_BENE_PTB_DDCTBL_AMT_1;
      private String LINE_BENE_PTB_DDCTBL_AMT_2;
      private String LINE_BENE_PTB_DDCTBL_AMT_3;
      private String LINE_BENE_PTB_DDCTBL_AMT_4;
      private String LINE_BENE_PTB_DDCTBL_AMT_5;
      private String LINE_BENE_PTB_DDCTBL_AMT_6;
      private String LINE_BENE_PTB_DDCTBL_AMT_7;
      private String LINE_BENE_PTB_DDCTBL_AMT_8;
      private String LINE_BENE_PTB_DDCTBL_AMT_9;
      private String LINE_BENE_PTB_DDCTBL_AMT_10;
      private String LINE_BENE_PTB_DDCTBL_AMT_11;
      private String LINE_BENE_PTB_DDCTBL_AMT_12;
      private String LINE_BENE_PTB_DDCTBL_AMT_13;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_1;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_2;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_3;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_4;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_5;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_6;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_7;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_8;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_9;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_10;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_11;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_12;
      private String LINE_BENE_PRMRY_PYR_PD_AMT_13;
      private String LINE_COINSRNC_AMT_1;
      private String LINE_COINSRNC_AMT_2;
      private String LINE_COINSRNC_AMT_3;
      private String LINE_COINSRNC_AMT_4;
      private String LINE_COINSRNC_AMT_5;
      private String LINE_COINSRNC_AMT_6;
      private String LINE_COINSRNC_AMT_7;
      private String LINE_COINSRNC_AMT_8;
      private String LINE_COINSRNC_AMT_9;
      private String LINE_COINSRNC_AMT_10;
      private String LINE_COINSRNC_AMT_11;
      private String LINE_COINSRNC_AMT_12;
      private String LINE_COINSRNC_AMT_13;
      private String LINE_ALOWD_CHRG_AMT_1;
      private String LINE_ALOWD_CHRG_AMT_2;
      private String LINE_ALOWD_CHRG_AMT_3;
      private String LINE_ALOWD_CHRG_AMT_4;
      private String LINE_ALOWD_CHRG_AMT_5;
      private String LINE_ALOWD_CHRG_AMT_6;
      private String LINE_ALOWD_CHRG_AMT_7;
      private String LINE_ALOWD_CHRG_AMT_8;
      private String LINE_ALOWD_CHRG_AMT_9;
      private String LINE_ALOWD_CHRG_AMT_10;
      private String LINE_ALOWD_CHRG_AMT_11;
      private String LINE_ALOWD_CHRG_AMT_12;
      private String LINE_ALOWD_CHRG_AMT_13;
      private String LINE_PRCSG_IND_CD_1;
      private String LINE_PRCSG_IND_CD_2;
      private String LINE_PRCSG_IND_CD_3;
      private String LINE_PRCSG_IND_CD_4;
      private String LINE_PRCSG_IND_CD_5;
      private String LINE_PRCSG_IND_CD_6;
      private String LINE_PRCSG_IND_CD_7;
      private String LINE_PRCSG_IND_CD_8;
      private String LINE_PRCSG_IND_CD_9;
      private String LINE_PRCSG_IND_CD_10;
      private String LINE_PRCSG_IND_CD_11;
      private String LINE_PRCSG_IND_CD_12;
      private String LINE_PRCSG_IND_CD_13;
      private String LINE_ICD9_DGNS_CD_1;
      private String LINE_ICD9_DGNS_CD_2;
      private String LINE_ICD9_DGNS_CD_3;
      private String LINE_ICD9_DGNS_CD_4;
      private String LINE_ICD9_DGNS_CD_5;
      private String LINE_ICD9_DGNS_CD_6;
      private String LINE_ICD9_DGNS_CD_7;
      private String LINE_ICD9_DGNS_CD_8;
      private String LINE_ICD9_DGNS_CD_9;
      private String LINE_ICD9_DGNS_CD_10;
      private String LINE_ICD9_DGNS_CD_11;
      private String LINE_ICD9_DGNS_CD_12;
      private String LINE_ICD9_DGNS_CD_13;
      
      public Message(String[] inputs){
    	  
    	  DESYNPUF_ID=inputs[0];
    	  CLM_ID=inputs[1];
    	  CLM_FROM_DT=inputs[2];
    	  CLM_THRU_DT=inputs[3];
    	  ICD9_DGNS_CD_1=inputs[4];
    	  ICD9_DGNS_CD_2=inputs[5];
    	  ICD9_DGNS_CD_3=inputs[6];
    	  ICD9_DGNS_CD_4=inputs[7];
    	  ICD9_DGNS_CD_5=inputs[8];
    	  ICD9_DGNS_CD_6=inputs[9];
    	  ICD9_DGNS_CD_7=inputs[10];
    	  ICD9_DGNS_CD_8=inputs[11];
    	  PRF_PHYSN_NPI_1=inputs[12];
    	  PRF_PHYSN_NPI_2=inputs[13];
    	  PRF_PHYSN_NPI_3=inputs[14];
    	  PRF_PHYSN_NPI_4=inputs[15];
    	  PRF_PHYSN_NPI_5=inputs[16];
    	  PRF_PHYSN_NPI_6=inputs[17];
    	  PRF_PHYSN_NPI_7=inputs[18];
    	  PRF_PHYSN_NPI_8=inputs[19];
    	  PRF_PHYSN_NPI_9=inputs[20];
    	  PRF_PHYSN_NPI_10=inputs[21];
    	  PRF_PHYSN_NPI_11=inputs[22];
    	  PRF_PHYSN_NPI_12=inputs[23];
    	  PRF_PHYSN_NPI_13=inputs[24];
    	  TAX_NUM_1=inputs[25];
    	  TAX_NUM_2=inputs[26];
    	  TAX_NUM_3=inputs[27];
    	  TAX_NUM_4=inputs[28];
    	  TAX_NUM_5=inputs[29];
    	  TAX_NUM_6=inputs[30];
    	  TAX_NUM_7=inputs[31];
    	  TAX_NUM_8=inputs[32];
    	  TAX_NUM_9=inputs[33];
    	  TAX_NUM_10=inputs[34];
    	  TAX_NUM_11=inputs[35];
    	  TAX_NUM_12=inputs[36];
    	  TAX_NUM_13=inputs[37];
    	  HCPCS_CD_1=inputs[38];
    	  HCPCS_CD_2=inputs[39];
    	  HCPCS_CD_3=inputs[40];
    	  HCPCS_CD_4=inputs[41];
    	  HCPCS_CD_5=inputs[42];
    	  HCPCS_CD_6=inputs[43];
    	  HCPCS_CD_7=inputs[45];
    	  HCPCS_CD_8=inputs[46];
    	  HCPCS_CD_9=inputs[47];
    	  HCPCS_CD_10=inputs[48];
    	  HCPCS_CD_11=inputs[49];
    	  HCPCS_CD_12=inputs[50];
    	  HCPCS_CD_13=inputs[51];
    	  LINE_NCH_PMT_AMT_1=inputs[52];
    	  LINE_NCH_PMT_AMT_2=inputs[53];
    	  LINE_NCH_PMT_AMT_3=inputs[54];
    	  LINE_NCH_PMT_AMT_4=inputs[55];
    	  LINE_NCH_PMT_AMT_5=inputs[56];
    	  LINE_NCH_PMT_AMT_6=inputs[57];
    	  LINE_NCH_PMT_AMT_7=inputs[58];
    	  LINE_NCH_PMT_AMT_8=inputs[59];
    	  LINE_NCH_PMT_AMT_9=inputs[60];
    	  LINE_NCH_PMT_AMT_10=inputs[61];
    	  LINE_NCH_PMT_AMT_11=inputs[62];
    	  LINE_NCH_PMT_AMT_12=inputs[63];
    	  LINE_NCH_PMT_AMT_13=inputs[64];
    	  LINE_BENE_PTB_DDCTBL_AMT_1=inputs[65];
    	  LINE_BENE_PTB_DDCTBL_AMT_2=inputs[66];
    	  LINE_BENE_PTB_DDCTBL_AMT_3=inputs[67];
    	  LINE_BENE_PTB_DDCTBL_AMT_4=inputs[68];
    	  LINE_BENE_PTB_DDCTBL_AMT_5=inputs[69];
    	  LINE_BENE_PTB_DDCTBL_AMT_6=inputs[70];
    	  LINE_BENE_PTB_DDCTBL_AMT_7=inputs[71];
    	  LINE_BENE_PTB_DDCTBL_AMT_8=inputs[72];
    	  LINE_BENE_PTB_DDCTBL_AMT_9=inputs[73];
    	  LINE_BENE_PTB_DDCTBL_AMT_10=inputs[74];
    	  LINE_BENE_PTB_DDCTBL_AMT_11=inputs[75];
    	  LINE_BENE_PTB_DDCTBL_AMT_12=inputs[76];
    	  LINE_BENE_PTB_DDCTBL_AMT_13=inputs[77];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_1=inputs[78];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_2=inputs[79];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_3=inputs[80];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_4=inputs[81];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_5=inputs[82];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_6=inputs[83];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_7=inputs[84];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_8=inputs[85];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_9=inputs[86];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_10=inputs[87];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_11=inputs[88];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_12=inputs[89];
    	  LINE_BENE_PRMRY_PYR_PD_AMT_13=inputs[90];
    	  LINE_COINSRNC_AMT_1=inputs[91];
    	  LINE_COINSRNC_AMT_2=inputs[92];
    	  LINE_COINSRNC_AMT_3=inputs[93];
    	  LINE_COINSRNC_AMT_4=inputs[94];
    	  LINE_COINSRNC_AMT_5=inputs[95];
    	  LINE_COINSRNC_AMT_6=inputs[96];
    	  LINE_COINSRNC_AMT_7=inputs[97];
    	  LINE_COINSRNC_AMT_8=inputs[98];
    	  LINE_COINSRNC_AMT_9=inputs[99];
    	  LINE_COINSRNC_AMT_10=inputs[100];
    	  LINE_COINSRNC_AMT_11=inputs[101];
    	  LINE_COINSRNC_AMT_12=inputs[102];
    	  LINE_COINSRNC_AMT_13=inputs[103];
    	  LINE_ALOWD_CHRG_AMT_1=inputs[104];
    	  LINE_ALOWD_CHRG_AMT_2=inputs[105];
    	  LINE_ALOWD_CHRG_AMT_3=inputs[106];
    	  LINE_ALOWD_CHRG_AMT_4=inputs[107];
    	  LINE_ALOWD_CHRG_AMT_5=inputs[108];
    	  LINE_ALOWD_CHRG_AMT_6=inputs[109];
    	  LINE_ALOWD_CHRG_AMT_7=inputs[110];
    	  LINE_ALOWD_CHRG_AMT_8=inputs[111];
    	  LINE_ALOWD_CHRG_AMT_9=inputs[112];
    	  LINE_ALOWD_CHRG_AMT_10=inputs[113];
    	  LINE_ALOWD_CHRG_AMT_11=inputs[114];
    	  LINE_ALOWD_CHRG_AMT_12=inputs[115];
    	  LINE_ALOWD_CHRG_AMT_13=inputs[116];
    	  LINE_PRCSG_IND_CD_1=inputs[117];
    	  LINE_PRCSG_IND_CD_2=inputs[118];
    	  LINE_PRCSG_IND_CD_3=inputs[119];
    	  LINE_PRCSG_IND_CD_4=inputs[120];
    	  LINE_PRCSG_IND_CD_5=inputs[121];
    	  LINE_PRCSG_IND_CD_6=inputs[122];
    	  LINE_PRCSG_IND_CD_7=inputs[123];
    	  LINE_PRCSG_IND_CD_8=inputs[124];
    	  LINE_PRCSG_IND_CD_9=inputs[125];
    	  LINE_PRCSG_IND_CD_10=inputs[126];
    	  LINE_PRCSG_IND_CD_11=inputs[127];
    	  LINE_PRCSG_IND_CD_12=inputs[128];
    	  LINE_PRCSG_IND_CD_13=inputs[129];
    	  LINE_ICD9_DGNS_CD_1=inputs[130];
    	  LINE_ICD9_DGNS_CD_2=inputs[131];
    	  LINE_ICD9_DGNS_CD_3=inputs[132];
    	  LINE_ICD9_DGNS_CD_4=inputs[133];
    	  LINE_ICD9_DGNS_CD_5=inputs[134];
    	  LINE_ICD9_DGNS_CD_6=inputs[135];
    	  LINE_ICD9_DGNS_CD_7=inputs[136];
    	  LINE_ICD9_DGNS_CD_8=inputs[137];
    	  LINE_ICD9_DGNS_CD_9=inputs[138];
    	  LINE_ICD9_DGNS_CD_10=inputs[139];
    	  LINE_ICD9_DGNS_CD_11=inputs[140];
    	  LINE_ICD9_DGNS_CD_12=inputs[141];
   // 	  LINE_ICD9_DGNS_CD_13=inputs[142];
      
      }
      
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
      public String getDESYNPUF_ID() {
		return DESYNPUF_ID;
	}

	public void setDESYNPUF_ID(String dESYNPUF_ID) {
		DESYNPUF_ID = dESYNPUF_ID;
	}

	public String getCLM_ID() {
		return CLM_ID;
	}

	public void setCLM_ID(String cLM_ID) {
		CLM_ID = cLM_ID;
	}

	public String getCLM_FROM_DT() {
		return CLM_FROM_DT;
	}

	public void setCLM_FROM_DT(String cLM_FROM_DT) {
		CLM_FROM_DT = cLM_FROM_DT;
	}

	public String getCLM_THRU_DT() {
		return CLM_THRU_DT;
	}

	public void setCLM_THRU_DT(String cLM_THRU_DT) {
		CLM_THRU_DT = cLM_THRU_DT;
	}

	public String getICD9_DGNS_CD_1() {
		return ICD9_DGNS_CD_1;
	}

	public void setICD9_DGNS_CD_1(String iCD9_DGNS_CD_1) {
		ICD9_DGNS_CD_1 = iCD9_DGNS_CD_1;
	}

	public String getICD9_DGNS_CD_2() {
		return ICD9_DGNS_CD_2;
	}

	public void setICD9_DGNS_CD_2(String iCD9_DGNS_CD_2) {
		ICD9_DGNS_CD_2 = iCD9_DGNS_CD_2;
	}

	public String getICD9_DGNS_CD_3() {
		return ICD9_DGNS_CD_3;
	}

	public void setICD9_DGNS_CD_3(String iCD9_DGNS_CD_3) {
		ICD9_DGNS_CD_3 = iCD9_DGNS_CD_3;
	}

	public String getICD9_DGNS_CD_4() {
		return ICD9_DGNS_CD_4;
	}

	public void setICD9_DGNS_CD_4(String iCD9_DGNS_CD_4) {
		ICD9_DGNS_CD_4 = iCD9_DGNS_CD_4;
	}

	public String getICD9_DGNS_CD_5() {
		return ICD9_DGNS_CD_5;
	}

	public void setICD9_DGNS_CD_5(String iCD9_DGNS_CD_5) {
		ICD9_DGNS_CD_5 = iCD9_DGNS_CD_5;
	}

	public String getICD9_DGNS_CD_6() {
		return ICD9_DGNS_CD_6;
	}

	public void setICD9_DGNS_CD_6(String iCD9_DGNS_CD_6) {
		ICD9_DGNS_CD_6 = iCD9_DGNS_CD_6;
	}

	public String getICD9_DGNS_CD_7() {
		return ICD9_DGNS_CD_7;
	}

	public void setICD9_DGNS_CD_7(String iCD9_DGNS_CD_7) {
		ICD9_DGNS_CD_7 = iCD9_DGNS_CD_7;
	}

	public String getICD9_DGNS_CD_8() {
		return ICD9_DGNS_CD_8;
	}

	public void setICD9_DGNS_CD_8(String iCD9_DGNS_CD_8) {
		ICD9_DGNS_CD_8 = iCD9_DGNS_CD_8;
	}

	public String getPRF_PHYSN_NPI_1() {
		return PRF_PHYSN_NPI_1;
	}

	public void setPRF_PHYSN_NPI_1(String pRF_PHYSN_NPI_1) {
		PRF_PHYSN_NPI_1 = pRF_PHYSN_NPI_1;
	}

	public String getPRF_PHYSN_NPI_2() {
		return PRF_PHYSN_NPI_2;
	}

	public void setPRF_PHYSN_NPI_2(String pRF_PHYSN_NPI_2) {
		PRF_PHYSN_NPI_2 = pRF_PHYSN_NPI_2;
	}

	public String getPRF_PHYSN_NPI_3() {
		return PRF_PHYSN_NPI_3;
	}

	public void setPRF_PHYSN_NPI_3(String pRF_PHYSN_NPI_3) {
		PRF_PHYSN_NPI_3 = pRF_PHYSN_NPI_3;
	}

	public String getPRF_PHYSN_NPI_4() {
		return PRF_PHYSN_NPI_4;
	}

	public void setPRF_PHYSN_NPI_4(String pRF_PHYSN_NPI_4) {
		PRF_PHYSN_NPI_4 = pRF_PHYSN_NPI_4;
	}

	public String getPRF_PHYSN_NPI_5() {
		return PRF_PHYSN_NPI_5;
	}

	public void setPRF_PHYSN_NPI_5(String pRF_PHYSN_NPI_5) {
		PRF_PHYSN_NPI_5 = pRF_PHYSN_NPI_5;
	}

	public String getPRF_PHYSN_NPI_6() {
		return PRF_PHYSN_NPI_6;
	}

	public void setPRF_PHYSN_NPI_6(String pRF_PHYSN_NPI_6) {
		PRF_PHYSN_NPI_6 = pRF_PHYSN_NPI_6;
	}

	public String getPRF_PHYSN_NPI_7() {
		return PRF_PHYSN_NPI_7;
	}

	public void setPRF_PHYSN_NPI_7(String pRF_PHYSN_NPI_7) {
		PRF_PHYSN_NPI_7 = pRF_PHYSN_NPI_7;
	}

	public String getPRF_PHYSN_NPI_8() {
		return PRF_PHYSN_NPI_8;
	}

	public void setPRF_PHYSN_NPI_8(String pRF_PHYSN_NPI_8) {
		PRF_PHYSN_NPI_8 = pRF_PHYSN_NPI_8;
	}

	public String getPRF_PHYSN_NPI_9() {
		return PRF_PHYSN_NPI_9;
	}

	public void setPRF_PHYSN_NPI_9(String pRF_PHYSN_NPI_9) {
		PRF_PHYSN_NPI_9 = pRF_PHYSN_NPI_9;
	}

	public String getPRF_PHYSN_NPI_10() {
		return PRF_PHYSN_NPI_10;
	}

	public void setPRF_PHYSN_NPI_10(String pRF_PHYSN_NPI_10) {
		PRF_PHYSN_NPI_10 = pRF_PHYSN_NPI_10;
	}

	public String getPRF_PHYSN_NPI_11() {
		return PRF_PHYSN_NPI_11;
	}

	public void setPRF_PHYSN_NPI_11(String pRF_PHYSN_NPI_11) {
		PRF_PHYSN_NPI_11 = pRF_PHYSN_NPI_11;
	}

	public String getPRF_PHYSN_NPI_12() {
		return PRF_PHYSN_NPI_12;
	}

	public void setPRF_PHYSN_NPI_12(String pRF_PHYSN_NPI_12) {
		PRF_PHYSN_NPI_12 = pRF_PHYSN_NPI_12;
	}

	public String getPRF_PHYSN_NPI_13() {
		return PRF_PHYSN_NPI_13;
	}

	public void setPRF_PHYSN_NPI_13(String pRF_PHYSN_NPI_13) {
		PRF_PHYSN_NPI_13 = pRF_PHYSN_NPI_13;
	}

	public String getTAX_NUM_1() {
		return TAX_NUM_1;
	}

	public void setTAX_NUM_1(String tAX_NUM_1) {
		TAX_NUM_1 = tAX_NUM_1;
	}

	public String getTAX_NUM_2() {
		return TAX_NUM_2;
	}

	public void setTAX_NUM_2(String tAX_NUM_2) {
		TAX_NUM_2 = tAX_NUM_2;
	}

	public String getTAX_NUM_3() {
		return TAX_NUM_3;
	}

	public void setTAX_NUM_3(String tAX_NUM_3) {
		TAX_NUM_3 = tAX_NUM_3;
	}

	public String getTAX_NUM_4() {
		return TAX_NUM_4;
	}

	public void setTAX_NUM_4(String tAX_NUM_4) {
		TAX_NUM_4 = tAX_NUM_4;
	}

	public String getTAX_NUM_5() {
		return TAX_NUM_5;
	}

	public void setTAX_NUM_5(String tAX_NUM_5) {
		TAX_NUM_5 = tAX_NUM_5;
	}

	public String getTAX_NUM_6() {
		return TAX_NUM_6;
	}

	public void setTAX_NUM_6(String tAX_NUM_6) {
		TAX_NUM_6 = tAX_NUM_6;
	}

	public String getTAX_NUM_7() {
		return TAX_NUM_7;
	}

	public void setTAX_NUM_7(String tAX_NUM_7) {
		TAX_NUM_7 = tAX_NUM_7;
	}

	public String getTAX_NUM_8() {
		return TAX_NUM_8;
	}

	public void setTAX_NUM_8(String tAX_NUM_8) {
		TAX_NUM_8 = tAX_NUM_8;
	}

	public String getTAX_NUM_9() {
		return TAX_NUM_9;
	}

	public void setTAX_NUM_9(String tAX_NUM_9) {
		TAX_NUM_9 = tAX_NUM_9;
	}

	public String getTAX_NUM_10() {
		return TAX_NUM_10;
	}

	public void setTAX_NUM_10(String tAX_NUM_10) {
		TAX_NUM_10 = tAX_NUM_10;
	}

	public String getTAX_NUM_11() {
		return TAX_NUM_11;
	}

	public void setTAX_NUM_11(String tAX_NUM_11) {
		TAX_NUM_11 = tAX_NUM_11;
	}

	public String getTAX_NUM_12() {
		return TAX_NUM_12;
	}

	public void setTAX_NUM_12(String tAX_NUM_12) {
		TAX_NUM_12 = tAX_NUM_12;
	}

	public String getTAX_NUM_13() {
		return TAX_NUM_13;
	}

	public void setTAX_NUM_13(String tAX_NUM_13) {
		TAX_NUM_13 = tAX_NUM_13;
	}

	public String getHCPCS_CD_1() {
		return HCPCS_CD_1;
	}

	public void setHCPCS_CD_1(String hCPCS_CD_1) {
		HCPCS_CD_1 = hCPCS_CD_1;
	}

	public String getHCPCS_CD_2() {
		return HCPCS_CD_2;
	}

	public void setHCPCS_CD_2(String hCPCS_CD_2) {
		HCPCS_CD_2 = hCPCS_CD_2;
	}

	public String getHCPCS_CD_3() {
		return HCPCS_CD_3;
	}

	public void setHCPCS_CD_3(String hCPCS_CD_3) {
		HCPCS_CD_3 = hCPCS_CD_3;
	}

	public String getHCPCS_CD_4() {
		return HCPCS_CD_4;
	}

	public void setHCPCS_CD_4(String hCPCS_CD_4) {
		HCPCS_CD_4 = hCPCS_CD_4;
	}

	public String getHCPCS_CD_5() {
		return HCPCS_CD_5;
	}

	public void setHCPCS_CD_5(String hCPCS_CD_5) {
		HCPCS_CD_5 = hCPCS_CD_5;
	}

	public String getHCPCS_CD_6() {
		return HCPCS_CD_6;
	}

	public void setHCPCS_CD_6(String hCPCS_CD_6) {
		HCPCS_CD_6 = hCPCS_CD_6;
	}

	public String getHCPCS_CD_7() {
		return HCPCS_CD_7;
	}

	public void setHCPCS_CD_7(String hCPCS_CD_7) {
		HCPCS_CD_7 = hCPCS_CD_7;
	}

	public String getHCPCS_CD_8() {
		return HCPCS_CD_8;
	}

	public void setHCPCS_CD_8(String hCPCS_CD_8) {
		HCPCS_CD_8 = hCPCS_CD_8;
	}

	public String getHCPCS_CD_9() {
		return HCPCS_CD_9;
	}

	public void setHCPCS_CD_9(String hCPCS_CD_9) {
		HCPCS_CD_9 = hCPCS_CD_9;
	}

	public String getHCPCS_CD_10() {
		return HCPCS_CD_10;
	}

	public void setHCPCS_CD_10(String hCPCS_CD_10) {
		HCPCS_CD_10 = hCPCS_CD_10;
	}

	public String getHCPCS_CD_11() {
		return HCPCS_CD_11;
	}

	public void setHCPCS_CD_11(String hCPCS_CD_11) {
		HCPCS_CD_11 = hCPCS_CD_11;
	}

	public String getHCPCS_CD_12() {
		return HCPCS_CD_12;
	}

	public void setHCPCS_CD_12(String hCPCS_CD_12) {
		HCPCS_CD_12 = hCPCS_CD_12;
	}

	public String getHCPCS_CD_13() {
		return HCPCS_CD_13;
	}

	public void setHCPCS_CD_13(String hCPCS_CD_13) {
		HCPCS_CD_13 = hCPCS_CD_13;
	}

	public String getLINE_NCH_PMT_AMT_1() {
		return LINE_NCH_PMT_AMT_1;
	}

	public void setLINE_NCH_PMT_AMT_1(String lINE_NCH_PMT_AMT_1) {
		LINE_NCH_PMT_AMT_1 = lINE_NCH_PMT_AMT_1;
	}

	public String getLINE_NCH_PMT_AMT_2() {
		return LINE_NCH_PMT_AMT_2;
	}

	public void setLINE_NCH_PMT_AMT_2(String lINE_NCH_PMT_AMT_2) {
		LINE_NCH_PMT_AMT_2 = lINE_NCH_PMT_AMT_2;
	}

	public String getLINE_NCH_PMT_AMT_3() {
		return LINE_NCH_PMT_AMT_3;
	}

	public void setLINE_NCH_PMT_AMT_3(String lINE_NCH_PMT_AMT_3) {
		LINE_NCH_PMT_AMT_3 = lINE_NCH_PMT_AMT_3;
	}

	public String getLINE_NCH_PMT_AMT_4() {
		return LINE_NCH_PMT_AMT_4;
	}

	public void setLINE_NCH_PMT_AMT_4(String lINE_NCH_PMT_AMT_4) {
		LINE_NCH_PMT_AMT_4 = lINE_NCH_PMT_AMT_4;
	}

	public String getLINE_NCH_PMT_AMT_5() {
		return LINE_NCH_PMT_AMT_5;
	}

	public void setLINE_NCH_PMT_AMT_5(String lINE_NCH_PMT_AMT_5) {
		LINE_NCH_PMT_AMT_5 = lINE_NCH_PMT_AMT_5;
	}

	public String getLINE_NCH_PMT_AMT_6() {
		return LINE_NCH_PMT_AMT_6;
	}

	public void setLINE_NCH_PMT_AMT_6(String lINE_NCH_PMT_AMT_6) {
		LINE_NCH_PMT_AMT_6 = lINE_NCH_PMT_AMT_6;
	}

	public String getLINE_NCH_PMT_AMT_7() {
		return LINE_NCH_PMT_AMT_7;
	}

	public void setLINE_NCH_PMT_AMT_7(String lINE_NCH_PMT_AMT_7) {
		LINE_NCH_PMT_AMT_7 = lINE_NCH_PMT_AMT_7;
	}

	public String getLINE_NCH_PMT_AMT_8() {
		return LINE_NCH_PMT_AMT_8;
	}

	public void setLINE_NCH_PMT_AMT_8(String lINE_NCH_PMT_AMT_8) {
		LINE_NCH_PMT_AMT_8 = lINE_NCH_PMT_AMT_8;
	}

	public String getLINE_NCH_PMT_AMT_9() {
		return LINE_NCH_PMT_AMT_9;
	}

	public void setLINE_NCH_PMT_AMT_9(String lINE_NCH_PMT_AMT_9) {
		LINE_NCH_PMT_AMT_9 = lINE_NCH_PMT_AMT_9;
	}

	public String getLINE_NCH_PMT_AMT_10() {
		return LINE_NCH_PMT_AMT_10;
	}

	public void setLINE_NCH_PMT_AMT_10(String lINE_NCH_PMT_AMT_10) {
		LINE_NCH_PMT_AMT_10 = lINE_NCH_PMT_AMT_10;
	}

	public String getLINE_NCH_PMT_AMT_11() {
		return LINE_NCH_PMT_AMT_11;
	}

	public void setLINE_NCH_PMT_AMT_11(String lINE_NCH_PMT_AMT_11) {
		LINE_NCH_PMT_AMT_11 = lINE_NCH_PMT_AMT_11;
	}

	public String getLINE_NCH_PMT_AMT_12() {
		return LINE_NCH_PMT_AMT_12;
	}

	public void setLINE_NCH_PMT_AMT_12(String lINE_NCH_PMT_AMT_12) {
		LINE_NCH_PMT_AMT_12 = lINE_NCH_PMT_AMT_12;
	}

	public String getLINE_NCH_PMT_AMT_13() {
		return LINE_NCH_PMT_AMT_13;
	}

	public void setLINE_NCH_PMT_AMT_13(String lINE_NCH_PMT_AMT_13) {
		LINE_NCH_PMT_AMT_13 = lINE_NCH_PMT_AMT_13;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_1() {
		return LINE_BENE_PTB_DDCTBL_AMT_1;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_1(String lINE_BENE_PTB_DDCTBL_AMT_1) {
		LINE_BENE_PTB_DDCTBL_AMT_1 = lINE_BENE_PTB_DDCTBL_AMT_1;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_2() {
		return LINE_BENE_PTB_DDCTBL_AMT_2;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_2(String lINE_BENE_PTB_DDCTBL_AMT_2) {
		LINE_BENE_PTB_DDCTBL_AMT_2 = lINE_BENE_PTB_DDCTBL_AMT_2;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_3() {
		return LINE_BENE_PTB_DDCTBL_AMT_3;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_3(String lINE_BENE_PTB_DDCTBL_AMT_3) {
		LINE_BENE_PTB_DDCTBL_AMT_3 = lINE_BENE_PTB_DDCTBL_AMT_3;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_4() {
		return LINE_BENE_PTB_DDCTBL_AMT_4;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_4(String lINE_BENE_PTB_DDCTBL_AMT_4) {
		LINE_BENE_PTB_DDCTBL_AMT_4 = lINE_BENE_PTB_DDCTBL_AMT_4;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_5() {
		return LINE_BENE_PTB_DDCTBL_AMT_5;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_5(String lINE_BENE_PTB_DDCTBL_AMT_5) {
		LINE_BENE_PTB_DDCTBL_AMT_5 = lINE_BENE_PTB_DDCTBL_AMT_5;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_6() {
		return LINE_BENE_PTB_DDCTBL_AMT_6;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_6(String lINE_BENE_PTB_DDCTBL_AMT_6) {
		LINE_BENE_PTB_DDCTBL_AMT_6 = lINE_BENE_PTB_DDCTBL_AMT_6;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_7() {
		return LINE_BENE_PTB_DDCTBL_AMT_7;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_7(String lINE_BENE_PTB_DDCTBL_AMT_7) {
		LINE_BENE_PTB_DDCTBL_AMT_7 = lINE_BENE_PTB_DDCTBL_AMT_7;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_8() {
		return LINE_BENE_PTB_DDCTBL_AMT_8;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_8(String lINE_BENE_PTB_DDCTBL_AMT_8) {
		LINE_BENE_PTB_DDCTBL_AMT_8 = lINE_BENE_PTB_DDCTBL_AMT_8;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_9() {
		return LINE_BENE_PTB_DDCTBL_AMT_9;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_9(String lINE_BENE_PTB_DDCTBL_AMT_9) {
		LINE_BENE_PTB_DDCTBL_AMT_9 = lINE_BENE_PTB_DDCTBL_AMT_9;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_10() {
		return LINE_BENE_PTB_DDCTBL_AMT_10;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_10(String lINE_BENE_PTB_DDCTBL_AMT_10) {
		LINE_BENE_PTB_DDCTBL_AMT_10 = lINE_BENE_PTB_DDCTBL_AMT_10;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_11() {
		return LINE_BENE_PTB_DDCTBL_AMT_11;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_11(String lINE_BENE_PTB_DDCTBL_AMT_11) {
		LINE_BENE_PTB_DDCTBL_AMT_11 = lINE_BENE_PTB_DDCTBL_AMT_11;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_12() {
		return LINE_BENE_PTB_DDCTBL_AMT_12;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_12(String lINE_BENE_PTB_DDCTBL_AMT_12) {
		LINE_BENE_PTB_DDCTBL_AMT_12 = lINE_BENE_PTB_DDCTBL_AMT_12;
	}

	public String getLINE_BENE_PTB_DDCTBL_AMT_13() {
		return LINE_BENE_PTB_DDCTBL_AMT_13;
	}

	public void setLINE_BENE_PTB_DDCTBL_AMT_13(String lINE_BENE_PTB_DDCTBL_AMT_13) {
		LINE_BENE_PTB_DDCTBL_AMT_13 = lINE_BENE_PTB_DDCTBL_AMT_13;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_1() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_1;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_1(String lINE_BENE_PRMRY_PYR_PD_AMT_1) {
		LINE_BENE_PRMRY_PYR_PD_AMT_1 = lINE_BENE_PRMRY_PYR_PD_AMT_1;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_2() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_2;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_2(String lINE_BENE_PRMRY_PYR_PD_AMT_2) {
		LINE_BENE_PRMRY_PYR_PD_AMT_2 = lINE_BENE_PRMRY_PYR_PD_AMT_2;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_3() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_3;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_3(String lINE_BENE_PRMRY_PYR_PD_AMT_3) {
		LINE_BENE_PRMRY_PYR_PD_AMT_3 = lINE_BENE_PRMRY_PYR_PD_AMT_3;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_4() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_4;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_4(String lINE_BENE_PRMRY_PYR_PD_AMT_4) {
		LINE_BENE_PRMRY_PYR_PD_AMT_4 = lINE_BENE_PRMRY_PYR_PD_AMT_4;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_5() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_5;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_5(String lINE_BENE_PRMRY_PYR_PD_AMT_5) {
		LINE_BENE_PRMRY_PYR_PD_AMT_5 = lINE_BENE_PRMRY_PYR_PD_AMT_5;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_6() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_6;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_6(String lINE_BENE_PRMRY_PYR_PD_AMT_6) {
		LINE_BENE_PRMRY_PYR_PD_AMT_6 = lINE_BENE_PRMRY_PYR_PD_AMT_6;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_7() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_7;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_7(String lINE_BENE_PRMRY_PYR_PD_AMT_7) {
		LINE_BENE_PRMRY_PYR_PD_AMT_7 = lINE_BENE_PRMRY_PYR_PD_AMT_7;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_8() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_8;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_8(String lINE_BENE_PRMRY_PYR_PD_AMT_8) {
		LINE_BENE_PRMRY_PYR_PD_AMT_8 = lINE_BENE_PRMRY_PYR_PD_AMT_8;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_9() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_9;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_9(String lINE_BENE_PRMRY_PYR_PD_AMT_9) {
		LINE_BENE_PRMRY_PYR_PD_AMT_9 = lINE_BENE_PRMRY_PYR_PD_AMT_9;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_10() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_10;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_10(
			String lINE_BENE_PRMRY_PYR_PD_AMT_10) {
		LINE_BENE_PRMRY_PYR_PD_AMT_10 = lINE_BENE_PRMRY_PYR_PD_AMT_10;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_11() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_11;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_11(
			String lINE_BENE_PRMRY_PYR_PD_AMT_11) {
		LINE_BENE_PRMRY_PYR_PD_AMT_11 = lINE_BENE_PRMRY_PYR_PD_AMT_11;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_12() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_12;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_12(
			String lINE_BENE_PRMRY_PYR_PD_AMT_12) {
		LINE_BENE_PRMRY_PYR_PD_AMT_12 = lINE_BENE_PRMRY_PYR_PD_AMT_12;
	}

	public String getLINE_BENE_PRMRY_PYR_PD_AMT_13() {
		return LINE_BENE_PRMRY_PYR_PD_AMT_13;
	}

	public void setLINE_BENE_PRMRY_PYR_PD_AMT_13(
			String lINE_BENE_PRMRY_PYR_PD_AMT_13) {
		LINE_BENE_PRMRY_PYR_PD_AMT_13 = lINE_BENE_PRMRY_PYR_PD_AMT_13;
	}

	public String getLINE_COINSRNC_AMT_1() {
		return LINE_COINSRNC_AMT_1;
	}

	public void setLINE_COINSRNC_AMT_1(String lINE_COINSRNC_AMT_1) {
		LINE_COINSRNC_AMT_1 = lINE_COINSRNC_AMT_1;
	}

	public String getLINE_COINSRNC_AMT_2() {
		return LINE_COINSRNC_AMT_2;
	}

	public void setLINE_COINSRNC_AMT_2(String lINE_COINSRNC_AMT_2) {
		LINE_COINSRNC_AMT_2 = lINE_COINSRNC_AMT_2;
	}

	public String getLINE_COINSRNC_AMT_3() {
		return LINE_COINSRNC_AMT_3;
	}

	public void setLINE_COINSRNC_AMT_3(String lINE_COINSRNC_AMT_3) {
		LINE_COINSRNC_AMT_3 = lINE_COINSRNC_AMT_3;
	}

	public String getLINE_COINSRNC_AMT_4() {
		return LINE_COINSRNC_AMT_4;
	}

	public void setLINE_COINSRNC_AMT_4(String lINE_COINSRNC_AMT_4) {
		LINE_COINSRNC_AMT_4 = lINE_COINSRNC_AMT_4;
	}

	public String getLINE_COINSRNC_AMT_5() {
		return LINE_COINSRNC_AMT_5;
	}

	public void setLINE_COINSRNC_AMT_5(String lINE_COINSRNC_AMT_5) {
		LINE_COINSRNC_AMT_5 = lINE_COINSRNC_AMT_5;
	}

	public String getLINE_COINSRNC_AMT_6() {
		return LINE_COINSRNC_AMT_6;
	}

	public void setLINE_COINSRNC_AMT_6(String lINE_COINSRNC_AMT_6) {
		LINE_COINSRNC_AMT_6 = lINE_COINSRNC_AMT_6;
	}

	public String getLINE_COINSRNC_AMT_7() {
		return LINE_COINSRNC_AMT_7;
	}

	public void setLINE_COINSRNC_AMT_7(String lINE_COINSRNC_AMT_7) {
		LINE_COINSRNC_AMT_7 = lINE_COINSRNC_AMT_7;
	}

	public String getLINE_COINSRNC_AMT_8() {
		return LINE_COINSRNC_AMT_8;
	}

	public void setLINE_COINSRNC_AMT_8(String lINE_COINSRNC_AMT_8) {
		LINE_COINSRNC_AMT_8 = lINE_COINSRNC_AMT_8;
	}

	public String getLINE_COINSRNC_AMT_9() {
		return LINE_COINSRNC_AMT_9;
	}

	public void setLINE_COINSRNC_AMT_9(String lINE_COINSRNC_AMT_9) {
		LINE_COINSRNC_AMT_9 = lINE_COINSRNC_AMT_9;
	}

	public String getLINE_COINSRNC_AMT_10() {
		return LINE_COINSRNC_AMT_10;
	}

	public void setLINE_COINSRNC_AMT_10(String lINE_COINSRNC_AMT_10) {
		LINE_COINSRNC_AMT_10 = lINE_COINSRNC_AMT_10;
	}

	public String getLINE_COINSRNC_AMT_11() {
		return LINE_COINSRNC_AMT_11;
	}

	public void setLINE_COINSRNC_AMT_11(String lINE_COINSRNC_AMT_11) {
		LINE_COINSRNC_AMT_11 = lINE_COINSRNC_AMT_11;
	}

	public String getLINE_COINSRNC_AMT_12() {
		return LINE_COINSRNC_AMT_12;
	}

	public void setLINE_COINSRNC_AMT_12(String lINE_COINSRNC_AMT_12) {
		LINE_COINSRNC_AMT_12 = lINE_COINSRNC_AMT_12;
	}

	public String getLINE_COINSRNC_AMT_13() {
		return LINE_COINSRNC_AMT_13;
	}

	public void setLINE_COINSRNC_AMT_13(String lINE_COINSRNC_AMT_13) {
		LINE_COINSRNC_AMT_13 = lINE_COINSRNC_AMT_13;
	}

	public String getLINE_ALOWD_CHRG_AMT_1() {
		return LINE_ALOWD_CHRG_AMT_1;
	}

	public void setLINE_ALOWD_CHRG_AMT_1(String lINE_ALOWD_CHRG_AMT_1) {
		LINE_ALOWD_CHRG_AMT_1 = lINE_ALOWD_CHRG_AMT_1;
	}

	public String getLINE_ALOWD_CHRG_AMT_2() {
		return LINE_ALOWD_CHRG_AMT_2;
	}

	public void setLINE_ALOWD_CHRG_AMT_2(String lINE_ALOWD_CHRG_AMT_2) {
		LINE_ALOWD_CHRG_AMT_2 = lINE_ALOWD_CHRG_AMT_2;
	}

	public String getLINE_ALOWD_CHRG_AMT_3() {
		return LINE_ALOWD_CHRG_AMT_3;
	}

	public void setLINE_ALOWD_CHRG_AMT_3(String lINE_ALOWD_CHRG_AMT_3) {
		LINE_ALOWD_CHRG_AMT_3 = lINE_ALOWD_CHRG_AMT_3;
	}

	public String getLINE_ALOWD_CHRG_AMT_4() {
		return LINE_ALOWD_CHRG_AMT_4;
	}

	public void setLINE_ALOWD_CHRG_AMT_4(String lINE_ALOWD_CHRG_AMT_4) {
		LINE_ALOWD_CHRG_AMT_4 = lINE_ALOWD_CHRG_AMT_4;
	}

	public String getLINE_ALOWD_CHRG_AMT_5() {
		return LINE_ALOWD_CHRG_AMT_5;
	}

	public void setLINE_ALOWD_CHRG_AMT_5(String lINE_ALOWD_CHRG_AMT_5) {
		LINE_ALOWD_CHRG_AMT_5 = lINE_ALOWD_CHRG_AMT_5;
	}

	public String getLINE_ALOWD_CHRG_AMT_6() {
		return LINE_ALOWD_CHRG_AMT_6;
	}

	public void setLINE_ALOWD_CHRG_AMT_6(String lINE_ALOWD_CHRG_AMT_6) {
		LINE_ALOWD_CHRG_AMT_6 = lINE_ALOWD_CHRG_AMT_6;
	}

	public String getLINE_ALOWD_CHRG_AMT_7() {
		return LINE_ALOWD_CHRG_AMT_7;
	}

	public void setLINE_ALOWD_CHRG_AMT_7(String lINE_ALOWD_CHRG_AMT_7) {
		LINE_ALOWD_CHRG_AMT_7 = lINE_ALOWD_CHRG_AMT_7;
	}

	public String getLINE_ALOWD_CHRG_AMT_8() {
		return LINE_ALOWD_CHRG_AMT_8;
	}

	public void setLINE_ALOWD_CHRG_AMT_8(String lINE_ALOWD_CHRG_AMT_8) {
		LINE_ALOWD_CHRG_AMT_8 = lINE_ALOWD_CHRG_AMT_8;
	}

	public String getLINE_ALOWD_CHRG_AMT_9() {
		return LINE_ALOWD_CHRG_AMT_9;
	}

	public void setLINE_ALOWD_CHRG_AMT_9(String lINE_ALOWD_CHRG_AMT_9) {
		LINE_ALOWD_CHRG_AMT_9 = lINE_ALOWD_CHRG_AMT_9;
	}

	public String getLINE_ALOWD_CHRG_AMT_10() {
		return LINE_ALOWD_CHRG_AMT_10;
	}

	public void setLINE_ALOWD_CHRG_AMT_10(String lINE_ALOWD_CHRG_AMT_10) {
		LINE_ALOWD_CHRG_AMT_10 = lINE_ALOWD_CHRG_AMT_10;
	}

	public String getLINE_ALOWD_CHRG_AMT_11() {
		return LINE_ALOWD_CHRG_AMT_11;
	}

	public void setLINE_ALOWD_CHRG_AMT_11(String lINE_ALOWD_CHRG_AMT_11) {
		LINE_ALOWD_CHRG_AMT_11 = lINE_ALOWD_CHRG_AMT_11;
	}

	public String getLINE_ALOWD_CHRG_AMT_12() {
		return LINE_ALOWD_CHRG_AMT_12;
	}

	public void setLINE_ALOWD_CHRG_AMT_12(String lINE_ALOWD_CHRG_AMT_12) {
		LINE_ALOWD_CHRG_AMT_12 = lINE_ALOWD_CHRG_AMT_12;
	}

	public String getLINE_ALOWD_CHRG_AMT_13() {
		return LINE_ALOWD_CHRG_AMT_13;
	}

	public void setLINE_ALOWD_CHRG_AMT_13(String lINE_ALOWD_CHRG_AMT_13) {
		LINE_ALOWD_CHRG_AMT_13 = lINE_ALOWD_CHRG_AMT_13;
	}

	public String getLINE_PRCSG_IND_CD_1() {
		return LINE_PRCSG_IND_CD_1;
	}

	public void setLINE_PRCSG_IND_CD_1(String lINE_PRCSG_IND_CD_1) {
		LINE_PRCSG_IND_CD_1 = lINE_PRCSG_IND_CD_1;
	}

	public String getLINE_PRCSG_IND_CD_2() {
		return LINE_PRCSG_IND_CD_2;
	}

	public void setLINE_PRCSG_IND_CD_2(String lINE_PRCSG_IND_CD_2) {
		LINE_PRCSG_IND_CD_2 = lINE_PRCSG_IND_CD_2;
	}

	public String getLINE_PRCSG_IND_CD_3() {
		return LINE_PRCSG_IND_CD_3;
	}

	public void setLINE_PRCSG_IND_CD_3(String lINE_PRCSG_IND_CD_3) {
		LINE_PRCSG_IND_CD_3 = lINE_PRCSG_IND_CD_3;
	}

	public String getLINE_PRCSG_IND_CD_4() {
		return LINE_PRCSG_IND_CD_4;
	}

	public void setLINE_PRCSG_IND_CD_4(String lINE_PRCSG_IND_CD_4) {
		LINE_PRCSG_IND_CD_4 = lINE_PRCSG_IND_CD_4;
	}

	public String getLINE_PRCSG_IND_CD_5() {
		return LINE_PRCSG_IND_CD_5;
	}

	public void setLINE_PRCSG_IND_CD_5(String lINE_PRCSG_IND_CD_5) {
		LINE_PRCSG_IND_CD_5 = lINE_PRCSG_IND_CD_5;
	}

	public String getLINE_PRCSG_IND_CD_6() {
		return LINE_PRCSG_IND_CD_6;
	}

	public void setLINE_PRCSG_IND_CD_6(String lINE_PRCSG_IND_CD_6) {
		LINE_PRCSG_IND_CD_6 = lINE_PRCSG_IND_CD_6;
	}

	public String getLINE_PRCSG_IND_CD_7() {
		return LINE_PRCSG_IND_CD_7;
	}

	public void setLINE_PRCSG_IND_CD_7(String lINE_PRCSG_IND_CD_7) {
		LINE_PRCSG_IND_CD_7 = lINE_PRCSG_IND_CD_7;
	}

	public String getLINE_PRCSG_IND_CD_8() {
		return LINE_PRCSG_IND_CD_8;
	}

	public void setLINE_PRCSG_IND_CD_8(String lINE_PRCSG_IND_CD_8) {
		LINE_PRCSG_IND_CD_8 = lINE_PRCSG_IND_CD_8;
	}

	public String getLINE_PRCSG_IND_CD_9() {
		return LINE_PRCSG_IND_CD_9;
	}

	public void setLINE_PRCSG_IND_CD_9(String lINE_PRCSG_IND_CD_9) {
		LINE_PRCSG_IND_CD_9 = lINE_PRCSG_IND_CD_9;
	}

	public String getLINE_PRCSG_IND_CD_10() {
		return LINE_PRCSG_IND_CD_10;
	}

	public void setLINE_PRCSG_IND_CD_10(String lINE_PRCSG_IND_CD_10) {
		LINE_PRCSG_IND_CD_10 = lINE_PRCSG_IND_CD_10;
	}

	public String getLINE_PRCSG_IND_CD_11() {
		return LINE_PRCSG_IND_CD_11;
	}

	public void setLINE_PRCSG_IND_CD_11(String lINE_PRCSG_IND_CD_11) {
		LINE_PRCSG_IND_CD_11 = lINE_PRCSG_IND_CD_11;
	}

	public String getLINE_PRCSG_IND_CD_12() {
		return LINE_PRCSG_IND_CD_12;
	}

	public void setLINE_PRCSG_IND_CD_12(String lINE_PRCSG_IND_CD_12) {
		LINE_PRCSG_IND_CD_12 = lINE_PRCSG_IND_CD_12;
	}

	public String getLINE_PRCSG_IND_CD_13() {
		return LINE_PRCSG_IND_CD_13;
	}

	public void setLINE_PRCSG_IND_CD_13(String lINE_PRCSG_IND_CD_13) {
		LINE_PRCSG_IND_CD_13 = lINE_PRCSG_IND_CD_13;
	}

	public String getLINE_ICD9_DGNS_CD_1() {
		return LINE_ICD9_DGNS_CD_1;
	}

	public void setLINE_ICD9_DGNS_CD_1(String lINE_ICD9_DGNS_CD_1) {
		LINE_ICD9_DGNS_CD_1 = lINE_ICD9_DGNS_CD_1;
	}

	public String getLINE_ICD9_DGNS_CD_2() {
		return LINE_ICD9_DGNS_CD_2;
	}

	public void setLINE_ICD9_DGNS_CD_2(String lINE_ICD9_DGNS_CD_2) {
		LINE_ICD9_DGNS_CD_2 = lINE_ICD9_DGNS_CD_2;
	}

	public String getLINE_ICD9_DGNS_CD_3() {
		return LINE_ICD9_DGNS_CD_3;
	}

	public void setLINE_ICD9_DGNS_CD_3(String lINE_ICD9_DGNS_CD_3) {
		LINE_ICD9_DGNS_CD_3 = lINE_ICD9_DGNS_CD_3;
	}

	public String getLINE_ICD9_DGNS_CD_4() {
		return LINE_ICD9_DGNS_CD_4;
	}

	public void setLINE_ICD9_DGNS_CD_4(String lINE_ICD9_DGNS_CD_4) {
		LINE_ICD9_DGNS_CD_4 = lINE_ICD9_DGNS_CD_4;
	}

	public String getLINE_ICD9_DGNS_CD_5() {
		return LINE_ICD9_DGNS_CD_5;
	}

	public void setLINE_ICD9_DGNS_CD_5(String lINE_ICD9_DGNS_CD_5) {
		LINE_ICD9_DGNS_CD_5 = lINE_ICD9_DGNS_CD_5;
	}

	public String getLINE_ICD9_DGNS_CD_6() {
		return LINE_ICD9_DGNS_CD_6;
	}

	public void setLINE_ICD9_DGNS_CD_6(String lINE_ICD9_DGNS_CD_6) {
		LINE_ICD9_DGNS_CD_6 = lINE_ICD9_DGNS_CD_6;
	}

	public String getLINE_ICD9_DGNS_CD_7() {
		return LINE_ICD9_DGNS_CD_7;
	}

	public void setLINE_ICD9_DGNS_CD_7(String lINE_ICD9_DGNS_CD_7) {
		LINE_ICD9_DGNS_CD_7 = lINE_ICD9_DGNS_CD_7;
	}

	public String getLINE_ICD9_DGNS_CD_8() {
		return LINE_ICD9_DGNS_CD_8;
	}

	public void setLINE_ICD9_DGNS_CD_8(String lINE_ICD9_DGNS_CD_8) {
		LINE_ICD9_DGNS_CD_8 = lINE_ICD9_DGNS_CD_8;
	}

	public String getLINE_ICD9_DGNS_CD_9() {
		return LINE_ICD9_DGNS_CD_9;
	}

	public void setLINE_ICD9_DGNS_CD_9(String lINE_ICD9_DGNS_CD_9) {
		LINE_ICD9_DGNS_CD_9 = lINE_ICD9_DGNS_CD_9;
	}

	public String getLINE_ICD9_DGNS_CD_10() {
		return LINE_ICD9_DGNS_CD_10;
	}

	public void setLINE_ICD9_DGNS_CD_10(String lINE_ICD9_DGNS_CD_10) {
		LINE_ICD9_DGNS_CD_10 = lINE_ICD9_DGNS_CD_10;
	}

	public String getLINE_ICD9_DGNS_CD_11() {
		return LINE_ICD9_DGNS_CD_11;
	}

	public void setLINE_ICD9_DGNS_CD_11(String lINE_ICD9_DGNS_CD_11) {
		LINE_ICD9_DGNS_CD_11 = lINE_ICD9_DGNS_CD_11;
	}

	public String getLINE_ICD9_DGNS_CD_12() {
		return LINE_ICD9_DGNS_CD_12;
	}

	public void setLINE_ICD9_DGNS_CD_12(String lINE_ICD9_DGNS_CD_12) {
		LINE_ICD9_DGNS_CD_12 = lINE_ICD9_DGNS_CD_12;
	}

  }

}
