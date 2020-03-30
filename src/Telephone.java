/*
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://\\Z:\\Syllabus and Papers\\JPR\\Projects\\Telrphone Dir\\src\\Telephone.accdb");
            Statement st  = con.createStatement();
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


public class Telephone
{
    String fname;
    String lname;
    String user;
    String pass;
    String phone;
    ResultSet check;
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

    void register()
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter First Name: ");
            fname = r.readLine();
            System.out.println("Enter  Last Name: ");
            lname = r.readLine();
            while (true) {
                System.out.println("Enter Username: ");
                user = r.readLine();
                System.out.println("Enter Password: ");
                pass = r.readLine();
                try {
                    st.executeUpdate("INSERT INTO Login VALUES('" + fname + "','" + lname + "','" + user + "','" + pass+"')");
                    System.out.println("Registration Successful");
                    break;
                }
                catch (SQLIntegrityConstraintViolationException s)
                {
                    System.out.println("Username Already Exists!!\n");
                }
            }
            con.close();
        }
        catch(IOException | ClassNotFoundException | SQLException i)
        {
            i.printStackTrace();
        }
    }

    boolean login()
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter Username: ");
            user = r.readLine();
            System.out.println("Enter Password: ");
            pass = r.readLine();
            check = st.executeQuery("SELECT Fname, Lname, Password from Login where Username ='" + user+"' ");
            if(check.next())
            {
                if(check.getString("Password").equals(pass)) {
                    fname = check.getString("Fname");
                    lname = check.getString("Lname");
                    System.out.println("Welcome "+ fname + " " + lname + "\nLogin Successful");
                    con.close();
                    return true;
                }
                else {
                    System.out.println("Password Entered is incorrect");
                    con.close();
                    return false;
                }
            }
            else
            {
                System.out.println(user+" does not exists!\nPlease Register");
                con.close();
                return false;
            }

        }
        catch (IOException | ClassNotFoundException | SQLException i)
        {
            i.printStackTrace();
            return false;
        }
    }

    void insert()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st = con.createStatement();
            System.out.println("Enter Phone number: ");
            phone = r.readLine();
            System.out.println("Enter First Name: ");
            fname = r.readLine();
            System.out.println("Enter Last Name: ");
            lname = r.readLine();
            st.executeUpdate("INSERT INTO  Directory(Username, Phone, Fname, Lname) VALUES('" + user + "','" + phone + "','" + fname + "','" + lname+"')");
            System.out.println("Contact Created");
            con.close();
        }
        catch (IOException | SQLException | ClassNotFoundException i)
        {
            i.printStackTrace();
        }
    }

    ResultSet searchbyboth() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter First Name: ");
            fname = r.readLine();
            System.out.println("Enter Last Name: ");
            lname = r.readLine();
            check = st.executeQuery("SELECT ID, Fname, Lname, Phone FROM Directory WHERE Username = '" + user + "' AND Fname = '" + fname + "'AND Lname = '" + lname + "'");
            ResultSetMetaData md = check.getMetaData();
            while (check.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.println(md.getColumnName(i)+" : "+check.getString(i));
                }
                System.out.println();
            }
            return check;
        }catch (IOException i)
        {
            i.printStackTrace();
            return check;
        } catch (SQLException i)
        {
            System.out.println("CONTACT NOW FOUND");
            return check;
        } catch (ClassNotFoundException i)
        {
            i.printStackTrace();
            return check;
        }
    }

    ResultSet searchbyfname()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter First Name: ");
            fname = r.readLine();
            check = st.executeQuery("SELECT ID,Fname, Lname, Phone FROM Directory WHERE Username = '" + user + "' AND Fname = '" + fname + "'");
            return check;
        }catch (IOException i)
        {
            i.printStackTrace();
            return check;
        } catch (SQLException i)
        {
            System.out.println("CONTACT NOW FOUND");
            return check;
        } catch (ClassNotFoundException i)
        {
            i.printStackTrace();
            return check;
        }
    }

    ResultSet searchbylname()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter Last Name: ");
            lname = r.readLine();
            check = st.executeQuery("SELECT ID,Fname, Lname,Phone FROM Directory WHERE Username = '" + user +  "'AND Lname = '" + lname + "'");
            return check;
        }
        catch (IOException i)
        {
            i.printStackTrace();
            return check;
        } catch (SQLException i)
        {
            System.out.println("CONTACT NOW FOUND");
            return check;
        } catch (ClassNotFoundException i)
        {
            i.printStackTrace();
            return check;
        }
    }

        ResultSet searchbynum()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            System.out.println("Enter Phone Number: ");
            phone = r.readLine();
            check = st.executeQuery("SELECT ID,Fname, Lname, Phone FROM Directory WHERE Username = '" + user + "' AND Phone = '" + phone + "'");
            return check;
        } catch (IOException i)
        {
            i.printStackTrace();
            return check;
        } catch (SQLException i)
        {
            System.out.println("CONTACT NOW FOUND");
            return check;
        } catch (ClassNotFoundException i)
        {
            i.printStackTrace();
            return check;
        }
    }

    void showall()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st  = con.createStatement();
            check = st.executeQuery("SELECT ID, Fname, Lname, Phone FROM Directory WHERE Username = '" + user + "'");
            ResultSetMetaData md = check.getMetaData();
            while (check.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.println(md.getColumnName(i)+" : "+check.getString(i));
                }
                System.out.println();
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    int[] search()
    {
        try {
            System.out.println("1.Search By First Name\n2.Search By Last Name\n3.Search By Both Names\n4.Search By Number");
            ResultSet check = null;
            int c = Integer.parseInt(r.readLine());
            switch (c)
            {
                case 1:
                    check = searchbyfname();
                    break;
                case 2:
                    check = searchbylname();
                    break;
                case 3:
                    check = searchbyboth();
                    break;
                case 4:
                    check = searchbynum();
                    break;
                default:
                    System.out.println("WRONG OPTION SELECTED");
                    break;
            }
            assert check != null;
            ResultSetMetaData md = check.getMetaData();
            if(md.getColumnCount()>0) {
                int[] id = new int[100];
                while (check.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.println(md.getColumnName(i) + " : " + check.getString(i));
                        id[i] = check.getInt("ID");
                    }
                    System.out.println();

                }
                return id;
            }
            else
            {
                System.out.println("No Contact Found");
                return new int[]{0};
            }
        }
        catch (IOException | SQLException | NullPointerException e)
        {
            e.printStackTrace();
            return new int[]{0};
        }
    }

    boolean contains(int [] arr,int tocheck)
    {   boolean a = false;
        for(int el : arr)
        {
            if(el==tocheck) {
                a =  true;
                break;
            }
        }
        return a;
    }

    void delete()
    {
        try {
            int id;
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st = con.createStatement();
            System.out.println("SEARCH THE CONTACT TO DELETE");
            int[] q = search();
            if(q.length>0) {
                if (q.length > 1) {
                    System.out.println("ENTER ID OF CONTACT TO DELETE(TO EXIT DELETE OPTION PRESS 0):  ");
                    id = Integer.parseInt(r.readLine());
                    if(id!=0) {
                        if (contains(q,id)) {
                            st.executeUpdate("DELETE FROM Directory where ID = '" + id + "' ");
                            System.out.println("CONTACT DELETED");
                        } else {
                            System.out.println("ID Does not Exists!");
                        }
                    }
                    else
                    {
                        System.out.println("DELETE OPTION EXITED");
                    }
                }
                else if(q.length == 1)
                {
                    st.executeUpdate("DELETE FROM Directory where ID = '" + q[0] + "' ");
                    System.out.println("CONTACT DELETED");
                }
            }
        }
        catch (IOException | SQLException | ClassNotFoundException i)
        {
            i.printStackTrace();
        }
    }

    void update()
    {
        try {
            int id;
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\Telephone.accdb");
            Statement st = con.createStatement();
            System.out.println("SEARCH THE CONTACT TO UPDATE");
            int[] q = search();
            if(q.length>0) {
                if (q.length > 1) {
                    System.out.println("ENTER ID OF CONTACT TO UPDATE(TO EXIT UPDATE OPTION PRESS 0):  ");
                    id = Integer.parseInt(r.readLine());
                    if(id!=0) {
                        if (contains(q,id)) {
                            System.out.println("PLEASE SELECT\n1.UPDATE FIRST NAME\n2.UPDATE LAST NAME\n3.UPDATE PHONE NUMBER\n0.EXIT UPDATE");
                            int ch = Integer.parseInt(r.readLine());
                            switch (ch)
                            {
                                case 0:
                                    System.out.println("UPDATE OPTION EXITED");
                                    break;
                                case 1:
                                    System.out.println("ENTER NEW FIRST NAME: ");
                                    fname = r.readLine();
                                    st.executeUpdate("UPDATE Directory SET Fname = '" + fname + "' WHERE ID = '" + id + "' ");
                                    System.out.println("CONTACT UPDATED");
                                    break;
                                case 2:
                                    System.out.println("ENTER NEW LAST NAME: ");
                                    lname = r.readLine();
                                    st.executeUpdate("UPDATE Directory SET Lname = '" + lname + "' WHERE ID = '" + id + "' ");
                                    System.out.println("CONTACT UPDATED");
                                    break;
                                case 3:
                                    System.out.println("ENTER NEW FIRST NAME: ");
                                    phone = r.readLine();
                                    st.executeUpdate("UPDATE Directory SET Phone = '" + phone + "' WHERE ID = '" + id + "' ");
                                    System.out.println("CONTACT UPDATED");
                                    break;
                                default:
                                    System.out.println("NO SUCH OPTION");
                            }
                        } else {
                            System.out.println("ID Does not Exists!");
                        }
                    }
                    else
                    {
                        System.out.println("UPDATE OPTION EXITED");
                    }
                }
                else if(q.length == 1)
                {
                    System.out.println("PLEASE SELECT\n1.UPDATE FIRST NAME\n2.UPDATE LAST NAME\n3.UPDATE PHONE NUMBER\n0.EXIT UPDATE");
                    int ch = Integer.parseInt(r.readLine());
                    switch (ch)
                    {
                        case 0:
                            System.out.println("UPDATE OPTION EXITED");
                            break;
                        case 1:
                            System.out.println("ENTER NEW FIRST NAME: ");
                            fname = r.readLine();
                            st.executeUpdate("UPDATE Directory SET Fname = '" + fname + "' WHERE ID = '" + q[0] + "' ");
                            System.out.println("CONTACT UPDATED");
                            break;
                        case 2:
                            System.out.println("ENTER NEW LAST NAME: ");
                            lname = r.readLine();
                            st.executeUpdate("UPDATE Directory SET Lname = '" + lname + "' WHERE ID = '" + q[0] + "' ");
                            System.out.println("CONTACT UPDATED");
                            break;
                        case 3:
                            System.out.println("ENTER NEW FIRST NAME: ");
                            phone = r.readLine();
                            st.executeUpdate("UPDATE Directory SET Phone = '" + phone + "' WHERE ID = '" + q[0] + "' ");
                            System.out.println("CONTACT UPDATED");
                            break;
                        default:
                            System.out.println("NO SUCH OPTION");
                    }
                }
            }
        }
        catch (IOException | SQLException | ClassNotFoundException i)
        {
            i.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            boolean x = true;
            boolean y = true;
            Telephone t = new Telephone();
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            while (x) {
                System.out.println("1.REGISTER\n2.LOGIN\n3.EXIT");
                int a = Integer.parseInt(r.readLine());
                switch (a)
                {
                    case 1:
                        System.out.println("REGISTRATION STARTED....");
                        t.register();
                        break;
                    case 2:
                        System.out.println("LOGGING IN....");
                        if(t.login()) {
                            while (y) {
                                System.out.println("1/SHOW ALL CONTACTS\n2.CREATE NEW CONTACT\n3.SEARCH CONTACT\n4.UPDATE CONTACT\n5.DELETE CONTACT\n6.LOGOUT");
                                int b = Integer.parseInt(r.readLine());
                                switch (b) {
                                    case 1:
                                        t.showall();
                                        break;
                                    case 2:
                                        t.insert();
                                        break;
                                    case 3:
                                        t.search();
                                        break;
                                    case 4:
                                        t.update();
                                        break;
                                    case 5:
                                        t.delete();
                                        break;
                                    case 6:
                                        System.out.println("LOGGING OUT....");
                                        y = false;
                                        break;
                                    default:
                                        System.out.println("PLEASE SELECT OPTIONS AMONG 1,2,3,4 AND 5");
                                        break;
                                }
                            }
                            System.out.println("LOGGED OUT");
                        }
                        break;
                    case 3:
                        System.out.println("EXITING....");
                        x = false;
                        break;
                    default:
                        System.out.println("PLEASE SELECT OPTIONS AMONG 1,2 AND 3");
                        break;
                }
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
