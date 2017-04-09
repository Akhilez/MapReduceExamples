Description
------------------
Payroll dataset
_____________

Location:
------------
>/dump/datasets/payroll.csv

File format:
--------------

    Sno.    Field Name

    1       Name
    2       Position Title
    3       Department
    4       Employee Annual Salary


Regular expression:
---------------
java:

>(\"[\\w ]+, [\\w ]+\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})