package com.kiveukoi;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class SoapHelper
{
	private static final String	SOAP_ACTION	= "listeGroupes";
	private static final String	METHOD_NAME	= "listeGroupes";
	private static final String	NAMESPACE	= "http://schemas.xmlsoap.org/soap/envelope/";
	//L'URL suivante ne peut pas être localhost car localhost représente l'émulateur
	private static final String	URL	= "http://kiveukoi.dyndns.org/PPE-WS-SOAP/test.xml";

	public static SoapObject soap (String property, String value) throws IOException, XmlPullParserException
	{
		// Création de la requête SOAP
		SoapObject request = new SoapObject (NAMESPACE, METHOD_NAME);
		//Ajout de propriété: addProperty(nom de variable, valeur) -> Le nom de la variable vient du fichier WSDL
		if (property != null)
			request.addProperty(property, value);
		//Toutes les données demandées sont mises dans une enveloppe.
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope (
				SoapEnvelope.VER11);
		//Préparation de la requête
		envelope.setOutputSoapObject (request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE (URL);
		//Ceci est optionnel, on l'utilise pour savoir si nous voulons ou non utiliser 
		//un paquet "sniffer" pour vérifier le message original (androidHttpTransport.requestDump)
		androidHttpTransport.debug = true; 
		//Envoie de la requête
		androidHttpTransport.call (SOAP_ACTION, envelope);
		//Obtention du résultat
		SoapObject soapResult = (SoapObject) envelope.getResponse ();
		return soapResult;
	}
}
