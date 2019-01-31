package com.ssafy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FoodSAX {
	
	FoodSAX(){
		String url = "http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?ServiceKey=nw2RgjbfShJMzZ05sLGUzWEasNUweUuRNuA6YHyEvNHn9b3Ahc9rp8VMOKYbPW5qb%2FKqQ0eP1imWvPWKnjJ9Zw%3D%3D&numOfRows=100";
		connectFoods(url);
	}
	
	List<Foods> list = new ArrayList<>();
	
	public List<Foods> getFoodsList(){
		//list.clear();
//		String url = "http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?ServiceKey=nw2RgjbfShJMzZ05sLGUzWEasNUweUuRNuA6YHyEvNHn9b3Ahc9rp8VMOKYbPW5qb%2FKqQ0eP1imWvPWKnjJ9Zw%3D%3D&numOfRows=100";
//		connectFoods(url);
		return list;
	}
	
	public void connectFoods(String url) {
		SAXParserFactory f = null;
		try {
			f = SAXParserFactory.newInstance();
			SAXParser p = f.newSAXParser();
			p.parse(new URL(url).openConnection().getInputStream(), new SAXHandler());
		} catch (SAXException | IOException e) {
			System.out.println(e);
		} catch (ParserConfigurationException e) {
			System.out.println(e);
		}
	}
	
	class SAXHandler extends DefaultHandler{
		StringBuilder b = new StringBuilder();
		boolean flag = false;
		Foods food = null;
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equalsIgnoreCase("item")) {
				food = new Foods();
				flag = true;
			}
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(flag && food != null) {
				switch(qName) {
				case "prdlstReportNo": food.setFoodcode(b.toString().trim());
					break;
				case "prdlstNm": food.setFoodname(b.toString().trim());
					break;
				case "manufacture": food.setMaker(b.toString().trim());
					break;
				case "rawmtrl": food.setMaterial(b.toString().trim());
					break;
				case "item":
					list.add(food);
				}
			}
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			b.setLength(0);
			b.append(ch,start,length);
		}
		
		
	}
}

