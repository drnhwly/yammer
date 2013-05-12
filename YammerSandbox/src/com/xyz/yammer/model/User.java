package com.xyz.yammer.model;

import java.util.Date;

public class User {

	public String type;
	public String full_name;
	public String mugshot_url_template;
	public String significant_other;
	public Boolean verified_admin;
	public String kids_names;
	public String timezone;
	public String guid;
	public String location;
	public String last_name;
	public String hire_date;
	public String name;

	public School[] schools;
	
	public String birth_date;
	public String mugshot_url;
//	"settings":{ "xdr_proxy":"https://xdrproxy.yammer.com" },
	public String[] network_domains;
	
	public String url;
	public String web_url;
	public String expertise;
	public String summary;
	public String interests;
	public String state;
	
	public Stats stats;
	
	public Boolean can_broadcast;
	public String department;
	public Integer id;

	public Company[] previous_companies;
	public String first_name;
	public Integer network_id;
	public String network_name;
	public String job_title;
	public Boolean show_ask_for_photo;
	public String activated_at;
	public Boolean admin;

	public Contact contact;
	
	public String[] external_urls;

}
