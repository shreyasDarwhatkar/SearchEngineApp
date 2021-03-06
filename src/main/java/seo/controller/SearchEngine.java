package seo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import seo.model.ResultBean;

 
@Controller
public class SearchEngine {
 
	private static final String VIEW_INDEX = "index";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model, HttpSession session) {
 
		return VIEW_INDEX;
 
	}
	
	@RequestMapping(value = "/startSearch", method = RequestMethod.GET)
	public String startSearch( ModelMap model, @RequestParam String data, HttpSession session ) throws FileNotFoundException, IOException, ParseException {
 
		String word = data;
        String jsonPath1 = "C:\\Users\\Gaurav\\CS454_Web_Search_Engine\\Indexer\\target\\indexer.json";
        String jsonPath2 = "C:\\Users\\Gaurav\\CS454_Web_Search_Engine\\Indexer\\target\\ranking.json";
        File jsonFile1 = new File(jsonPath1);
        File jsonFile2 = new File(jsonPath2);
        JSONParser jsonParser = new JSONParser();
		JSONObject indexObj = (JSONObject) jsonParser.parse(new FileReader(jsonFile1));
		JSONObject rankObj = (JSONObject) jsonParser.parse(new FileReader(jsonFile2));
		
		
		List<seo.model.ResultBean> results = new ArrayList<seo.model.ResultBean>();
		
		if(indexObj.get(word) == null)
		{
			session.setAttribute("results", null);
			return "displayResults";
		}
	
		JSONArray jsonArr = (JSONArray) indexObj.get(word);
		
		JSONObject jsonObj;
		seo.model.ResultBean resultSet;
		String temp;
		for(Object obj : jsonArr){
			jsonObj = (JSONObject) obj;
			resultSet = new seo.model.ResultBean();
			temp = (String) jsonObj.get("UUId");
			resultSet.setId(temp);
			temp = (String) jsonObj.get("Description");
			resultSet.setDesc(temp);
			temp = (String) jsonObj.get("Count");
			resultSet.setCount(Integer.parseInt(temp));
			temp = (String) jsonObj.get("TitleRank");
			resultSet.setTitleRank(Double.parseDouble(temp));
			temp = (String) jsonObj.get("Title");
			resultSet.setTitle(temp);
			temp = (String) jsonObj.get("TermFrequency");
			resultSet.setTermFreq(Double.parseDouble(temp));
			results.add(resultSet);
		}
		
		double temp1;
		for(ResultBean result : results){
			if(rankObj.get(result.getId())!=null){
			jsonObj = (JSONObject) rankObj.get(result.getId());
			temp1 = (Double) jsonObj.get("Rank");
			result.setPageRank(temp1);
			temp = (String) jsonObj.get("URL");
			result.setUrl(temp);
			}
		}
		results = cleanUrl(results);
		session.setAttribute("results", results);
		sortByTotal(results);
		//sortByPageRank(results);
		
	/*	for(ResultBean result : results){
			System.out.println(result.getUrl());
		}
	*/	
		
		return "displayResults";
 
	}

	
	public static void sortByPageRank(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getPageRank()<results.get(j+1).getPageRank())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByWordCount(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getCount()<results.get(j+1).getCount())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTitleRank(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getTitleRank()<results.get(j+1).getTitleRank())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTermFreq(List<ResultBean> results){
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				if(results.get(j).getTermFreq()<results.get(j+1).getTermFreq())
					swap(results,j,j+1);
			}
    }
    
    public static void sortByTotal(List<ResultBean> results){
    	double total1 = 0.0;
    	double total2 = 0.0;
    	for(int i = 0; i<results.size();i++)
			for(int j = 0; j<results.size()-1;j++)
			{
				total1 = (10*results.get(j).getPageRank())+results.get(j).getCount()+results.get(j).getTitleRank()+results.get(j).getTermFreq();
				total2 = (10*results.get(j+1).getPageRank())+results.get(j+1).getCount()+results.get(j+1).getTitleRank()+results.get(j+1).getTermFreq();
				
				//System.out.println(total1+" vs "+total2);
				if(total1<total2)
					swap(results,j,j+1);
			}
    }
    
    public static void swap(List<ResultBean> results, int x, int y){
    	ResultBean temp;
		temp = results.get(x);
		results.set(x, results.get(y));
		results.set(y, temp);
    }
    public static List<ResultBean> cleanUrl(List<ResultBean> result)
    {
    	Iterator<ResultBean> iterator = result.iterator();
    	while(iterator.hasNext())
    	{
    		ResultBean resultBean = iterator.next();
    		if(resultBean.getUrl().contains(".rss") || resultBean.getUrl().contains(".css"))
    		{
    			iterator.remove();
    		}
    	}
    	return result;
    }
 
}