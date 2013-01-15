package com.kiveukoi;

import java.util.HashMap;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Soap {

	private SoapObject so;

	public Soap(String methodName, HashMap<String, String> property) {
		so = connect(methodName, property);
	}

	private SoapObject connect(String methodName,
			HashMap<String, String> property) {
		String SOAP_ACTION = "http://tempuri.org/";
		String NAMESPACE = "http://tempuri.org/" + methodName;
		String URL = "http://kiveukoi.dyndns.org/PPE-WS-SOAP/client.php";

		try {
			SoapObject Request = new SoapObject(NAMESPACE, methodName);

			for (String key : property.keySet()) {
				Request.addProperty(key, property.get(key));
			}

			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER12);
			soapEnvelope.dotNet = false;
			soapEnvelope.setOutputSoapObject(Request);

			HttpTransportSE transport = new HttpTransportSE(URL);

			transport.call(SOAP_ACTION, soapEnvelope);
			SoapObject resultString = (SoapObject) soapEnvelope.getResponse();
			return resultString;
		} catch (Exception ex) {
			return null;
		}
	}

	public SoapObject getSo() {
		return so;
	}
}