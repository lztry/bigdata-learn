package com.lz.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

object wordcount {
  def main(args:Array[String]): Unit ={
    val conf = new SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val inputFile = "file:///home/lz/code/bigdata/data/wordcount"
    val result = sc.textFile(inputFile).flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey((a,b)=>a+b)
    result.foreach(println);
  }

}
