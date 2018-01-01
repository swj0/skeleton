package com.wen.jun.rest.constants;


//这里的vend是记录日志的时候使用，不用区分是短信，还是邮件
//不要混用com.rsc.data20.common.SmsVendor
public enum Vendor {
	CHUANGLAN(1),
	SSE9(2);
	
	private short svl;
	
	Vendor(Integer i){
		svl = i.shortValue();
	}
	
	
	public short sval(){
		return this.svl;
	}
}
