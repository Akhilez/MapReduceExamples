
Data set: http log file

www-a1.proxy.aol.com - - [04/Jul/1995:20:54:18 -0400] "GET /facilities/tour.html HTTP/1.0" 200 3723
euler.princeton.edu - - [04/Jul/1995:20:54:19 -0400] "GET /shuttle/missions/sts-71/sts-71-patch-small.gif HTTP/1.0" 200 12054
magi01p13.magi.com - - [04/Jul/1995:20:54:23 -0400] "GET /shuttle/missions/sts-71/images/KSC-95EC-0868.jpg HTTP/1.0" 200 61848
dv2.mpcs.com - - [04/Jul/1995:20:54:24 -0400] "GET /cgi-bin/imagemap/countdown?326,281 HTTP/1.0" 302 98

create an xml file with the ip address and response size for each entry.

sample output:
<results>
...
...
<result>                                                                                            
<key>yhm0071.bekkoame.or.jp</key>                                                                   
<value>7074</value>                                                                                 
</result>                                                                                           
<result>                                                                                            
<key>yhm0071.bekkoame.or.jp</key>                                                                   
<value>3214</value>                                                                                 
</result>                                                                                           
<result>                                                                                            
<key>yhm0071.bekkoame.or.jp</key>                                                                   
<value>7634</value>                                                                                 
</result>                                                                                           
</results> 


