package com.dpworld.androidapp.helpers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class DPServices {
	
	public static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
	public static final String SOAP_ADDRESS = "http://lfs.qict.com.pk/vbs/service.asmx";
	
	String operationName = "";
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getSoapAction(){
			String toReturn = WSDL_TARGET_NAMESPACE + operationName;
		return toReturn;
	}
	
	public static String containerStatus(String containerNo, String userid, String pass){
		final String SOAP_ACTION = "http://tempuri.org/Container_Status";
		final String OPERATION_NAME = "Container_Status"; 
		final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
		final String SOAP_ADDRESS = "http://lfs.qict.com.pk/vbs/service.asmx";		
	
		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
		PropertyInfo pi;
		
		pi=new PropertyInfo();
		pi.setName("Container_No");
	    pi.setValue(containerNo);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("UserID");
	    pi.setValue(userid);
	    pi.setType(String.class);
	    request.addProperty(pi);
	    pi=new PropertyInfo();
	    pi.setName("Password");
	    pi.setValue(pass);
	    pi.setType(String.class);
	    request.addProperty(pi);	    
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
		SoapEnvelope.VER11);
		envelope.dotNet = true;
	
		envelope.setOutputSoapObject(request);
	
		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response=null;
		try{
			httpTransport.call(SOAP_ACTION, envelope);
			response = envelope.getResponse();
		}
		catch (Exception exception){
			response=exception.toString();
		}
		return response.toString();
	}
	
}
