package classes;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Ese
 */
@ManagedBean(name="search_bean", eager=true)
@SessionScoped
public class SearchPrisonerBean {

    String searchword="";

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }
    public void changeText(ValueChangeEvent levent){
        searchword=levent.getNewValue().toString();
        System.out.println(searchword);
    }
    public List<PrisonerData> getPrisonerData()
    {
        List<PrisonerData> rlist=new ArrayList<>();
        try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/frs_dbase", "root", "password@1"))
        {
        String sql="select * from offender where firstname like '" + searchword+ "%' or othername like'" + searchword + "&'";
        PreparedStatement stmt=con.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            
            rlist.add(new PrisonerData(rs.getString("firstname"), rs.getString("othername"), rs.getString("serial_id"),
            rs.getInt("phone_no"), rs.getString("image_url"), rs.getString("birth_date"), rs.getString("plate_number"),
            rs.getString("crime_committed"), rs.getString("image_url")));
        }
        
        }
        
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return rlist;
    }
    public SearchPrisonerBean() {
    }
    
}
