package com.wen.jun.rest.base;

import static com.mongodb.client.model.Sorts.descending;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;


public class BaseService {
	
	protected SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
	
	protected static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMddHHmm");
	
	protected String current(){
		return format2.format(new Date());
	}
	/**
	 * 
	 * @param col
	 * @param fieldName
	 * @return
	 */
	protected  Integer getMaxId(MongoCollection col,String fieldName){
		
		//1.是否有记录
		long cnt = col.count();
		if(cnt < 1){
			//创建索引
			col.dropIndex("primary");
			col.createIndex(new BasicDBObject(fieldName,1),new IndexOptions().name("primary").unique(true));
			return 1;
		}
		//2.现在的id已经用到哪里
    	FindIterable<Document> iterable = col.find().sort(descending(fieldName)).limit(1);
    	Document first = iterable.first();
    	Integer i=0;
    	if(first != null){
    		i = first.getInteger(fieldName);
    		i=i+1;
    	}
    	return i;
    }
	

}
