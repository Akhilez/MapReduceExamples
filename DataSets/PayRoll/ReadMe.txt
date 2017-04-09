input location: /dump/demo/payroll.csv

------------------

Fiel format:

Sno.    Field Name

1       Name
2       Position Title
3       Department
4       Employee Annual Salary

---------------------

Regular expression:

java:

1. "(\"[\\w ]+, [\\w ]+\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})"