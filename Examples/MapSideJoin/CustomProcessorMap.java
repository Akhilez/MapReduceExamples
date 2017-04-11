import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.filecache.DistributedCache;

import java.lang.*;
import java.util.*;
import java.io.*;

public class CustomerProcessorMap extends Mapper<Object, Text, Text, Text> {
    private String str = "", mapperOutKey = "", mapperOutValue = "", ordersString = "";
    private StringproductString="",customerString="",productId="";
    private HashMap<String, String> hashTableA = new HashMap<String, String>();
    private HashMap<String, String> hashTableB = new HashMap<String, String>();


    private BufferedReader cacheReader1, cacheReader2;

    public void setup(Context context) throws IOException, InterruptedException {

        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.getLocal(conf);

        Path[] dataFile = DistributedCache.getLocalCacheFiles(conf);

        // [0] because we added just one file.
        String str = "";
        cacheReader1 = new BufferedReader(new InputStreamReader(fs.open
                (dataFile[0])));
        // now one can use BufferedReader's readLine() to read data(orders)
        while ((str = cacheReader1.readLine()) != null) {
            String[] tokens = str.split(",");

            //key is customerid,value is orderid,date,pid
            hashTableA.put(tokens[2], tokens[0] + ',' + tokens[1] + "," + tokens[4]);
        }

        cacheReader2 = new BufferedReader(new InputStreamReader(fs.open
                (dataFile[1])));
        // now one can use BufferedReader's readLine() to read data(products)
        while ((str = cacheReader2.readLine()) != null) {
            String[] tokens = str.split(",");
            hashTableB.put(tokens[0], tokens[1]);//key is pid, value product name
        }

    }

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String splitarray[] = line.split(",");
        int customerId = Integer.parseInt(splitarray[0].trim());
        String cName = splitarray[1].trim();
        String cPhone = splitarray[2].trim();
        String cAddr = splitarray[3].trim();
        customerString = cName + "\t" + cPhone;
        Set<String> hashTablekeys1 = hashTableA.keySet(); //orders hashtable
        Set<String> hashTablekeys2 = hashTableB.keySet(); //products hashtable

        for (String hashkey1 : hashTablekeys1) {
            if ((customerId + "").equals(hashkey1)) {
                String oS = hashTableA.get(hashkey1);
                String[] orderArr = oS.split(",");
                productId = orderArr[2];
                ordersString = orderArr[0] + "\t" + orderArr[1];
            }
        }
        for (String hashkey1 : hashTablekeys2) {
            if (productId.equals(hashkey1))
                productString = hashTableB.get(hashkey1);
        }

        context.write(new Text(customerString + "\t" + ordersString + "\t" + productString),
                new Text(mapperOutValue));
    }
}
