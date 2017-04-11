Data set: http log file

www-a1.proxy.aol.com - - [04/Jul/1995:20:54:18 -0400] "GET /facilities/tour.html HTTP/1.0" 200 3723
euler.princeton.edu - - [04/Jul/1995:20:54:19 -0400] "GET /shuttle/missions/sts-71/sts-71-patch-small.gif HTTP/1.0" 200 12054
magi01p13.magi.com - - [04/Jul/1995:20:54:23 -0400] "GET /shuttle/missions/sts-71/images/KSC-95EC-0868.jpg HTTP/1.0" 200 61848
dv2.mpcs.com - - [04/Jul/1995:20:54:24 -0400] "GET /cgi-bin/imagemap/countdown?326,281 HTTP/1.0" 302 98

Create a custom writabel "LogProcessor", read data from input split as 
LogProcessor object and find the total response size for each website.

sample output:
www-b4.proxy.aol.com    2204                                                                        
www-b6.proxy.aol.com    1200                                                                        
www-d1.proxy.aol.com    608                                                                         
www-d4.proxy.aol.com    2600                                                                        
yhm0071.bekkoame.or.jp  800   