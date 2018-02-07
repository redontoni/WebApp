package comapany.management.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author user
 */
@ManagedBean(name = "dashboardBean")
@SessionScoped
public class DashboardFragmentsBean {
    private String fragment;
    
    public DashboardFragmentsBean(){
        fragment="/private/fragments/dashboard_fragments/home.xhtml";
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
