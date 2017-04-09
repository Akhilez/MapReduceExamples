# MapReduce Examples

This project is to refer the mapreduce concepts that are needed to learn for hadoop certification exams.

Instructions:
--------------
Include the Hadoop jar files in eclipse
>Right-click project name > Build Path > Configure Build Path..

>Libraries tab > Add External Jars

>Select *hadoop-common-2.7.3.jar* and *hadoop-mapreduce-client-core-2.7.3.jar*
______________________
Creating your jar file in eclipse:
>Right-click > Export > Java > JAR File > Next

>Choose your destination by clicking *Browse* and hit *Finish*
_____________
Using putty, we can run the files as follows:

Uploading the jar file on CMD:
>pscp.exe Tests/flights/payrollDeptDetails.jar student@cluster_ip:/home/student
__________________
Running on PUTTY:
>yarn jar mr1.jar MRDriver /input/path/example/dump/demo/dept.txt /output/path/newFolderName
________________
Displaying output on PUTTY:
>hdfs dfs -ls /dump/MROut

>hdfs dfs -cat /dump/MROut/part-r-00000


Note:
-------------
    This is for reference purposes only.
    The code you see is working 100%.
    Do not try to install this repo and run natively.