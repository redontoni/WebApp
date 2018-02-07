/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comapany.management.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author user
 */
@ManagedBean(name = "homeBean")
@ViewScoped
public class HomeFragmentsBean {
    private String fragment;
    
    public HomeFragmentsBean(){
        fragment="/private/fragments/index_fragments/home.xhtml";
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
