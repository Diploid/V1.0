/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class RegistrationBean
{
 private RegistrationForm registrationForm = null;
 private List<String> interests = null;
 private List<String> genders = null;
 
 public RegistrationBean()
 {
  this.interests = new ArrayList<String>();
  this.interests.add("Sports");
  this.interests.add("Gadgets");
  this.interests.add("Politics");
  this.interests.add("Technology");
  
  this.genders = new ArrayList<String>();
  this.genders.add("Male");
  this.genders.add("Female");
 
 }
 
 public String register()
 {
  System.out.println("register.....");
  //store data in DB
  System.out.println(this.registrationForm);
  return "welcome";//go to welcome.xhtml
 }
 
 public RegistrationForm getRegistrationForm()
 {
  if(this.registrationForm == null){
   this.registrationForm = new RegistrationForm();
  }
  return registrationForm;
 }

 public void setRegistrationForm(RegistrationForm registrationForm)
 {
  this.registrationForm = registrationForm;
 }

 public List<String> getInterests()
 {
  return interests;
 }

 public void setInterests(List<String> interests)
 {
  this.interests = interests;
 }

 public List<String> getGenders()
 {
  return genders;
 }

 public void setGenders(List<String> genders)
 {
  this.genders = genders;
 }
 
}