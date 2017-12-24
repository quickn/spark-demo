package com.quickn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by json2 on 2017/12/24.
  */
object TestLocal {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("TEST_LOCAL")

    val sc = new SparkContext(conf)
    val input = sc.textFile(args(0))
    val output = args(1).toString

    val UI_RDD = input.filter { x =>
      val fields = x.split("	")
      fields(2).toDouble > 0.001
    }.map { x =>
      val fields = x.split("	")
      (fields(0).toString, (fields(1).toString, fields(2).toString))
    }.groupByKey().map { x =>
      val userid = x._1
      val i_s_list = x._2

      val i_s_arr = i_s_list.toArray
      val len = i_s_arr.length
      val buf = new StringBuilder;
      for (i <- 0 until len - 1) {
        buf ++= i_s_arr(i)._1
        buf.append(",")
      }
      buf ++= i_s_arr(len - 1)._1
      userid + "\t" + buf
    }.saveAsTextFile(output)
  }
}
