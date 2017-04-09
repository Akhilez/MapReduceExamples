# MapReduce Examples

This project is to refer the mapreduce concepts that are needed to learn for hadoop certification exams.

Instructions:
--------------
Using putty, we can run the files as follows:

//uploading the jar file on CMD
>pscp.exe Tests/flights/payrollDeptDetails.jar student@172.168.1.30:/home/student

//running on PUTTY
yarn jar mr1.jar MRDriver /dump/demo/dept.txt /output/path

//displaying output on PUTTY
hdfs dfs -ls /dump/MROut
hdfs dfs -cat /dump/MROut/part-r-00000


Note:
    This is for reference purposes only.
    The code you see is working 100%.
    Do not try to install this repo and run natively.